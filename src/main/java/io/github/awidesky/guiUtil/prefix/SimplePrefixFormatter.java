package io.github.awidesky.guiUtil.prefix;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.awidesky.guiUtil.level.Level;

public class SimplePrefixFormatter extends PrefixFormatter {
	
	private DateFormat date = null;
	private Pattern datePtn = Pattern.compile("%d(\\{(.*)\\})?");
	
	public SimplePrefixFormatter() {
		this("[%l] [%t] [%d] %p");
	}
	public SimplePrefixFormatter(String pattern) {
		setPattern(pattern);
	}
	
	@Override
	public SimplePrefixFormatter setPattern(String pattern) {
		this.pattern = pattern;
		Matcher m = datePtn.matcher(pattern);
		String datePatternStr = null;
		if(m.find()) datePatternStr = m.group(2);
		
		if(datePatternStr != null && !datePatternStr.isBlank())
			date = new SimpleDateFormat(datePatternStr);
		else 
			date = null;
		
		return this;
	}
	
	
	@Override
	public String format(Level level, String prefix) {
		return pattern
				.replace("%l", level.name())
				.replaceAll(datePtn.pattern(), date == null ? "" : date.format(new Date()))
				.replace("%t", Thread.currentThread().getName())
				.replace("%p", prefix != null ? prefix : "")
				.replace("%%", "%");
	}
	@Override
	public SimplePrefixFormatter clone() {
		return new SimplePrefixFormatter(pattern);
	}

}
