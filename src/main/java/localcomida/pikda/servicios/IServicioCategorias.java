package localcomida.pikda.servicios;

import java.util.List;
import localcomida.pikda.dominio.entidades.Categoria;

public interface IServicioCategorias {
    
    List<Categoria> listar();
    Categoria obtener(Long codigo);

}
