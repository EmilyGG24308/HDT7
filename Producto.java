import.java.util.Map;

//Producto en el inventario

class Producto{
  String name;
  String sku;
  String description;
  Map<String, Integer> sizes;

  public Producto(String sku, String name, String description, Map<String, Integer> sizes) {
      this.sku = sku;
      this.name = name;
      this.description = description;
      this.sizes = sizes;
  }

  public void updateDescription(String newDescription) {
      this.description = newDescription;
  }

  public void updateSizes(Map<String, Integer> newSizes) {
      this.sizes = newSizes;
  }
}

  
  
