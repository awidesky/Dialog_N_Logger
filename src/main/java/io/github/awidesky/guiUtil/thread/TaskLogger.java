/*
 * Copyright (c) 2023 Eugene Hong
 *
 * This software is distributed under license. Use of this software
 * implies agreement with all terms and conditions of the accompanying
 * software license.
 * Please refer to LICENSE
 * */

package io.github.awidesky.guiUtil.thread;

import java.io.PrintWriter;
import java.util.function.Consumer;

import io.github.awidesky.guiUtil.AbstractLogger;
import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.prefix.PrefixFormatter;

/**
 * An abstract Logger class for one-consumer, multi-provider model that manages each logs as "task"
 * (whose type is {@code Consumer<PrintWriter>})
 * <p>
 * log() method family call does not actually write the content to external log destination.
 * Instead, log content will be packed in a task. The content is actually written 
 * when the task is executed.
 * 
 * <p>
 * {@code TaskLogger} provides abstract methods that queue({@code TaskLogger#queueLogTask(Consumer)}) 
 * or run({@code TaskLogger#runLogTask(Consumer)}) the task. The queue(if exists) should be generated and managed in 
 * another class or subclass. {@code TaskLogger} does not know or care about the queue.
 * */
public abstract class TaskLogger extends AbstractLogger {

	/**
	 * Creates a task based logger.
	 * */
	TaskLogger(PrefixFormatter prefix, Level level) {
		setLogLevel(level);
		this.prefix = prefix;
	}

	/**
	 * Queue a log task.
	 * Implementation may queue given <code>logTask</code> to a worker thread. 
	 * */
	protected abstract void queueLogTask(Consumer<PrintWriter> logTask);

	@Override
	public void newLine() {
		queueLogTask((logTo) -> {
			logTo.println();
		});
	}

	@Override
	protected void writeString(Level level, CharSequence str) {
		queueLogTask(getLogTask(level, str));
	}

	/**
	 * Prefix will printed only once even if the String is multiple line.
	 * */
	protected Consumer<PrintWriter> getLogTask(Level level, CharSequence data) {
		return (logTo) -> {
			logTo.println(prefix.format(level, prefixStr) + data);
		};
	}
	
	/**
	 * Closes this logger and releases any system resources associated with it.
	 * If the logger is already closed then invoking this method has no effect.
	 */
	@Override
	public abstract void close();
}