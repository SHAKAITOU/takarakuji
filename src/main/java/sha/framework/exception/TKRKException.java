package sha.framework.exception;

public class TKRKException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public TKRKException() {
		super();
	}

	public TKRKException(String message) {
		super(message);
	}

	public TKRKException(String message, Throwable cause) {
        super(message, cause);
    }

	public TKRKException(Throwable cause) {
        super(cause);
    }

	 protected TKRKException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
		 super(message, cause, enableSuppression, writableStackTrace);
	 }

}
