package localcomida.pikda.repositorios;

import localcomida.pikda.dominio.entidades.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.lang.Nullable;

public interface IRepositorioProductos extends JpaRepository<Producto, Long>, JpaSpecificationExecutor<Producto> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "categoria" })
    List<Producto> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "categoria" })
    List<Producto> findAll(@Nullable Specification<Producto> spec);

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "categoria" })
    Optional<Producto> findById(Long id);

    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "categoria" })
    List<Producto> findByDisponibleTrueOrderByCategoria_Nombre();

    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "categoria" })
    List<Producto> findByDisponibleTrueAndCategoria_Codigo(Long codigoCategoria);

}
