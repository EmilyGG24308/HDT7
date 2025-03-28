import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class InventarioTest {
    private Inventario inventario;
    private Producto producto;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        Map<String, Integer> sizes = new HashMap<>();
        sizes.put("M", 10);
        sizes.put("L", 5);
        producto = new Producto("12345", "Camiseta", "Camiseta de algodón", sizes);
        inventario.addProducto(producto);
    }

    @Test
    void testBuscarProductoPorSku() {
        Producto result = inventario.searchBySku("12345");
        assertNotNull(result);
        assertEquals("Camiseta", result.name);
    }

    @Test
    void testBuscarProductoPorNombre() {
        Producto result = inventario.searchByName("Camiseta");
        assertNotNull(result);
        assertEquals("12345", result.sku);
    }

    @Test
    void testModificarCantidadDeTalla() {
        assertTrue(inventario.editProducto("12345", "Nueva descripción", Map.of("M", 15, "L", 8)));
        Producto result = inventario.searchBySku("12345");
        assertEquals("Nueva descripción", result.description);
        assertEquals(15, result.sizes.get("M"));
        assertEquals(8, result.sizes.get("L"));
    }

    @Test
    void testAgregarNuevoProducto() {
        Map<String, Integer> sizes = new HashMap<>();
        sizes.put("S", 8);
        Producto nuevoProducto = new Producto("67890", "Pantalón", "Pantalón de mezclilla", sizes);
        inventario.addProducto(nuevoProducto);
        assertNotNull(inventario.searchBySku("67890"));
    }

    @Test
    void testListProductosPorSku() {
        // Simulate listing by SKU
        inventario.listProductosPorSku(); 
    }

    @Test
    void testListProductosPorNombre() {
        // Simulate listing by name
        inventario.listProductosPorNombre(); 
    }
}
