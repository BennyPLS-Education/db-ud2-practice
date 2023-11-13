package orm;

import static utils.Inputs.getNumber;

public enum CustomerType {
    PARTICULAR,
    SMALL_BUSINESS,
    BUSINESS;
    
    public static CustomerType customerTypeSelector() {
        System.out.println("1. Particular");
        System.out.println("2. Small business");
        System.out.println("3. Business");
        CustomerType selected;
        while (true) {
            selected = switch (getNumber("Select a customer type: ")) {
                case 1 -> CustomerType.PARTICULAR;
                case 2 -> CustomerType.SMALL_BUSINESS;
                case 3 -> CustomerType.BUSINESS;
                default -> null;
            };
            
            if (selected != null) break;
            
            System.out.println("Not a valid option");
        }
        
        return selected;
    }
}
