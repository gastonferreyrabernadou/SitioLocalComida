package localcomida.pikda.repositorios.especificaciones;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import localcomida.pikda.dominio.entidades.Producto;


public class especificacionesProducto {
    
    public static Specification<Producto> textoCodigoIgualA(String textoCodigo) {
        if (textoCodigo == null) return null;

        return new Specification<Producto>() {

            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Long codigo;

                try {
                    codigo = Long.parseLong(textoCodigo);
                } catch (NumberFormatException e) {
                    return null;
                }

                return criteriaBuilder.equal(root.get("codigo"), codigo);
            }

        };
    }

    public static Specification<Producto> descripcionContiene(String descripcion) {
        if (descripcion == null) return null;

        return new Specification<Producto>() {

            @Override
            public Predicate toPredicate(Root<Producto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("descripcion"), "%" + descripcion +  "%");
            }

        };
    }

    public static Specification<Producto> buscar(String criterio) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.where(textoCodigoIgualA(criterio)).or(descripcionContiene(criterio));
    }

}
