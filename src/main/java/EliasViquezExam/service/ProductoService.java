package EliasViquezExam.service;

import EliasViquezExam.demo.domain.Producto;
import java.util.List;

public interface ProductoService {
    
   // Se obtiene un listado de arboles en un List
    public List<Producto> getProductos();
    
    // Get a Arbol, based on the ID of a category
    public Producto getProducto(Producto producto);

    // Insert a new Arbol if the category ID is empty
    // Update a Arbol if the category ID is NOT empty
    public void save(Producto producto);

    // Delete the Arbol that has the ID passed as a parameter
    public void delete(Producto producto);
    
}