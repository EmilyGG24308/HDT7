import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Carga los productos desde el csv 

class CSVLoader{
  public static void loadInventario(String filename, Inventario inventario) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length < 3) continue;
            
            String sku = parts[0].trim();
            String name = parts[1].trim();
            String description = parts[2].trim();
            Map<String, Integer> sizes = new HashMap<>();
            
            if (parts.length > 3) {
                for (String sizeEntry : parts[3].split("\\|")) {
                    String[] sizeData = sizeEntry.split(":");
                    if (sizeData.length == 2) {
                        sizes.put(sizeData[0].trim(), Integer.parseInt(sizeData[1].trim()));
                    }
                }
            }
            
            inventario.addProducto(new Producto(sku, name, description, sizes));
        }
        br.close();
    }
}

  
