import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;

/**
 * Enumeration of available stroke and paint styles to eliminate magic numbers and provide type safety.
 */
public enum StrokeStyle {
    THIN_BLACK(1, new BasicStroke(3.0f), Color.black, "3px black stroke"),
    NO_STROKE(3, new BasicStroke(0.0f), Color.black, "No stroke"),
    THICK_BLACK(4, new BasicStroke(7.0f), Color.black, "7px black stroke"),
    GRADIENT(7, new BasicStroke(3.0f), null, "Gradient paint"),
    RED(8, new BasicStroke(3.0f), Color.red, "Red paint");

    private final int code;
    private final BasicStroke defaultStroke;
    private final Paint defaultPaint;
    private final String description;

    StrokeStyle(int code, BasicStroke stroke, Paint paint, String description) {
        this.code = code;
        this.defaultStroke = stroke;
        this.defaultPaint = paint;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public BasicStroke getDefaultStroke() {
        return defaultStroke;
    }

    public Paint getDefaultPaint() {
        return defaultPaint;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Convert numeric code to StrokeStyle enum.
     * @param code The numeric stroke/paint code.
     * @return The corresponding StrokeStyle.
     * @throws IllegalArgumentException if code is not recognized.
     */
    public static StrokeStyle fromCode(int code) {
        for (StrokeStyle style : values()) {
            if (style.code == code) {
                return style;
            }
        }
        throw new IllegalArgumentException("Unsupported stroke style code: " + code);
    }
}
