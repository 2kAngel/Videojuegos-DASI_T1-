package es.upsa.dasi.videojuegos.exceptions;

public class VideojuegoNotFoundException extends EntityNotFoundException{

    public VideojuegoNotFoundException(String id) {
        super("No existe el videojuego con identificador " + id, id);
    }
}
