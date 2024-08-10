/*
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.guiUtil;

import java.io.Closeable;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.DateFormat;

import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.level.Leveled;



/**
 * The interface for a Logger object.
 * 
 * @author Eugene Hong
 * */
public interface Logger extends Leveled, Closeable {


	/**
	 * A pre-constructed no-opt logger object that just prints noting. 
	 * */
	public static final Logger nullLogger = new AbstractLogger() {
		@Override public void newLine() {}
		@Override public void close() {}
		@Override public void writeString(Level level, CharSequence str) {}
	}; 

	
	/**
	 * Set date information prefix for this {@code Logger} instance.
	 * if argument is {@code null}, no date information prefix is appended.
	 * Date prefix is always appended very first of the line.
	 * 
	 * @return this logger
	 * */
	public Logger setDatePrefix(DateFormat datePrefix);

	/**
	 * Set additional prefix for this {@code Logger} instance.
	 * if argument is {@code null}, no additional prefix is appended.
	 * The additional prefix is always appended after date prefix(if exists).
	 * 
	 * @return this logger
	 * 
	 * @see Logger#setDatePrefix(DateFormat)
	 * */
	public Logger setPrefix(String prefix);
	
	/**
	 * If parameter is true, level of each log will be printed as prefix.
	 * 
	 * @param flag
	 */
	public void setPrintLogLevel(boolean flag);
	
	/**
	 * Print a new line without printing any prefixes, regardless of level.
	 * */
	public void newLine();

	/**
	 * Log a String.
	 * @deprecated use logging with level like {@code Logger#info(CharSequence)}.
	 * */
	@Deprecated
	public void log(CharSequence data);
	/**
	 * Log a formatted String.
	 * @deprecated use logging with level like {@code Logger#info(CharSequence, Object...)}.
	 * */
	@Deprecated
	public void log(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable}.
	 * @deprecated use logging with level like {@code Logger#info(Throwable)}.
	 * */
	@Deprecated
	public void log(Throwable e);
	/**
	 * Log a array of <code>Object</code>s.
	 * @deprecated use logging with level like {@code Logger#info(CharSequence)}.
	 * */
	@Deprecated
	public void log(Object... objs);
	
	
	/**
	 * Log a empty line at INFO level.
	 * */
	public void info();
	/**
	 * Log a String at INFO level.
	 * */
	public void info(CharSequence data);
	/**
	 * Log a formatted String at INFO level.
	 * */
	public void info(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at INFO level.
	 * */
	public void info(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at INFO level.
	 * */
	public void info(CharSequence data, Throwable e);

	/**
	 * Log a empty line at DEBUG level.
	 * */
	public void debug();
	/**
	 * Log a String at DEBUG level.
	 * */
	public void debug(CharSequence data);
	/**
	 * Log a formatted String at DEBUG level.
	 * */
	public void debug(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at DEBUG level.
	 * */
	public void debug(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at DEBUG level.
	 * */
	public void debug(CharSequence data, Throwable e);

	/**
	 * Log a empty line at TRACE level.
	 * */
	public void trace();
	/**
	 * Log a String at TRACE level.
	 * */
	public void trace(CharSequence data);
	/**
	 * Log a formatted String at TRACE level.
	 * */
	public void trace(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at TRACE level.
	 * */
	public void trace(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at TRACE level.
	 * */
	public void trace(CharSequence data, Throwable e);

	/**
	 * Log a empty line at WARNING level.
	 * */
	public void warning();
	/**
	 * Log a String at WARNING level.
	 * */
	public void warning(CharSequence data);
	/**
	 * Log a formatted String at WARNING level.
	 * */
	public void warning(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at WARNING level.
	 * */
	public void warning(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at WARNING level.
	 * */
	public void warning(CharSequence data, Throwable e);

	/**
	 * Log a empty line at ERROR level.
	 * */
	public void error();
	/**
	 * Log a String at ERROR level.
	 * */
	public void error(CharSequence data);
	/**
	 * Log a formatted String at ERROR level.
	 * */
	public void error(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at ERROR level.
	 * */
	public void error(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at ERROR level.
	 * */
	public void error(CharSequence data, Throwable e);

	/**
	 * Log a empty line at FATAL level.
	 * */
	public void fatal();
	/**
	 * Log a String at FATAL level.
	 * */
	public void fatal(CharSequence data);
	/**
	 * Log a formatted String at FATAL level.
	 * */
	public void fatal(CharSequence format, Object...objs);
	/**
	 * Log a {@code Throwable} at FATAL level.
	 * */
	public void fatal(Throwable e);
	/**
	 * Log a {@code Throwable} with an accompanying message at FATAL level.
	 * */
	public void fatal(CharSequence data, Throwable e);
	
	/**
	 * Log a message at given level.
	 */
	public void logInLevel(Level level, CharSequence str);

	/***
	 * 
	 * All String written to returned Stream will logged as given level.
	 * @param level Every data written will logged in given level. It {@code null},
	 * 				current level of the {@code Logger} will used
	 * @param charset byte data written in returned Stream will encoded to given charset. 
	 * @return
	 */
	public PrintStream toPrintStream(Level level, boolean autoFlush, Charset charset);

	/**
	 * Generate a child logger that adds additional prefix.
	 * Returned logger is just a proxy logger that writes all output to its parent ({@code this}).
	 * with additional prefix.
	 * 
	 * @param morePrefix additional prefix that'll appended in output.
	 * @param closeChildIfParentClosed if {@code true}, the returned child logger will closed
	 * 									if the parent({@code this}) is closed.
	 * 
	 * @return new child logger with additional prefix
	 */
	public Logger withMorePrefix(String morePrefix, boolean closeChildIfParentClosed);
}
