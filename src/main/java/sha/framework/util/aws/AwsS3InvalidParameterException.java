package sha.framework.util.aws;

/**
 * Thrown to indicate that a method has been passed an illegal or inappropriate argument.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
public class AwsS3InvalidParameterException extends Exception {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    public AwsS3InvalidParameterException(String message) {
        super(message);
    }
}
