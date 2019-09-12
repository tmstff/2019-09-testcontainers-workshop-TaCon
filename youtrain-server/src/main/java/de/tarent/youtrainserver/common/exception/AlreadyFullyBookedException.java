package de.tarent.youtrainserver.common.exception;

/**
 * Custom exception thrown when a booking request is made but no free places are left.
 *
 * TODO: It's better to respond with a 400 - Bad Request
 *
 * @author Mark Vz
 */
public class AlreadyFullyBookedException extends RuntimeException {
    public AlreadyFullyBookedException(final String msg) {
        super(msg);
    }
}
