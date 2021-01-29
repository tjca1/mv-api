package br.com.mv.log;

public class Logger {

	private static final String MSG = "msg=";
	private org.slf4j.Logger log;

	public Logger(org.slf4j.Logger logger) {
		this.log = logger;
	}

	public static final Logger getLogger(Class<?> clazz) {
		return new Logger(org.slf4j.LoggerFactory.getLogger(clazz));
	}

	public void info(String msg) {
		log.info(MSG + msg);
	}

	public void info(String msg, Object request) {
		log.info(msg + "={}", request);
	}

	public void info(String format, Object... arguments) {
		log.info(format + "={}", arguments);
	}

	public void info(String msg, Exception exception) {
		log.info(msg + "={}", exception);
	}

	public void warn(String msg) {
		log.warn(MSG + msg);
	}

	public void warn(String msg, Object object) {
		log.warn(msg, "={}", object);
	}

	public void error(String msg) {
		log.error(MSG + msg);
	}

	public void error(String msg, Exception exception) {
		log.error(MSG + msg + ". stack=", exception);
	}

	public void error(String format, Object... arguments) {
		log.error(MSG + format, "stack=" + arguments);
	}

	public void error(Exception exception) {
		log.error("stack=", exception);
	}

	public void debug(Object message) {
		log.debug(message + " {}", "");
	}

	public void debug(String message, Exception exception) {
		log.debug(message, exception);
	}

}
