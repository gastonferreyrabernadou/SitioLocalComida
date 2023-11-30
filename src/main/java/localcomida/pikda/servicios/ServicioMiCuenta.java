package localcomida.pikda.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import localcomida.pikda.dominio.entidades.Rol;
import localcomida.pikda.dominio.entidades.Cliente;
import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.dominio.excepciones.excepcionPIKDA;
import localcomida.pikda.dominio.excepciones.excepcionNoExiste;
import localcomida.pikda.dominio.excepciones.excepcionExiste;
import localcomida.pikda.dominio.repositorios.IRepositorioClientes;
import localcomida.pikda.dominio.repositorios.IRepositorioPedidos;

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
            throws ExcepcionLocalComidaRapida {
        cliente.setActivo(true);

        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente != null) {
            throw new ExcepcionYaExiste("El cliente ya existe.");
        }

        cliente.getRoles().add(new Rol("cliente"));

        repositorioClientes.save(cliente);
    }

    @Override
    public void modificarCliente(Cliente cliente)
            throws ExcepcionLocalComidaRapida {
        cliente.setActivo(true);

        Cliente clienteExistente = repositorioClientes.findById(cliente.getNombreUsuario()).orElse(null);

        if (clienteExistente == null) {
            throw new ExcepcionNoExiste("El cliente no existe.");
        }

        cliente.getRoles().clear();

        for (Rol r : clienteExistente.getRoles()) {
            cliente.getRoles().add(r);
        }

        repositorioClientes.save(cliente);
    }

    @Override
    //@Transactional
    public void eliminarCliente(String nombreUsuario)
            throws ExcepcionLocalComidaRapida {
        Cliente clienteExistente = repositorioClientes.findById(nombreUsuario).orElse(null);

        if (clienteExistente == null) {
            throw new ExcepcionNoExiste("El cliente no existe.");
        }

        List<Pedido> pedidos = repositorioPedidos.findByCliente_NombreUsuario(nombreUsuario);

        /*if (pedidos.size() > 0) {
            repositorioPedidos.deleteByCliente_NombreUsuario(nombreUsuario);
        }

        repositorioClientes.deleteById(nombreUsuario);*/

        if (pedidos.size() == 0) {
            repositorioClientes.deleteById(nombreUsuario);
        } else {
            clienteExistente.setActivo(false);

            repositorioClientes.save(clienteExistente);
        }
    }
}
