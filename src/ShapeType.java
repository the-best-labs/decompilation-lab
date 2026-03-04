/**
 * Enumeration of available shape types to eliminate magic numbers and provide type safety.
 */
public enum ShapeType {
    STAR_3_ARM(1, "3-arm star"),
    STAR_5_ARM(3, "5-arm star"),
    RECTANGLE(5, "Rectangle"),
    TRIANGLE(7, "Triangle"),
    CIRCLE_SECTOR(9, "Circle with cut sector");

    private final int code;
    private final String description;

    ShapeType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Convert numeric code to ShapeType enum.
     * @param code The numeric shape code.
     * @return The corresponding ShapeType.
     * @throws IllegalArgumentException if code is not recognized.
     */
    public static ShapeType fromCode(int code) {
        for (ShapeType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported shape code: " + code);
    }
}
