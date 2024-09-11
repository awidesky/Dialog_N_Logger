package io.github.awidesky.guiUtil.prefix;

import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.simple.StringLogger;

/**
 * A {@code NullPrefixFormatter} does not generate any prefix.
 * {@link NullPrefixFormatter#format(Level, String)} will always return
 * an empty string.<br>
 * This object can be used when you don't need to print any prefixes at all.
 * 
 * @see StringLogger#StringLogger()
 */
public class NullPrefixFormatter extends PrefixFormatter {
	
	private static final NullPrefixFormatter instance = new NullPrefixFormatter();
	
	/**
	 * Returns a {@code NullPrefixFormatter} instance.
	 * There is only one instance that initiated since
	 * there is no field specifiable.
	 * 
	 * @return the {@code NullPrefixFormatter} instance
	 */
	public static NullPrefixFormatter instance() { return instance; }
	
	/**
	 * The pattern is {@code null}.
	 */
	private NullPrefixFormatter() { pattern = null; }
	
	/**
	 * Does not set pattern field, just returns itself.
	 * pattern object is always {@code null}.
	 */
	@Override
	public PrefixFormatter setPattern(String pattern) {
		return this;
	}
	
	/**
	 * Returns a empty String every time.
	 * @return a empty String {@code ""}.
	 */
	@Override
	public String format(Level level, String prefix) {
		return "";
	}

	/**
	 * Return self, since there is no need to duplicate a {@code NullPrefixFormatter} instance
	 */
	@Override
	public NullPrefixFormatter clone() {
		return this;
	}
}
