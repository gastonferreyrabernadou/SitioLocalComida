package localcomida.pikda.dominio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @Column(length = 25)
    private String nombreRol;


    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }


    public Rol() {
        this(null);
    }

    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
