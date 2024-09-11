package io.github.awidesky.guiUtil.simple;

import java.nio.charset.Charset;

import io.github.awidesky.guiUtil.AbstractLogger;
import io.github.awidesky.guiUtil.LoggerOutputStream;
import io.github.awidesky.guiUtil.level.Level;

/**
 * Logs to the console via {@code System.out}.
 */
public class ConsoleLogger extends AbstractLogger {

	private boolean autoFlush;

	/**
	 * Constructs a console logger with auto flush enabled.
	 */
	public ConsoleLogger() {
		this(true);
	}
	/**
	 * Constructs a console logger with given auto flush option.
	 */
	public ConsoleLogger(boolean autoFlush) {
		this.autoFlush = autoFlush;
	}

	@Override
	public void newLine() {
		System.out.println();
		if(autoFlush) System.out.flush();
	}
	@Override
	protected void writeString(Level level, CharSequence str) {
		System.out.println(prefix.format(level, prefixStr) + str);
	}
	
	/**
	 * Do noting because we shouldn't close {@code System.out}.
	 */
	@Override
	public void close() {
		System.out.flush();
	}

	@Override
	protected LoggerOutputStream getLoggerOutputStream(Level level, boolean autoFlush, Charset charset) {
		LoggerOutputStream ret = super.getLoggerOutputStream(level, autoFlush, charset);
		ret.setCloseExternalLogger(false);
		return ret;
	}
}
