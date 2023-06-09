package springbootjpaprac.springbootjpaprac.exception;

public class NotEnoughStockException extends RuntimeException {     //모든 함수 override 해줘야함.

    public NotEnoughStockException(){}

    public NotEnoughStockException(String message){
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}
