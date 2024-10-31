package EliasViquezExam.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="sugerencia")
public class Sugerencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String nombre;
    private String sugerencia;
    
    
    public Sugerencia() {
    }

    public Sugerencia(String nombre, String sugerencia) {
        this.nombre = nombre;
        this.sugerencia = sugerencia;

    }
}