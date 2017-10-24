package sha.work.exception;

public class TKRKScreenException extends TKRKPFException {


	private static final long serialVersionUID = 1L;

	public TKRKScreenException() {
		super();
	}

	public TKRKScreenException(String message) {
		super(message);
	}

	public TKRKScreenException(String message, Throwable cause) {
        super(message, cause);
    }

	public TKRKScreenException(Throwable cause) {
        super(cause);
    }

	protected TKRKScreenException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
