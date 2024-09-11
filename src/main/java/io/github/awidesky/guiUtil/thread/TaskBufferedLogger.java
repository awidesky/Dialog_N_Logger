/*
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.guiUtil.thread;

import java.io.Flushable;
import java.io.StringWriter;

import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.prefix.PrefixFormatter;

/**
 * A {@code TaskLogger} that buffer all logs to {@code StringWriter} and does not actually prints it
 * before {@code TaskBufferedLogger#flush()} is called.
 * {@code TaskBufferedLogger#runLogTask(Consumer)} is not supported; this class is for buffered operation only.
 * */
public abstract class TaskBufferedLogger extends TaskLogger implements Flushable {

	private StringWriter buffer = new StringWriter();

	/**
	 * Creates a task based buffered logger.
	 * */
	TaskBufferedLogger(PrefixFormatter prefix, Level level) {
		super(prefix, level);
	}

	@Override
	public void newLine() {
		buffer.append(System.lineSeparator());
	}
	
	@Override
	protected void writeString(Level level, CharSequence data) {
		buffer.append(prefix.format(level, prefixStr) + data);
		newLine();
	}
	
	/**
	 * Empty the buffer and submit logs to the parent {@code LoggerThread}.<br>
	 * Buffered logs are submitted if and only if {@code flush()} or {@code close()} called.
	 * */
	@Override
	public void flush() {
		if(buffer.getBuffer().length() != 0) {
			String str = buffer.toString();
			buffer.getBuffer().setLength(0);
			queueLogTask((logTo) -> {
				logTo.print(str);
			});
		}
	}

}
