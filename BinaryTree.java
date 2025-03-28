// Uses Binary para almacenar y buscar (nombre o SKU)
class BinaryTree<K extends Comparable<K>, V> {
    public class Node {  
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    public void insert(K key, V value) {
        root = insertRecursive(root, key, value);
    }

    private Node insertRecursive(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = insertRecursive(node.left, key, value);
        else if (cmp > 0) node.right = insertRecursive(node.right, key, value);
        else node.value = value;
        
        return node;
    }

    public V search(K key) {
        return searchRecursive(root, key);
    }

    private V searchRecursive(Node node, K key) {
        if (node == null) return null;
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return searchRecursive(node.left, key);
        else if (cmp > 0) return searchRecursive(node.right, key);
        else return node.value;
    }

    // Método público para obtener la raíz
    public Node getRoot() {
        return root;
    }
}


