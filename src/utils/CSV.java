package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class CSV {
    
    public static <T> void export(List<T> dataClass, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {

            Class<?> recordClass = dataClass.get(0).getClass();
            Field[] fields = recordClass.getDeclaredFields();
            
            for (int i = 0; i < fields.length; i++) {
                writer.append(fields[i].getName());
                
                if (i != fields.length - 1) writer.append(',');
            }
            writer.append('\n');
            
            for (int j = 0, dataClassSize = dataClass.size(); j < dataClassSize; j++) {
                T record = dataClass.get(j);
                
                System.out.printf("Exporting {%s}\n", record);
                for (int i = 0, fieldsLength = fields.length; i < fieldsLength; i++) {
                    fields[i].setAccessible(true);
                    writer.append(String.valueOf(fields[i].get(record)));
                    
                    if (i != fieldsLength - 1) writer.append(',');
                }
                
                if (j != dataClassSize - 1) writer.append('\n');
            }
            
            System.out.println("CSV file exported successfully!");
        } catch (IOException | IllegalAccessException e) {
            System.out.println("Error: Could not write to file");
        }
    }
}
