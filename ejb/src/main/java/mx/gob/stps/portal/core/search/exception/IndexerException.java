package mx.gob.stps.portal.core.search.exception;

import javax.ejb.ApplicationException;

/**
 * Lanzada cuando ocurre un error durante la indexacion de registros
 * @author oscar.manzo
 *
 */
@ApplicationException(rollback=true)
public class IndexerException extends Exception {
	private static final long serialVersionUID = 1947033259712515976L;
	public IndexerException() {super();}
    public IndexerException(final String message) {super(message);}
    public IndexerException(final Throwable cause) {super(cause);}
    public IndexerException(final String message, final Throwable cause) {super(message, cause);}
}
