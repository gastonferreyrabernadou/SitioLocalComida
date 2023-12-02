package localcomida.pikda.repositorios;

import localcomida.pikda.dominio.entidades.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.lang.Nullable;

public interface IRepositorioPedidos extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
    
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "cliente" })
    List<Pedido> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "cliente" })
    Page<Pedido> findAll(Pageable pageable);

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "cliente" })
    Page<Pedido> findAll(@Nullable Specification<Pedido> spec, Pageable pageable);

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "cliente" })
    Optional<Pedido> findById(Long id);

    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "cliente" })
    List<Pedido> findByCliente_NombreUsuario(String nombreUsuario);

    int deleteByCliente_NombreUsuario(String nombreUsuario);

}
