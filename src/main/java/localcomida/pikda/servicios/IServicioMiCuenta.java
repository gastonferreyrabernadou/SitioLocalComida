package localcomida.pikda.servicios;

import localcomida.pikda.dominio.entidades.Cliente;
import localcomida.pikda.excepciones.excepcionPIKDA;

public interface IServicioMiCuenta {

    Cliente obtenerCliente(String nombreUsuario);
    void registrarCliente(Cliente cliente) throws excepcionPIKDA;
    void modificarCliente(Cliente cliente) throws excepcionPIKDA;
    void eliminarCliente(String nombreUsuario) throws excepcionPIKDA;

}
