/*
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.guiUtil.simple;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import io.github.awidesky.guiUtil.AbstractLogger;
import io.github.awidesky.guiUtil.level.Level;

/**
 * A Simple Logger class that prints log to given {@code OutputStream}.
 * 
 * @author Eugene Hong
 * */
public class SimpleLogger extends AbstractLogger {
	
	private PrintWriter logTo;
	private boolean close = true;
	
	/**
	 * Creates a logger with given {@code OutputStream}.
	 * Logged {@code String}s are encoded with {@code Charset#defaultCharset()}, and auto flushed
	 * into the {@code OutputStream}.
	 * */
	public SimpleLogger(OutputStream os) {
		this(os, true, Charset.defaultCharset());
	}
	
	/**
	 * Creates a logger with given {@code OutputStream} and {@code Charset}.
	 * Logged {@code String}s are auto flushed into the {@code OutputStream}.  
	 * */
	public SimpleLogger(OutputStream os, Charset cs) {
		this(os, true, cs);
	}
	
	/**
	 * Creates a logger with given {@code OutputStream}.
	 * Logged {@code String}s are encoded with {@code Charset#defaultCharset()}
	 * */
	public SimpleLogger(OutputStream os, boolean autoFlush) {
		this(os, autoFlush, Charset.defaultCharset());
	}
	
	/**
	 * Creates a logger with given {@code OutputStream} and {@code Charset}.
	 * */
	public SimpleLogger(OutputStream os, boolean autoFlush, Charset cs) {
		this(new OutputStreamWriter(os, cs), autoFlush);
		close = !(os == System.out || os == System.err); 
	}

	/**
	 * Creates a logger with given {@code Writer}.
	 * */
	public SimpleLogger(Writer wt, boolean autoFlush) {
		logTo = new PrintWriter(wt, autoFlush);
	}
	
	/**
	 * Logs an empty new line without prefix
	 * */
	@Override
	public void newLine() {
		logTo.println();
	}


	/**
	 * Closes this logger and releases any system resources associated with it.
	 * If the logger is already closed then invoking this method has no effect.
	 * */
	@Override
	public void close() {
		logTo.flush();
		if(close) logTo.close();
	}


	@Override
	public void writeString(Level level, CharSequence str) {
		logTo.println(prefix.format(level, prefixStr) + str);
	}

}
