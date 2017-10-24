package sha.work.exception;

public class TKRKAPIException extends TKRKPFException {


	private static final long serialVersionUID = 1L;

	public TKRKAPIException() {
		super();
	}

	public TKRKAPIException(String message) {
		super(message);
	}

	public TKRKAPIException(String message, Throwable cause) {
        super(message, cause);
    }

	public TKRKAPIException(Throwable cause) {
        super(cause);
    }

	 protected TKRKAPIException(String message, Throwable cause,
             boolean enableSuppression,
             boolean writableStackTrace) {
		 super(message, cause, enableSuppression, writableStackTrace);
	 }

}
