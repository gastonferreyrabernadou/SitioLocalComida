package localcomida.pikda.repositorio.especificaciones;

import org.springframework.data.jpa.domain.Specification;
import localcomida.pikda.dominio.entidades.Empleado;

public class especificacionesEmpleado {

    // public static Specification<Empleado> nombreUsuarioIgualA(String nombreUsuario) {
    //     if (nombreUsuario == null) return null;

    //     return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nombreUsuario"), nombreUsuario);
    // }

    // public static Specification<Empleado> nombreCompletoContiene(String nombreCompleto) {
    //     if (nombreCompleto == null) return null;

    //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nombreCompleto"), "%" + nombreCompleto +  "%");
    // }

    // public static Specification<Empleado> buscar(String criterio) {
    //     if (criterio == null || criterio.isBlank()) return null;

    //     return Specification.where(nombreUsuarioIgualA(criterio)).or(nombreCompletoContiene(criterio));
    // }
}
