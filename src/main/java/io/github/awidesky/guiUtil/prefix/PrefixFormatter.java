package io.github.awidesky.guiUtil.prefix;

import io.github.awidesky.guiUtil.level.Level;

/**
 * A helper class for generate prefix as specified format(pattern).
 */
public abstract class PrefixFormatter implements Cloneable {
	protected String pattern;
	
	public PrefixFormatter setPattern(String pattern) {
		this.pattern = pattern;
		return this;
	}
	public String getPattern() {
		return pattern;
	}
	
	public abstract String format(Level level, String prefix);
	
	@Override
	public String toString() {
		return getClass().getName() + " [pattern=\"" + pattern + "\"]";
	}
	
	/**
	 * Clone the {@code PrefixFormatter} instance.<br>
	 * Implementations can just return a new child class instance
	 * with same {@code pattern} field.<br>
	 * The {@code PrefixFormatter} instance is mutable since the pattern is
	 * changeable via {@link PrefixFormatter#setPattern(String)}.
	 * This behavior is useful when 
	 *  clone method is 
	 */
	@Override
	public abstract PrefixFormatter clone();
}
