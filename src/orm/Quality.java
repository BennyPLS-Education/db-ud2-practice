package orm;

import static utils.Inputs.getNumber;

public enum Quality {
    A,
    B,
    C,
    D,
    E;
    
    public static Quality qualitySelector() {
        System.out.println("1. A");
        System.out.println("2. B");
        System.out.println("3. C");
        System.out.println("4. D");
        System.out.println("5. E");
        Quality selected;
        while (true) {
            selected = switch (getNumber("Select a quality: ")) {
                case 1 -> Quality.A;
                case 2 -> Quality.B;
                case 3 -> Quality.C;
                case 4 -> Quality.D;
                case 5 -> Quality.E;
                default -> null;
            };
            
            if (selected != null) break;
            
            System.out.println("Not a valid option");
        }
        
        return selected;
    }
}
