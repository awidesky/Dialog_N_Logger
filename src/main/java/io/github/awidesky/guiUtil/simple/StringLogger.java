package io.github.awidesky.guiUtil.simple;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import io.github.awidesky.guiUtil.AbstractLogger;
import io.github.awidesky.guiUtil.LoggerOutputStream;
import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.prefix.NullPrefixFormatter;

/**
 * Gather logs in a internal buffer.
 * {@code StringLogger#getString()} will return the logged data as {@code String}.
 * <br>
 * Field {@code printLogLevel} is set to {@code false} initially, since {@code StringLogger} 
 * is normally used to gobble output, like {@code java.io.StringWriter}.
 */
public class StringLogger extends AbstractLogger {

	private final List<String> list;
	
	/**
	 * Creates a new StringLogger with {@code printLogLevel}
	 * set to {@code false}, and {@code PrefixFormatter} set to
	 * {@link NullPrefixFormatter}.
	 */
	public StringLogger() {
		this(new LinkedList<>());
	}
	
	/**
	 * Initialize the {@code String} list with given parameter,
	 * and {@code PrefixFormatter} set to {@link NullPrefixFormatter}.
	 * @param list the buffer to store logged Strings
	 */
	protected StringLogger(List<String> list) {
		prefix = NullPrefixFormatter.instance();
		this.list = list;
	}
	
	/**
	 * Returns the log string that were collected
	 * After this call, the log buffer will cleared.
	 * 
	 * @return Texts that are logged since last call of this method.
	 */
	public String getString() {
		String ret = list.stream().collect(Collectors.joining("\n"));
		clear();
		return ret;
	}
	/**
	 * Returns the log string that were collected
	 * The log buffer will <strong>not</strong> be cleared.
	 * 
	 * @return Texts that are logged since last call of {@link StringLogger#getString()}.
	 */
	public String peekString() {
		return list.stream().collect(Collectors.joining("\n"));
	}
	
	/**
	 * Removes all of the logs from this logger.
	 * {@code getString()} will return empty String
	 * after this call returns.
	 */
	public void clear() {
		list.clear();
	}
	
	@Override
	public void newLine() {
		list.add("\n");
	}
	
	/**
	 * Does not do anything.<p>
	 * If clearing inner buffer of the {@code StringLogger} is needed,
	 * call {@code StringLogger#clear()}.
	 */
	@Override
	public void close() {}
	
	@Override
	protected void writeString(Level level, CharSequence str) {
		list.add(prefix.format(level, prefixStr) + str);
	}
	
	@Override
	protected LoggerOutputStream getLoggerOutputStream(Level level, boolean autoFlush, Charset charset) {
		LoggerOutputStream ret = super.getLoggerOutputStream(level, autoFlush, charset);
		ret.setCloseExternalLogger(false);
		return ret;
	}
	
}
