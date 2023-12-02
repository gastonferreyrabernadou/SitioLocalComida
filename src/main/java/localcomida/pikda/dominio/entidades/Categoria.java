package localcomida.pikda.dominio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
    
    @Id
    @Min(1000)
    private Long codigo;

    @Column(nullable = false, length = 100)
    private String nombre;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Categoria() {
        this(null, null);
    }

    public Categoria(Long codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
