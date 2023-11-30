package localcomida.pikda.repositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import localcomida.pikda.dominio.entidades.Cliente;


public class IRepositorioClientes extends JpaRepository<Cliente, String> {
    // @Override
    // @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "roles" })
    // List<Cliente> findAll();

    // @Override
    // @EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "roles" })
    // Optional<Cliente> findById(String id);
}
