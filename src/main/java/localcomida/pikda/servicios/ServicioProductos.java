package localcomida.pikda.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import localcomida.pikda.dominio.entidades.Producto;
import localcomida.pikda.excepciones.excepcionPIKDA;
import localcomida.pikda.excepciones.excepcionNoExiste;
import localcomida.pikda.excepciones.excepcionExiste;
import localcomida.pikda.excepciones.excepcionTieneVinculo;
import localcomida.pikda.repositorios.IRepositorioProductos;
import localcomida.pikda.repositorios.especificaciones.especificacionesProductos;


public class ServicioProductos implements IServicioProductos{
    
    @Autowired
    private IRepositorioProductos repositorioProductos;


    @Override
    public List<Producto> listarDisponibles() {
        return repositorioProductos.findByDisponibleTrueOrderByCategoria_Nombre();
    }

    @Override
    public List<Producto> listarDisponiblesXCategoria(Long codigoCategoria) {
        return repositorioProductos.findByDisponibleTrueAndCategoria_Codigo(codigoCategoria);
    }

    @Override
    public List<Producto> buscar(String criterio) {
        return repositorioProductos.findAll(especificacionesProductos.buscar(criterio));
    }

    @Override
    public Producto obtener(Long codigo) {
        return repositorioProductos.findById(codigo).orElse(null);
    }

    @Override
    public void agregar(Producto producto)
            throws excepcionPIKDA {
        Producto productoExistente = repositorioProductos.findById(producto.getCodigo()).orElse(null);

        if (productoExistente != null) {
            throw new excepcionExiste("El producto ya existe.");
        }

        repositorioProductos.save(producto);
    }

    @Override
    public void modificar(Producto producto)
            throws excepcionPIKDA {
        Producto productoExistente = repositorioProductos.findById(producto.getCodigo()).orElse(null);

        if (productoExistente == null) {
            throw new excepcionNoExiste("El producto no existe.");
        }

        if (!producto.getTieneImagen()) {
            producto.setTieneImagen(productoExistente.getTieneImagen());
        }

        repositorioProductos.save(producto);
    }

    @Override
    public void eliminar(Long codigo)
            throws excepcionPIKDA {
        Producto productoExistente = repositorioProductos.findById(codigo).orElse(null);

        if (productoExistente == null) {
            throw new excepcionNoExiste("El producto no existe.");
        }

        try {
            repositorioProductos.deleteById(codigo);
        } catch (DataIntegrityViolationException e) {
            throw new excepcionTieneVinculo("El producto tiene pedidos.");
        }
    }

}
