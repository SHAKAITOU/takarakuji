package sha.framework.util.aws;

/**
 * Checked exception thrown when an attempt is made to upload a file of that name already exists.
 * 
 * @author Fast Retailing
 * @version $Revision$
 */
public class AwsS3FileAlreadyExistsException extends Exception {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    public AwsS3FileAlreadyExistsException(String message) {
        super(message);
    }
}
