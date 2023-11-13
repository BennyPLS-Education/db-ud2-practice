package orm;

import static utils.Inputs.getNumber;

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
    
    public static Status statusSelector() {
        System.out.println("1. Preparing");
        System.out.println("2. In shipping");
        System.out.println("3. Delivered");
        Status selected;
        while (true) {
            selected = switch (getNumber("Select a status: ")) {
                case 1 -> Status.PREPARING;
                case 2 -> Status.IN_SHIPPING;
                case 3 -> Status.DELIVERED;
                default -> null;
            };
            
            if (selected != null) break;
            
            System.out.println("Not a valid option");
        }
        
        return selected;
    }
    
}
