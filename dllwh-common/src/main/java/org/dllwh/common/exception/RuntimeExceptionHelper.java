package org.dllwh.common.exception;

public class RuntimeExceptionHelper extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RuntimeExceptionHelper(Throwable e) {
		super(e.getMessage(), e);
	}

	public RuntimeExceptionHelper(String message) {
		super(message);
	}

	public RuntimeExceptionHelper(String messageTemplate, Object... params) {
		super(String.format(messageTemplate, params));
	}

	public RuntimeExceptionHelper(String message, Throwable throwable) {
		super(message, throwable);
	}

	public RuntimeExceptionHelper(Throwable throwable, String messageTemplate, Object... params) {
		super(String.format(messageTemplate, params), throwable);
	}
}