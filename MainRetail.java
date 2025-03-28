import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Interacts with the user (main menu)

public class MainRetail {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Scanner scanner = new Scanner(System.in);

        // Cargar datos desde CSV
        System.out.print("Ingrese el nombre del archivo CSV: ");
        String filename = scanner.nextLine();
        
        try {
            CSVLoader.loadInventario(filename, inventario);
        } catch (IOException e) {
            System.err.println("Error al cargar el inventario del archivo: " + e.getMessage());
        }

        int option;
        do {
            System.out.println("\n---- Bienvenidos!! ----");            
            System.out.println("\n--- Menú de Inventario ---");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Buscar producto por nombre");
            System.out.println("3. Listar productos por SKU");
            System.out.println("4. Listar productos por nombre");
            System.out.println("5. Modificar cantidad de una talla específica");
            System.out.println("6. Añadir un nuevo producto");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Ingrese SKU que desea buscar: ");
                    String sku = scanner.nextLine();
                    Producto p1 = inventario.searchBySku(sku);
                    if (p1 != null) {
                        System.out.println("Producto encontrado: " + p1.name + "\nDescripción: " + p1.description);
                        System.out.println("Tallas disponibles: " + p1.sizes);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese nombre del producto que se desea buscar: ");
                    String name = scanner.nextLine();
                    Producto p2 = inventario.searchByName(name);
                    if (p2 != null) {
                        System.out.println("Producto encontrado: " + p2.name + "\nDescripción: " + p2.description);
                        System.out.println("Tallas disponibles: " + p2.sizes);
                    } else {
                        System.out.println("Producto no encontrado en la sistema");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Listado de productos por SKU ---");
                    inventario.listProductosPorSku();
                    break;

                case 4:
                    System.out.println("\n--- Listado de productos por Nombre ---");
                    inventario.listProductosPorNombre();
                    break;

                case 5:
                    System.out.print("Ingrese SKU del producto: ");
                    String skuEdit = scanner.nextLine();
                    Producto producto = inventario.searchBySku(skuEdit);
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.name);
                        System.out.println("Tallas disponibles: " + producto.sizes);
                        System.out.print("Ingrese la talla a modificar (ej. s, m, l...): ");
                        String talla = scanner.nextLine();
                        if (producto.sizes.containsKey(talla)) {
                            System.out.print("Ingrese la nueva cantidad para la talla " + talla + ": ");
                            int nuevaCantidad = scanner.nextInt();
                            scanner.nextLine(); 
                            producto.sizes.put(talla, nuevaCantidad);
                            System.out.println("Cantidad actualizada correctamente.");
                        } else {
                            System.out.println("Talla no encontrada.");
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 6:
                    System.out.print("Ingrese SKU del nuevo producto: ");
                    String newSku = scanner.nextLine();
                    System.out.print("Ingrese nombre del producto: ");
                    String newName = scanner.nextLine();
                    System.out.print("Ingrese descripción del producto: ");
                    String newDesc = scanner.nextLine();

                    Map<String, Integer> newSizes = new HashMap<>();
                    String size;
                    do {
                        System.out.print("Ingrese una talla (o 'fin' para terminar): ");
                        size = scanner.nextLine();
                        if (!size.equalsIgnoreCase("fin")) {
                            System.out.print("Ingrese cantidad para la talla " + size + ": ");
                            int cantidad = scanner.nextInt();
                            scanner.nextLine(); 
                            newSizes.put(size, cantidad);
                        }
                    } while (!size.equalsIgnoreCase("fin"));

                    Producto nuevoProducto = new Producto(newSku, newName, newDesc, newSizes);
                    inventario.addProducto(nuevoProducto);
                    System.out.println("Producto añadido exitosamente!");
                    break;

                case 7:
                    System.out.println("Hasta Pronto !! ...");
                    break;

                default:
                    System.out.println("Opción inválida. Ingrese un valor valido.");
            }
        } while (option != 7);

        scanner.close();
    }
}

