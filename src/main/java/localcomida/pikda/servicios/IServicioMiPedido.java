package localcomida.pikda.servicios;

import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.excepciones.excepcionPIKDA;

public interface IServicioMiPedido {
    
    Pedido getPedido();
    void anadirUnidadProducto(Long codigo);
    void quitarUnidadProducto(Long codigo);
    void quitarProducto(Long codigo);
    void vaciarPedido();
    void realizar(String nombreUsuarioCliente) throws excepcionPIKDA;

}
