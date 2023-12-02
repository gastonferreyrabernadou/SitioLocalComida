package localcomida.pikda.dominio.entidades;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "empleados")
public class Empleado extends Usuario{
    
    @PastOrPresent
    private LocalDate fechaIngreso;

    @Min(0)
    private Double sueldo;

    @NotNull
    @Valid
    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Legajo legajo;

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public Legajo getLegajo() {
        return legajo;
    }

    public void setLegajo(Legajo legajo) {
        this.legajo = legajo;
    }

    public Empleado() {
        this(null,null,null);
    }

    public Empleado(LocalDate fechaIngreso, Double sueldo, Legajo legajo) {
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
        this.legajo = legajo;
    }

    @Entity
    @Table(name = "legajos")
    public static class Legajo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long numero;

        @NotBlank
        @Size(max=25)
        @Column(nullable = false, length = 25)
        private String nacionalidad;

        @NotBlank
        @Size(max = 10)
        @Column(nullable = false, length = 10)
        private int cedula;

        @NotBlank
        @Size(max = 30)
        @Column(nullable = false, length = 30)
        private String grupoSanguineo;

        @NotBlank
        @Size(max = 9)
        @Column(nullable = false, length = 9)
        private int telefonoEmergencia;

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

        @OneToOne(optional = false)
        private Empleado empleado;       

        public Long getNumero() {
            return numero;
        }


        public void setNumero(Long numero) {
            this.numero = numero;
        }


        public String getNacionalidad() {
            return nacionalidad;
        }


        public void setNacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
        }


        public int getCedula() {
            return cedula;
        }


        public void setCedula(int cedula) {
            this.cedula = cedula;
        }


        public String getGrupoSanguineo() {
            return grupoSanguineo;
        }


        public void setGrupoSanguineo(String grupoSanguineo) {
            this.grupoSanguineo = grupoSanguineo;
        }


        public int getTelefonoEmergencia() {
            return telefonoEmergencia;
        }


        public void setTelefonoEmergencia(int telefonoEmergencia) {
            this.telefonoEmergencia = telefonoEmergencia;
        }


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


        public Empleado getEmpleado() {
            return empleado;
        }


        public void setEmpleado(Empleado empleado) {
            this.empleado = empleado;
        }


        public Legajo() {
            this(0L, null, null, null, null, null, null, null, null, null, null);
        }


        public Legajo(Long numero, String contenido, String nacionalidad, int cedula, String grupoSanguineo,
                int telefonoEmergencia, String barrio, String direccionCalle, String direccionNumero,
                String direccionEsquina, String direccionComplemento, Empleado empleado) {
            this.numero = numero;
            this.nacionalidad = nacionalidad;
            this.cedula = cedula;
            this.grupoSanguineo = grupoSanguineo;
            this.telefonoEmergencia = telefonoEmergencia;
            this.barrio = barrio;
            this.direccionCalle = direccionCalle;
            this.direccionNumero = direccionNumero;
            this.direccionEsquina = direccionEsquina;
            this.direccionComplemento = direccionComplemento;
            this.empleado = empleado;
        }

        

    }

    

}
