import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class ProductoTest {
    private Producto producto;

    @BeforeEach
    void setUp() {
        Map<String, Integer> sizes = new HashMap<>();
        sizes.put("m", 10);
        sizes.put("l", 5);
        producto = new Producto("01", "Camiseta", "Camiseta de algodón", sizes);
    }

    @Test
    void testCrearProducto() {
        assertEquals("01", producto.sku);
        assertEquals("Camiseta", producto.name);
        assertEquals("Camiseta deportiva", producto.description);
        assertEquals(10, producto.sizes.get("m"));
        assertEquals(5, producto.sizes.get("l"));
    }

    @Test
    void testActualizarDescripcion() {
        producto.updateDescription("Nueva descripción");
        assertEquals("Nueva descripción", producto.description);
    }

    @Test
    void testActualizarTallas() {
        Map<String, Integer> newSizes = new HashMap<>();
        newSizes.put("s", 8);
        newSizes.put("xl", 3);
        producto.updateSizes(newSizes);
        assertEquals(8, producto.sizes.get("s"));
        assertEquals(3, producto.sizes.get("xl"));
        assertNull(producto.sizes.get("m")); 
    }
}