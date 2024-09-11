/*
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.guiUtil;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;

import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.prefix.PrefixFormatter;
import io.github.awidesky.guiUtil.prefix.SimplePrefixFormatter;


/**
 * An Abstract class for a {@code Logger} class with default implementations of general methods.
 * 
 * @author Eugene Hong
 * */
public abstract class AbstractLogger implements Logger {

	protected String prefixStr = null;
	protected PrefixFormatter prefix = new SimplePrefixFormatter();
	protected Level level = Level.getRootLogLevel();
	
	@Override
	public Logger setPrefixFormatter(PrefixFormatter prefix) {
		this.prefix = prefix;
		return this;
	}
	
	@Override
	public PrefixFormatter getPrefixFormatter() {
		return prefix;
	}

	@Override
	public String getPrefixString() {
		return prefixStr;
	}
	@Override
	public void setPrefixString(String prefixString) {
		this.prefixStr = prefixString;
	}
	
	/**
	 * Logs a String.
	 * <p>This method calls {@code Logger#info(CharSequence)} 
	 * @deprecated use logging with level like {@code Logger#info(CharSequence)}.
	 * */
	@Override
	@Deprecated
	public void log(CharSequence data) {
		info(data);
	}
	
	/**
	 * Logs a formatted String.
	 * <p>This method calls {@code Logger#info(CharSequence)}
	 * @deprecated use logging with level like {@code Logger#info(CharSequence, Object...)}.
	 * */
	@Override
	@Deprecated
	public void log(CharSequence format, Object... objs) {
		log(String.format(format.toString(), objs));
		
	}
	
	/**
	 * Logs an <code>Throwable</code>.
	 * <p>This method calls {@code Logger#info(CharSequence)}
	 * @deprecated use logging with level like {@code Logger#info(Throwable)}.
	 * */
	@Override
	@Deprecated
	public void log(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		log(sw.toString());
	}
	
	/**
	 * Logs an array of <code>Object</code>s.
	 * <p>This method calls {@code Logger#info(CharSequence)}
	 * @deprecated use logging with level like {@code Logger#info(CharSequence)}.
	 * */
	@Override
	@Deprecated
	public void log(Object... objs) {
		log(Arrays.stream(objs).map(Object::toString).collect(Collectors.joining("\n")));
	}

	
	@Override
	public void info() {
		logInLevel(Level.INFO, "");
	}

	@Override
	public void info(CharSequence data) {
		logInLevel(Level.INFO, data);
	}

	@Override
	public void info(CharSequence format, Object... objs) {
		logInLevel(Level.INFO, String.format(format.toString(), objs));
	}

	@Override
	public void info(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.INFO, sw.toString());
	}

	@Override
	public void info(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.INFO, data + sw.toString());
	}
	
	@Override
	public void debug() {
		logInLevel(Level.DEBUG, "");
	}
	
	@Override
	public void debug(CharSequence data) {
		logInLevel(Level.DEBUG, data);
	}

	@Override
	public void debug(CharSequence format, Object... objs) {
		logInLevel(Level.DEBUG, String.format(format.toString(), objs));
	}

	@Override
	public void debug(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.DEBUG, sw.toString());
	}

	@Override
	public void debug(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.DEBUG, data + sw.toString());
	}
	
	@Override
	public void trace() {
		logInLevel(Level.TRACE, "");
	}

	@Override
	public void trace(CharSequence data) {
		logInLevel(Level.TRACE, data);
	}

	@Override
	public void trace(CharSequence format, Object... objs) {
		logInLevel(Level.TRACE, String.format(format.toString(), objs));
	}

	@Override
	public void trace(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.TRACE, sw.toString());
	}
	
	@Override
	public void trace(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.TRACE, data + sw.toString());
	}
	
	@Override
	public void warning() {
		logInLevel(Level.WARNING, "");
	}

	@Override
	public void warning(CharSequence data) {
		logInLevel(Level.WARNING, data);
	}

	@Override
	public void warning(CharSequence format, Object... objs) {
		logInLevel(Level.WARNING, String.format(format.toString(), objs));
	}

	@Override
	public void warning(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.WARNING, sw.toString());
	}
	
	@Override
	public void warning(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.WARNING, data + sw.toString());
	}
	
	@Override
	public void error() {
		logInLevel(Level.ERROR, "");
	}

	@Override
	public void error(CharSequence data) {
		logInLevel(Level.ERROR, data);
	}

	@Override
	public void error(CharSequence format, Object... objs) {
		logInLevel(Level.ERROR, String.format(format.toString(), objs));
	}
	
	@Override
	public void error(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.ERROR, sw.toString());
	}
	
	@Override
	public void error(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.ERROR, data + sw.toString());
	}
	
	@Override
	public void fatal() {
		logInLevel(Level.FATAL, "");
	}

	@Override
	public void fatal(CharSequence data) {
		logInLevel(Level.FATAL, data);
	}

	@Override
	public void fatal(CharSequence format, Object... objs) {
		logInLevel(Level.FATAL, String.format(format.toString(), objs));
	}

	@Override
	public void fatal(Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.FATAL, sw.toString());
	}
	
	@Override
	public void fatal(CharSequence data, Throwable e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logInLevel(Level.FATAL, data + sw.toString());
	}
	
	@Override
	public boolean isInfoEnabled() {
		return level.compareTo(Level.INFO) >= 0;
	}

	@Override
	public boolean isDebugEnabled() {
		return level.compareTo(Level.DEBUG) >= 0;
	}

	@Override
	public boolean isTraceEnabled() {
		return level.compareTo(Level.TRACE) >= 0;
	}

	@Override
	public boolean isWarningEnabled() {
		return level.compareTo(Level.WARNING) >= 0;
	}

	@Override
	public boolean isErrorEnabled() {
		return level.compareTo(Level.ERROR) >= 0;
	}

	@Override
	public boolean isFatalEnabled() {
		return level.compareTo(Level.FATAL) >= 0;
	}

	@Override
	public void logInLevel(Level level, CharSequence str) {
		if(this.level.includes(level)) writeString(level, str);
	}
	
	@Override
	public void setLogLevel(Level level) {
		this.level = level;
	}

	@Override
	public Level getLogLevel() {
		return level;
	}
	
	/**
	 * Actually write the log with given level and string data.
	 * Implementations typically evaluate final string by<br>
	 * {@code prefix.format(level, prefixStr) + str}<br>
	 * and write to external logging destination.
	 * 
	 * @param level the log level
	 * @param str the string data to log
	 */
	protected abstract void writeString(Level level, CharSequence str);
	
	
	@Override
	public Logger withMorePrefix(String morePrefix, boolean closeParentIfChildClosed) {
		final AbstractLogger parent = this;
		return new AbstractLogger() {
			private final String addedPrefix = morePrefix;
			
			@Override
			public void close() throws IOException {
				if(closeParentIfChildClosed) parent.close();
			}
			
			@Override
			public void newLine() {
				parent.newLine();
			}
			
			@Override
			protected void writeString(Level level, CharSequence str) {
				parent.writeString(level, addedPrefix + str);
			}
		};
	}

	@Override
	public PrintStream toPrintStream(Level level, boolean autoFlush, Charset charset) {
		return new PrintStream(getLoggerOutputStream(level, autoFlush, charset), autoFlush, charset);
	}

	protected LoggerOutputStream getLoggerOutputStream(Level level, boolean autoFlush, Charset charset) {
		return new LoggerOutputStream(this, level == null ? this.level : level, charset);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [" + "level=" + level + ", prefixPattern=" + prefix + "]";
	}
	
}
