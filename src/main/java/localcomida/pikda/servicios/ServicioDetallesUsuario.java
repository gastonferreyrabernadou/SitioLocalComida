package localcomida.pikda.servicios;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import localcomida.pikda.dominio.entidades.Rol;
import localcomida.pikda.dominio.entidades.Usuario;
import localcomida.pikda.repositorios.IRepositorioClientes;
import localcomida.pikda.repositorios.IRepositorioEmpleados;


public class ServicioDetallesUsuario implements UserDetailsService{
    
    @Autowired
    IRepositorioEmpleados repositorioEmpleados;

    @Autowired
    IRepositorioClientes repositorioClientes;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Usuario usuario = repositorioEmpleados.findById(username).orElse(null);

        if (usuario == null) {
            usuario = repositorioClientes.findById(username).orElse(null);
        }

        if (usuario == null || !usuario.isActivo()) throw new UsernameNotFoundException("El usuario no existe.");

        List<GrantedAuthority> roles = new ArrayList<>();

        for (Rol r : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
        }

        return new User(usuario.getNombreUsuario(), usuario.getContrasena(), usuario.isActivo(), true, true, true, roles);
    }
}
