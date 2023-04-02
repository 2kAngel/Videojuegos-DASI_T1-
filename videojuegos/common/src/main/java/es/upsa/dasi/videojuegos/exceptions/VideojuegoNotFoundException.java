package es.upsa.dasi.videojuegos.exceptions;

public class VideojuegoNotFoundException extends EntityNotFoundException{

    public VideojuegoNotFoundException(String message, String id) {
        super("No existe el videojuego con identificador por " + id, id);
    }
}
