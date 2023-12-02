package localcomida.pikda.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import localcomida.pikda.dominio.entidades.Categoria;
import localcomida.pikda.repositorios.IRepositorioCategorias;

public class ServicioCategorias implements IServicioCategorias{
    
    @Autowired
    IRepositorioCategorias repositorioCategorias;


    @Override
    public List<Categoria> listar() {
        return repositorioCategorias.findAll(Sort.by("nombre"));
    }

    @Override
    public Categoria obtener(Long codigo) {
        return repositorioCategorias.findById(codigo).orElse(null);
    }

}
