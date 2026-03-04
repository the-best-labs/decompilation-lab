/*
 * Decompiled with CFR 0.152.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ShapeFactory {
    public Shape shape;
    public BasicStroke stroke = new BasicStroke(3.0f);
    public Paint paint = Color.black;
    public int width = 25;
    public int height = 25;

    /**
     * Creates a shape with specified type and style using type-safe enums.
     * @param shapeType The shape type (eliminates magic numbers).
     * @param strokeStyle The stroke/paint style (eliminates magic numbers).
     */
    public ShapeFactory(ShapeType shapeType, StrokeStyle strokeStyle) {
        createShape(shapeType);
        applyStyle(strokeStyle);
    }

    /**
     * Legacy constructor for backward compatibility. Decodes two-digit shape_type
     * where tens digit is shape and units digit is stroke/paint style.
     * @param shape_type Encoded shape type (e.g., 94 = circle with cut sector, 7px stroke).
     */
    public ShapeFactory(int shape_type) {
        ShapeType shapeType = ShapeType.fromCode(shape_type / 10);
        StrokeStyle strokeStyle = StrokeStyle.fromCode(shape_type % 10);
        createShape(shapeType);
        applyStyle(strokeStyle);
    }

    /**
     * Creates the shape based on ShapeType enum.
     */
    private void createShape(ShapeType shapeType) {
        switch (shapeType) {
            case STAR_3_ARM: {
                this.shape = ShapeFactory.createStar(3, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 2.0);
                break;
            }
            case STAR_5_ARM: {
                this.shape = ShapeFactory.createStar(5, new Point(0, 0), (double)this.width / 2.0, (double)this.width / 4.0);
                break;
            }
            case RECTANGLE: {
                this.shape = new Rectangle2D.Double((double)(-this.width) / 2.0, (double)(-this.height) / 2.0, this.width, this.height);
                break;
            }
            case TRIANGLE: {
                GeneralPath path = new GeneralPath();
                double tmp_height = Math.sqrt(2.0) / 2.0 * (double)this.height;
                path.moveTo((double)(-this.width / 2), -tmp_height);
                path.lineTo(0.0, -tmp_height);
                path.lineTo((double)(this.width / 2), tmp_height);
                path.closePath();
                this.shape = path;
                break;
            }
            case CIRCLE_SECTOR: {
                this.shape = new Arc2D.Double((double)(-this.width) / 2.0, (double)(-this.height) / 2.0, this.width, this.height, 30.0, 300.0, 2);
                break;
            }
        }
    }

    /**
     * Applies stroke and paint style based on StrokeStyle enum.
     */
    private void applyStyle(StrokeStyle strokeStyle) {
        this.stroke = strokeStyle.getDefaultStroke();

        // Special handling for gradient paint (needs width/height context)
        if (strokeStyle == StrokeStyle.GRADIENT) {
            this.paint = new GradientPaint(-this.width, -this.height, Color.white, this.width, this.height, Color.gray, true);
        } else {
            Paint defaultPaint = strokeStyle.getDefaultPaint();
            this.paint = (defaultPaint != null) ? defaultPaint : Color.black;
        }
    }

    /**
     * Creates a star shape with specified number of arms and radii.
     * @param arms Number of star arms (e.g., 3, 5).
     * @param center Center point of the star.
     * @param rOuter Outer radius (points of the star).
     * @param rInner Inner radius (valleys of the star).
     * @return A GeneralPath representing the star shape.
     */
    private static Shape createStar(int arms, Point center, double rOuter, double rInner) {
        double angle = Math.PI / (double)arms;
        GeneralPath path = new GeneralPath();
        int i = 0;
        while (i < 2 * arms) {
            double r = (i & 1) == 0 ? rOuter : rInner;
            Point2D.Double p = new Point2D.Double((double)center.x + Math.cos((double)i * angle) * r, (double)center.y + Math.sin((double)i * angle) * r);
            if (i == 0) {
                path.moveTo(p.getX(), p.getY());
            } else {
                path.lineTo(p.getX(), p.getY());
            }
            ++i;
        }
        path.closePath();
        return path;
    }
}

