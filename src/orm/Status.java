package orm;

public enum Status {
    DELIVERED,
    IN_SHIPPING,
    PREPARING;

    public static Status parseString(String status) {
        return switch (status) {
            case "DELIVERED" -> DELIVERED;
            case "IN SHIPPING" -> IN_SHIPPING;
            case "PREPARING" -> PREPARING;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case DELIVERED -> "DELIVERED";
            case IN_SHIPPING -> "IN SHIPPING";
            case PREPARING -> "PREPARING";
        };
    }
}
