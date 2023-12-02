package localcomida.pikda.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import localcomida.pikda.dominio.entidades.Rol;
import localcomida.pikda.dominio.entidades.Cliente;
import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.excepciones.excepcionPIKDA;
import localcomida.pikda.excepciones.excepcionNoExiste;
import localcomida.pikda.excepciones.excepcionExiste;
import localcomida.pikda.repositorios.IRepositorioClientes;
import localcomida.pikda.repositorios.IRepositorioPedidos;

public class ServicioMiCuenta implements IServicioMiCuenta {
    @Autowired
    IRepositorioClientes repositorioClientes;

    @Autowired
    IRepositorioPedidos repositorioPedidos;


    @Override
    public Cliente obtenerCliente(String nombreUsuario) {
        return repositorioClientes.findById(nombreUsuario).orElse(null);
    }

    @Override
    public void registrarCliente(Cliente cliente)
            throws excepcionPIKDA {
        cliente.setActivo(true);

        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente != null) {
            throw new excepcionExiste("El cliente ya existe.");
        }

        cliente.getRoles().add(new Rol("cliente"));

        repositorioClientes.save(cliente);
    }

    @Override
    public void modificarCliente(Cliente cliente)
            throws excepcionPIKDA {
        cliente.setActivo(true);

        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente == null) {
            throw new excepcionNoExiste("El cliente no existe.");
        }

        cliente.getRoles().clear();

        for (Rol r : clienteExistente.getRoles()) {
            cliente.getRoles().add(r);
        }

        repositorioClientes.save(cliente);
    }

    @Override
    public void eliminarCliente(String nombreUsuario)
            throws excepcionPIKDA {
        Cliente clienteExistente = repositorioClientes.findById(nombreUsuario).orElse(null);

        if (clienteExistente == null) {
            throw new excepcionExiste("El cliente no existe.");
        }

        List<Pedido> pedidos = repositorioPedidos.findByCliente_NombreUsuario(nombreUsuario);

        if (pedidos.size() == 0) {
            repositorioClientes.deleteById(nombreUsuario);
        } else {
            clienteExistente.setActivo(false);

            repositorioClientes.save(clienteExistente);
        }
    }
}
