package localcomida.pikda.excepciones;

public class excepcionNoExiste extends excepcionPIKDA{
    
    public excepcionNoExiste() {

    }

    public excepcionNoExiste(String mensaje) {
        super(mensaje);
    }

    public excepcionNoExiste(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}
