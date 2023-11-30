package localcomida.pikda.dominio;

public class excepcionPIKDA extends Exception {
    
    public excepcionPIKDA() {

    }

    public excepcionPIKDA(String mensaje) {
        super(mensaje);
    }

    public excepcionPIKDA(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}
