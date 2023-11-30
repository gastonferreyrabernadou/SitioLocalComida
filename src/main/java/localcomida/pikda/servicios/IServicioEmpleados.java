package localcomida.pikda.servicios;

import java.util.List;
import localcomida.pikda.dominio.entidades.Empleado;
import localcomida.pikda.dominio.excepciones.excepcionPIKDA;

public interface IServicioEmpleados {

    List<Empleado> buscar(String criterio);
    Empleado obtener(String nombreUsuario);
    void agregar(Empleado empleado) throws excepcionPIKDA;
    void modificar(Empleado empleado) throws excepcionPIKDA;
    void eliminar(String nombreUsuario) throws excepcionPIKDA;

}
