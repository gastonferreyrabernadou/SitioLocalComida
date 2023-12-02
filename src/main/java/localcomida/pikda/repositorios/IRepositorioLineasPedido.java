package localcomida.pikda.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import localcomida.pikda.dominio.entidades.Pedido;

public interface IRepositorioLineasPedido extends JpaRepository<Pedido.LineaPedido, Pedido.ClaveLineaPedido>{
 
}
