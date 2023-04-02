package es.upsa.dasi.videojuegos.exceptions;

public class EntityNotFoundException extends VideojuegoException{

    private String id;

    public EntityNotFoundException(String message, String id)
    {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
