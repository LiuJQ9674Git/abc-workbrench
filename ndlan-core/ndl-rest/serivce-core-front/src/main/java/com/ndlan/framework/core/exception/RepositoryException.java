package com.ndlan.framework.core.exception;

/**
 * Dao层异常信息
 * @author LiuJQ
 */
public class RepositoryException extends RuntimeException {

	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = 8350049272861703406L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

}
