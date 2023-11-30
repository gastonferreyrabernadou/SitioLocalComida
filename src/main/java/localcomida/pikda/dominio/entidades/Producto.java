package localcomida.pikda.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {
    
    @Min(1000)
    @Id
    private Long codigo;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String descripcion;

    @NotNull
    @ManyToOne(optional = false)
    private Categoria categoria;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Double precio;

    private boolean disponible;

    private boolean tieneImagen;


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean getTieneImagen() {
        return tieneImagen;
    }

    public void setTieneImagen(boolean tieneImagen) {
        this.tieneImagen = tieneImagen;
    }


    public Producto() {
        this(null, null, null, null, false, false);
    }

    public Producto(Long codigo, String descripcion, Categoria categoria, Double precio, boolean disponible, boolean tieneImagen) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.disponible = disponible;
        this.tieneImagen = tieneImagen;
    }
}
