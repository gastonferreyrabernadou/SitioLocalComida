package localcomida.pikda.configuracion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //REVISAR: Agregar un nuevo rol para manejo de personal

    /*@Bean
    public UserDetailsService authenticationManager(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(
            User.withUsername("gerente")
            .password(passwordEncoder.encode("gerente123"))
            .authorities("empleado", "gerente").build());

        manager.createUser(
            User.withUsername("empleado")
            .password(passwordEncoder.encode("empleado123"))
            .authorities("empleado").build());

        manager.createUser(
                User.withUsername("cliente")
                .password(passwordEncoder.encode("cliente123"))
                .authorities("cliente").build());

        return manager;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
            /*.csrf().disable()*/
            .authorizeRequests()
            .antMatchers("/productos/agregar", "/productos/modificar", "/productos/eliminar").hasAuthority("gerente")
            .antMatchers("/productos/**").hasAnyAuthority("empleado", "gerente")
            .antMatchers("/empleados/**").hasAuthority("gerente")
            .antMatchers("/pedidos/**").hasAnyAuthority("empleado", "gerente")
            .antMatchers("/").permitAll()
            .antMatchers("/mipedido/realizar").hasAuthority("cliente")
            .antMatchers("/mipedido/**").access("isAnonymous() or hasAuthority('cliente')")
            .antMatchers("/micuenta/registrarcliente").anonymous()
            .antMatchers("/micuenta/**").hasAuthority("cliente")
            .anyRequest().authenticated()
            .and().formLogin()
                .loginPage("/login").permitAll()
                .successHandler(new SimpleUrlAuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {
                        FlashMap flashMap = new FlashMap();
                        flashMap.put("mensaje", "Has ingresado a tu cuenta.");

                        FlashMapManager flashMapManager = new SessionFlashMapManager();
                        flashMapManager.saveOutputFlashMap(flashMap, request, response);

                        super.onAuthenticationSuccess(request, response, authentication);
                    }

                })
            .and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler() {

                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {
                        FlashMap flashMap = new FlashMap();
                        flashMap.put("mensaje", "Has salido de tu cuenta.");

                        FlashMapManager flashMapManager = new SessionFlashMapManager();
                        flashMapManager.saveOutputFlashMap(flashMap, request, response);

                        super.onLogoutSuccess(request, response, authentication);
                    }

                });

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .antMatchers("/css/**", "/imagenes/**", "/imagenes-productos/**");
    }

}

