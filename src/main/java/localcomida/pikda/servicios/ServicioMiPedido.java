package localcomida.pikda.servicios;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import localcomida.pikda.dominio.entidades.Pedido;
import localcomida.pikda.dominio.excepciones.excepcionPIKDA;
import localcomida.pikda.dominio.excepciones.excepcionNoExiste;
import localcomida.pikda.repositorios.IRepositorioClientes;
import localcomida.pikda.repositorios.IRepositorioLineasPedidos;
import localcomida.pikda.repositorios.IRepositorioPedidos;
import localcomida.pikda.repositorios.IRepositorioProductos;

public class ServicioMiPedido implements IServicioMiPedido{
    
    @Autowired
    private IRepositorioProductos repositorioProductos;

    @Autowired
    private IRepositorioClientes repositorioClientes;

    @Autowired
    private IRepositorioPedidos repositorioPedidos;

    @Autowired
    private IRepositorioLineasPedido repositorioLineasPedido;

    private Pedido pedido;


    @Override
    public Pedido getPedido() {
        return pedido;
    }


    public ServicioMiPedido() {
        pedido = new Pedido();
    }


    @Override
    public void anadirUnidadProducto(Long codigo) {
        List<Pedido.LineaPedido> lineas = pedido.getLineas();

        boolean productoEncontrado = false;

        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).getProducto().getCodigo() == codigo) {
                lineas.get(i).setCantidad(lineas.get(i).getCantidad() + 1);
                lineas.get(i).setImporte(lineas.get(i).getCantidad() * lineas.get(i).getProducto().getPrecio());
                productoEncontrado = true;

                break;
            }
        }

        if (!productoEncontrado) {
            Producto producto = repositorioProductos.findById(codigo).orElse(null);

            if (producto != null) {
                pedido.getLineas().add(new Pedido.LineaPedido(1, producto, producto.getPrecio()));
            }
        }

        actualizarTotalPedido();
    }

    @Override
    public void quitarUnidadProducto(Long codigo) {
        List<Pedido.LineaPedido> lineas = pedido.getLineas();

        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).getProducto().getCodigo() == codigo) {
                if (lineas.get(i).getCantidad() > 1) {
                    lineas.get(i).setCantidad(lineas.get(i).getCantidad() - 1);
                    lineas.get(i).setImporte(lineas.get(i).getCantidad() * lineas.get(i).getProducto().getPrecio());
                } else {
                    lineas.remove(i);
                }

                break;
            }
        }

        actualizarTotalPedido();
    }

    @Override
    public void quitarProducto(Long codigo) {
        List<Pedido.LineaPedido> lineas = pedido.getLineas();

        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).getProducto().getCodigo() == codigo) {
                lineas.remove(i);

                break;
            }
        }

        actualizarTotalPedido();
    }

    @Override
    public void vaciarPedido() {
        pedido = new Pedido();
    }

    @Override
    @Transactional
    public void realizar(String nombreUsuarioCliente)
            throws ExcepcionLocalComidaRapida {
        if (pedido.getLineas().size() == 0) {
            throw new ExcepcionLocalComidaRapida("El pedido no tiene líneas.");
        }

        pedido.setFechaHora(LocalDateTime.now());

        Cliente cliente = repositorioClientes.findById(nombreUsuarioCliente).orElseThrow(() -> new ExcepcionNoExiste("El cliente no existe."));
        pedido.setCliente(cliente);

        Pedido pedidoGuardado = repositorioPedidos.save(pedido);

        List<Pedido.LineaPedido> lineas = pedido.getLineas();

        for (int i = 0; i < lineas.size(); i++) {
            lineas.get(i).setClave(new Pedido.ClaveLineaPedido(i + 1, pedidoGuardado));

            repositorioLineasPedido.save(lineas.get(i));
        }

        pedido = new Pedido();
    }

    private void actualizarTotalPedido() {
        List<Pedido.LineaPedido> lineas = pedido.getLineas();

        Double total = 0D;

        for (int i = 0; i < lineas.size(); i++) {
            total += lineas.get(i).getImporte();
        }

        pedido.setTotal(total);
    }
}
