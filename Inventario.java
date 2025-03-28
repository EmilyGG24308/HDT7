import java.util.Map;

//Administra los productos (using BT)

class Inventario {
    private BinaryTree<String, Producto> skuTree = new BinaryTree<>();
    private BinaryTree<String, Producto> nameTree = new BinaryTree<>();

    // Agrega un producto a cada arbol
    public void addProducto(Producto producto) {
        skuTree.insert(producto.sku, producto);
        nameTree.insert(producto.name.toLowerCase(), producto);
    }

    // Search con SKU
    public Producto searchBySku(String sku) {
        return skuTree.search(sku);
    }

    // Search con Name
    public Producto searchByName(String name) {
        return nameTree.search(name.toLowerCase());
    }

    // Edits productos
    public boolean editProducto(String sku, String newDescription, Map<String, Integer> newSizes) {
        Producto producto = searchBySku(sku);
        if (producto != null) {
            producto.description = newDescription;
            producto.sizes = newSizes;
            return true;
        }
        return false;
    }

    public void listProductosPorSku() {
        inOrderTraversal(skuTree.getRoot());
    }
    
    public void listProductosPorNombre() {
        inOrderTraversal(nameTree.getRoot());
    }
    
    private void inOrderTraversal(BinaryTree<String, Producto>.Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            Producto p = node.value;
            System.out.println("SKU: " + p.sku + " | Nombre: " + p.name + " | Descripción: " + p.description + " | Tallas: " + p.sizes);
            inOrderTraversal(node.right);
        }
    }
      
}



