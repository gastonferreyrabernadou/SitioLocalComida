package localcomida.pikda.excepciones;

public class excepcionExiste extends excepcionPIKDA {
    
    public excepcionExiste() {

    }

    public excepcionExiste(String mensaje) {
        super(mensaje);
    }

    public excepcionExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }
}
