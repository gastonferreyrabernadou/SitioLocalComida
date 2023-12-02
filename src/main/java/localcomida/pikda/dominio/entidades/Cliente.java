package localcomida.pikda.dominio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario{

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String barrio;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String direccionCalle;

    @NotBlank
    @Size(max = 6)
    @Column(nullable = false, length = 6)
    private String direccionNumero;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String direccionEsquina;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String direccionComplemento;

    private boolean recibirPromociones;

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(String direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public String getDireccionEsquina() {
        return direccionEsquina;
    }

    public void setDireccionEsquina(String direccionEsquina) {
        this.direccionEsquina = direccionEsquina;
    }

    public String getDireccionComplemento() {
        return direccionComplemento;
    }

    public void setDireccionComplemento(String direccionComplemento) {
        this.direccionComplemento = direccionComplemento;
    }

    public boolean isRecibirPromociones() {
        return recibirPromociones;
    }

    public void setRecibirPromociones(boolean recibirPromociones) {
        this.recibirPromociones = recibirPromociones;
    }

    public Cliente(String barrio, String direccionCalle, String direccionNumero, String direccionEsquina, String direccionComplemento,
            boolean recibirPromociones) {
        this.barrio = barrio;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.direccionEsquina = direccionEsquina;
        this.direccionComplemento = direccionComplemento;
        this.recibirPromociones = recibirPromociones;
    }

    public Cliente() {
        this(null, null, null, null, null,  null);

    }


}
