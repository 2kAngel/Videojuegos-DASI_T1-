package es.upsa.dasi.videojuegos.exceptions;

public class DesarrolladoraNotFoundException extends EntityNotFoundException{

    public DesarrolladoraNotFoundException( String id) {
        super("No existe la desarrolladora con identificador " + id, id);
    }
}
