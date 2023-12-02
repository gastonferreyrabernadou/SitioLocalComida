package localcomida.pikda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.excepciones.excepcionPIKDA;
import localcomida.pikda.excepciones.excepcionNoExiste;
import localcomida.pikda.excepciones.excepcionExiste;
import localcomida.pikda.repositorios.IRepositorioClientes;
import localcomida.pikda.repositorios.IRepositorioPedidos;
import localcomida.pikda.repositorios.especificaciones.especificacionesPedidos;


public class ServicioPedidos implements IServicioPedidos {
    @Autowired
    private IRepositorioPedidos repositorioPedidos;


    @Override
    public Page<Pedido> buscar(String criterio, int filtroDespacho, int filtroFechaHora, Pageable pageable) {
        return repositorioPedidos.findAll(especificacionesPedidos.buscarYFiltrar(criterio, filtroDespacho, filtroFechaHora), pageable);
    }

    @Override
    public Pedido obtener(Long numero) {
        Pedido pedido = repositorioPedidos.findById(numero).orElse(null);

        return pedido;
    }

    @Override
    public void despachar(Long numero)
            throws excepcionPIKDA {
        Pedido pedidoExistente = repositorioPedidos.findById(numero).orElse(null);

        if (pedidoExistente == null) {
            throw new excepcionNoExiste("El pedido no existe.");
        }

        pedidoExistente.setDespachado(true);

        repositorioPedidos.save(pedidoExistente);
    }

    @Override
    public void eliminar(Long numero)
            throws excepcionPIKDA {
        Pedido pedidoExistente = repositorioPedidos.findById(numero).orElse(null);

        if (pedidoExistente == null) {
            throw new excepcionNoExiste("El pedido no existe.");
        }

        repositorioPedidos.deleteById(numero);
    }
}
