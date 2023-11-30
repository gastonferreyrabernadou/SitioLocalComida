package localcomida.pikda.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.dominio.excepciones.excepcionPIKDA;

public interface IServicioPedidos {
    
    Page<Pedido> buscar(String criterio, int filtroDespacho, int filtroFechaHora, Pageable pageable);
    Pedido obtener(Long numero);
    void despachar(Long numero) throws excepcionPIKDA;
    void eliminar(Long numero) throws excepcionPIKDA;

}
