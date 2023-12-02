package localcomida.pikda.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import localcomida.pikda.dominio.entidades.Rol;
import localcomida.pikda.dominio.entidades.Empleado;
import localcomida.pikda.excepciones.excepcionNoExiste;
import localcomida.pikda.excepciones.excepcionPIKDA;
import localcomida.pikda.excepciones.excepcionExiste;
import localcomida.pikda.repositorios.especificaciones.especificacionesEmpleado;
import localcomida.pikda.repositorios.IRepositorioEmpleados;


public class ServicioEmpleados implements IServicioEmpleados {
    @Autowired
    IRepositorioEmpleados repositorioEmpleados;


    @Override
    @Transactional
    public List<Empleado> buscar(String criterio) {
        return repositorioEmpleados.findAll(especificacionesEmpleado.buscar(criterio));
    }

    @Override
    public Empleado obtener(String nombreUsuario) {
        return repositorioEmpleados.findById(nombreUsuario).orElse(null);
    }

    @Override
    public void agregar(Empleado empleado)
            throws excepcionPIKDA {
        empleado.setActivo(true);

        Empleado empleadoExistente = repositorioEmpleados.findById(empleado.getNombreUsuario()).orElse(null);

        if (empleadoExistente != null) {
            throw new excepcionExiste("El empleado ya existe.");
        }

        empleado.getRoles().add(new Rol("empleado"));

        empleado.getLegajo().setEmpleado(empleado);

        repositorioEmpleados.save(empleado);
    }

    @Override
    public void modificar(Empleado empleado)
            throws excepcionPIKDA {
        empleado.setActivo(true);

        Empleado empleadoExistente = repositorioEmpleados.findById(empleado.getNombreUsuario()).orElse(null);

        if (empleadoExistente == null) {
            throw new excepcionNoExiste("El empleado no existe.");
        }

        empleado.getRoles().clear();

        for (Rol r : empleadoExistente.getRoles()) {
            empleado.getRoles().add(r);
        }

        empleado.getLegajo().setNumero(empleadoExistente.getLegajo().getNumero());
        empleado.getLegajo().setEmpleado(empleado);

        repositorioEmpleados.save(empleado);
    }

    @Override
    public void eliminar(String nombreUsuario)
            throws excepcionPIKDA {
        Empleado empleadoExistente = repositorioEmpleados.findById(nombreUsuario).orElse(null);

        if (empleadoExistente == null) {
            throw new excepcionExiste("El empleado no existe.");
        }

        repositorioEmpleados.deleteById(nombreUsuario);
    }
}
