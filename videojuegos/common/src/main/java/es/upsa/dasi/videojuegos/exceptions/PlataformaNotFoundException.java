package es.upsa.dasi.videojuegos.exceptions;

public class PlataformaNotFoundException extends EntityNotFoundException{

    public PlataformaNotFoundException(String message, String id) {
        super("No existe la plataforma con identificador " + id, id);
    }
}
