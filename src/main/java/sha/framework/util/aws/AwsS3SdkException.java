package sha.framework.util.aws;

/**
 * Thrown to indicate that an error was thrown by AWS SDK.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
public class AwsS3SdkException extends Exception {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    public AwsS3SdkException(String message, Throwable cause) {
        super(message, cause);
    }
}
