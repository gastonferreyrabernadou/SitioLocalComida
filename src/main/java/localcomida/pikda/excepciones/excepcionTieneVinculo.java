package localcomida.pikda.excepciones;

public class excepcionTieneVinculo extends excepcionPIKDA{
    
    public excepcionTieneVinculo() {

    }

    public excepcionTieneVinculo(String mensaje) {
        super(mensaje);
    }

    public excepcionTieneVinculo(String mensaje, Exception excepcionInterna) {
        super(mensaje, excepcionInterna);
    }

}
