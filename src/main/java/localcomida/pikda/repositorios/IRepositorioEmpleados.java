package localcomida.pikda.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.lang.Nullable;
import localcomida.pikda.dominio.entidades.Empleado;


public interface IRepositorioEmpleados extends JpaRepository<Empleado, String>, JpaSpecificationExecutor<Empleado> {
    
    // @Override
    // @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "roles", "legajo" })
    // List<Empleado> findAll();

    // @Override
    // @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "roles", "legajo" })
    // List<Empleado> findAll(@Nullable Specification<Empleado> spec);

    // @Override
    // @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "roles", "legajo" })
    // Optional<Empleado> findById(String id);
}
