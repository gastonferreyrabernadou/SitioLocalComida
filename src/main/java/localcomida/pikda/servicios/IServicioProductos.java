package localcomida.pikda.servicios;

import java.util.List;
import localcomida.pikda.dominio.entidades.Producto;
import localcomida.pikda.excepciones.excepcionPIKDA;

public interface IServicioProductos {
    
    List<Producto> listarDisponibles();
    List<Producto> listarDisponiblesXCategoria(Long codigoCategoria);
    List<Producto> buscar(String criterio);
    Producto obtener(Long codigo);
    void agregar(Producto producto) throws excepcionPIKDA;
    void modificar(Producto producto) throws excepcionPIKDA;
    void eliminar(Long codigo) throws excepcionPIKDA;


}
