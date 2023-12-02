package localcomida.pikda.dominio.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @NotBlank
    @Size(max=25)
    @Id
    @Column(length = 25)
    private String nombreUsuario;

    @NotBlank
    @Size(max=25)
    @Column(nullable = false, length = 25)
    private String contrasena;

    @Transient
    private String repetirContrasena;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String nombre;

    @NotBlank
    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String apellido;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(nullable = false, length = 50)
    private String correoElectronico;

    @NotBlank
    @Size(max = 9)
    @Column(nullable = false, length = 9)
    private int telefono;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    private boolean activo;

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "usuario_nombre_usuario") }, inverseJoinColumns = { @JoinColumn(name = "rol_nombre_rol") })
    private Set<Rol> roles;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRepetirContrasena() {
        return repetirContrasena;
    }

    public void setRepetirContrasena(String repetirContrasena) {
        this.repetirContrasena = repetirContrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<Rol> getRoles() {
        return this.roles;
    }

    public Usuario() {
        this(null, null, null, null, null, null, null, null);
    }

    public Usuario(String nombreUsuario, String contrasena, String nombre, String apellido,
            String correoElectronico, int telefono, LocalDate fechaNacimiento, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;

        roles = new HashSet<>();
    }
    
}
