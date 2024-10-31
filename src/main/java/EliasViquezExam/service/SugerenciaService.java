package EliasViquezExam.service;

import EliasViquezExam.demo.domain.Sugerencia;
import java.util.List;

public interface SugerenciaService {
    
   // Se obtiene un listado de arboles en un List
    public List<Sugerencia> getSugerencias();
    
    // Get a Arbol, based on the ID of a category
    public Sugerencia getSugerencia(Sugerencia sugerencia);

    // Insert a new Arbol if the category ID is empty
    // Update a Arbol if the category ID is NOT empty
    public void save(Sugerencia sugerencia);

    // Delete the Arbol that has the ID passed as a parameter
    public void delete(Sugerencia sugerencia);
    
}