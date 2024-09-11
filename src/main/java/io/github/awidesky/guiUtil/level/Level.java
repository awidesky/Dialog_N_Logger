package io.github.awidesky.guiUtil.level;

public enum Level {
	FATAL,
	ERROR,
	WARNING,
	INFO,
	DEBUG,
	TRACE;
	
	private static Level rootLogLevel = INFO;

	/***
	 * Get root log level.
	 * Newly generated loggers will use this level as their log level.
	 */
	public static Level getRootLogLevel() {
		return rootLogLevel;
	}

	/***
	 * Get root log level.
	 * Newly generated loggers will use given level as their log level.
	 */
	public static void setRootLogLevel(Level rootLevel) {
		Level.rootLogLevel = rootLevel;
	}
	
	/***
	 * Check if this log level includes the given log level.
	 * If returned value is {@code true}, it means a {@code Logger}
	 * instance whose log level is set to {@code this} will print
	 * a log leveled as {@code other}.
	 * Whereas if {@code false}, the {@code Logger} instance will omit
	 * a log leveled as {@code other}.
	 * 
	 * @param other {@code Level} instance to be checked
	 * @return {@code true} if {@code this} includes {@code other} Else {@code false}
	 */
	public boolean includes(Level other) {
		return other.ordinal() <= ordinal(); 
	}
}
