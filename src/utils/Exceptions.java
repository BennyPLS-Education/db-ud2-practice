package utils;

import java.sql.SQLException;

public class Exceptions {
    public static void manageError(SQLException e) {
        int errCode = e.getErrorCode();
        
        switch (errCode) {
            case 1062 -> {
                final String val = getValueBetween(e.getMessage(), '\'');
                System.out.println("Error: Empleat " + val + " duplicat");
            }
            
            case 1048 -> {
                final String val = getValueBetween(e.getMessage(), '\'');
                System.out.println("Error: El camp " + val + " no pot ser null");
            }
            
            case 1452 -> {
                var firstParenIndex = e.getMessage().indexOf('(');
                var secondParenIndexRelative = e.getMessage().substring(++firstParenIndex).indexOf('(');
                var identifierName = getValueBetween(e.getMessage()
                    .substring(secondParenIndexRelative + firstParenIndex), '`');
                System.out.println("Error: L'identificador de " + identifierName + " no existix");
            }
            
            case 1451 -> {
                var firstParenIndex = e.getMessage().indexOf('(');
                var secondParenIndexRelative = e.getMessage().substring(++firstParenIndex).indexOf('(');
                var identifierName = getValueBetween(e.getMessage()
                    .substring(secondParenIndexRelative + firstParenIndex), '`');
                System.out.println("Error: L'identificador de " + identifierName + " esta en usw");
            }
            
            case 1264 -> {
                final String val = getValueBetween(e.getMessage(), '\'');
                System.out.println("Error: El camp " + val + " esta fora de rang");
            }
            case 45000 -> System.out.println("Error: " + e.getMessage());
            
            default -> {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Error Code: " + errCode);
            }
        }
    }
    
    private static String getValueBetween(String message, char ch) {
        var valStart = message.indexOf(ch);
        var valEnd = message.indexOf(ch, ++valStart);
        
        return message.substring(valStart, valEnd);
    }
}
