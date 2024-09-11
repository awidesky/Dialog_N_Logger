package io.github.awidesky.guiUtil.simple;

import io.github.awidesky.guiUtil.level.Level;

/**
 * Writes log into system standard out({@code stdout}, {@code stderr}).
 * If the log level is above {@code Level#WARNING}, it'll logged to {@code System#err}
 */
public class SystemLogger extends ConsoleLogger {

	/**
	 * Constructs a system logger with given auto flush option.
	 */
	public SystemLogger(boolean autoFlush) {
		super(autoFlush);
	}
	
	@Override
	protected void writeString(Level level, CharSequence str) {
		if(Level.WARNING.includes(level))
			System.err.println(prefix.format(level, prefixStr) + str);
		else 
			System.out.println(prefix.format(level, prefixStr) + str);
	}

	@Override
	public void close() {
		System.err.flush();
		System.out.flush();
	}

}
