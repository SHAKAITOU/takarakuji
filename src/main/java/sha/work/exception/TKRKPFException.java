package sha.work.exception;

public class TKRKPFException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public TKRKPFException() {
		super();
	}

	public TKRKPFException(String message) {
		super(message);
	}

	public TKRKPFException(String message, Throwable cause) {
        super(message, cause);
    }

	public TKRKPFException(Throwable cause) {
        super(cause);
    }

	 protected TKRKPFException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
		 super(message, cause, enableSuppression, writableStackTrace);
	 }

}
