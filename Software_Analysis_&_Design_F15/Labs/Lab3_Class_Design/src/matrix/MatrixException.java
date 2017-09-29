package matrix;

/**
 * A MatrixException should be thrown when attempting to add or multiply 
 * matrices of incorrect dimensions.
 * @author tcolburn
 */
public class MatrixException extends RuntimeException {
    
    /**
     * Creates a MatrixException object with the indicated reason.
     * @param reason the reason why this MatrixException is being thrown.
     */
    public MatrixException(String reason) {
        super(reason);
    }
    
}
