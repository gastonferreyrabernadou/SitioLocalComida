package localcomida.pikda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import localcomida.pikda.dominio.entidades.Categoria;

public interface IRepositorioCategorias extends JpaRepository<Categoria, Long> {
    
}
