package excepcion;

public class ErrorEnResultado extends RuntimeException{
    private String mensajeError;

    public ErrorEnResultado(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    @Override
    public String getMessage() {
        return this.mensajeError;
    }
}
