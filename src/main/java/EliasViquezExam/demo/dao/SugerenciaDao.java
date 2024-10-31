package EliasViquezExam.demo.dao;

import EliasViquezExam.demo.domain.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugerenciaDao extends JpaRepository <Sugerencia,Long> {
    
}