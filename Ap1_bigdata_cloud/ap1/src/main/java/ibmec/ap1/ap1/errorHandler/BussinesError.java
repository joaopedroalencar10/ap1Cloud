package ibmec.ap1.ap1.errorHandler;

public class BussinesError {

    private String typeError = "BusinessException";

    public String message;

    public BussinesError(String typeError, String message) {
        this.typeError = typeError;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTypeError() {
        return typeError;
    }

}