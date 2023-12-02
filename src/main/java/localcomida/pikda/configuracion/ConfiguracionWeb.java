package localcomida.pikda.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ConfiguracionWeb implements WebMvcConfigurer {
    
    //REVISAR: ruta incorrecta, debo reemplazar
    @Value("${lcr.ruta-imagenes-productos}")
    private String rutaImagenesProductos;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imagenes-productos/**").addResourceLocations("file:" + rutaImagenesProductos + "/");
    }
}
