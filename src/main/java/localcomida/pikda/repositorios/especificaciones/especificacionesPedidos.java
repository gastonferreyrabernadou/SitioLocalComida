package localcomida.pikda.repositorios.especificaciones;

import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import javax.persistence.criteria.JoinType;
import localcomida.pikda.dominio.entidades.Pedido;

public class especificacionesPedidos {
    
    public static Specification<Pedido> textoNumeroIgualA(String textoNumero) {
        if (textoNumero == null) return null;

        return (root, query, criteriaBuilder) -> {
            Long numero;

            try {
                numero = Long.parseLong(textoNumero);
            } catch (NumberFormatException e) {
                return null;
            }

            return criteriaBuilder.equal(root.get("numero"), numero);
        };
    }

    public static Specification<Pedido> textoFechaIgualA(String textoFecha) {
        if (textoFecha == null) return null;

        return (root, query, criteriaBuilder) -> {
            LocalDate fecha;
            String fechaISO;

            try {
                fecha = LocalDate.parse(textoFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                fechaISO = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                return null;
            }

            return criteriaBuilder.like(root.get("fechaHora").as(String.class), "%" + fechaISO + "%");
        };
    }

    public static Specification<Pedido> deLasUltimasHoras(int horas) {
        return (root, query, criteriaBuilder) -> {
            LocalDateTime fechaHora = LocalDateTime.now().minusHours(horas);

            return criteriaBuilder.greaterThan(root.get("fechaHora"), fechaHora);
        };
    }


    // ERROR: el join<string,jointype> lo reemplacé por join<object, object>


    // public static Specification<Pedido> nombreUsuarioClienteContiene(String nombreUsuario) {
    //     if (nombreUsuario == null) return null;

    //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente", JoinType.LEFT).get("nombreUsuario"), "%" + nombreUsuario + "%");
    // }

    // public static Specification<Pedido> nombreClienteContiene(String nombre) {
    //     if (nombre == null) return null;

    //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente", JoinType.LEFT).get("nombre"), "%" + nombre + "%");
    // }

    // public static Specification<Pedido> apellidoClienteContiene(String apellido) {
    //     if (apellido == null) return null;

    //     return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente", JoinType.LEFT).get("apellido"), "%" + apellido + "%");
    // }

    public static Specification<Pedido> nombreUsuarioClienteContiene(String nombreUsuario) {
        if (nombreUsuario == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente").get("nombreUsuario"), "%" + nombreUsuario + "%");
    }

    public static Specification<Pedido> nombreClienteContiene(String nombre) {
        if (nombre == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente").get("nombre"), "%" + nombre + "%");
    }

    public static Specification<Pedido> apellidoClienteContiene(String apellido) {
        if (apellido == null) return null;

        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.join("cliente").get("apellido"), "%" + apellido + "%");
    }

    public static Specification<Pedido> despachado() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("despachado"));
    }

    public static Specification<Pedido> noDespachado() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("despachado"));
    }

    public static Specification<Pedido> buscarYFiltrar(String criterio, int filtroDespacho, int filtroFechaHora) {
        Specification<Pedido> especificacion = Specification.where(null);

        if (criterio != null && !criterio.isBlank()) {
            especificacion = especificacion.and(textoNumeroIgualA(criterio)
            .or(textoFechaIgualA(criterio))
            .or(nombreUsuarioClienteContiene(criterio))
            .or(nombreClienteContiene(criterio))
            .or(apellidoClienteContiene(criterio)));
        }

        if (filtroDespacho == 1) {
            especificacion = especificacion.and(noDespachado());
        } else if (filtroDespacho == 2) {
            especificacion = especificacion.and(despachado());
        }

        if (filtroFechaHora == 1) {
            especificacion = especificacion.and(deLasUltimasHoras(24));
        }

        return especificacion;
    }

}
