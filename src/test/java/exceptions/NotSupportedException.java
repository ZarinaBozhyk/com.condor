package exceptions;

/**
 * #Summary:
 * #Author: Zarina_Bozhyk
 * #Author’s Email:
 * #Creation Date: 5/13/2023
 * #Comments:
 */
public class NotSupportedException extends RuntimeException {
    public NotSupportedException(String msg) {
        super(msg);
    }
}