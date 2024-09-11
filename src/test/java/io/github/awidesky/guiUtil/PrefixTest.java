package io.github.awidesky.guiUtil;

import org.junit.jupiter.api.Test;

import io.github.awidesky.guiUtil.prefix.SimplePrefixFormatter;
import io.github.awidesky.guiUtil.simple.ConsoleLogger;

class PrefixTest {

	private String pattern = "[%l] [%t] [%d{yyyy-MM-dd kk:mm:ss}] [%p] ";
	private String prefix = "additional prefix";

	@Test
	void test() {
		System.out.println("\n==========================PrefixTest==========================");
		try(ConsoleLogger logger = new ConsoleLogger();) {
			logger.setPrefixFormatter(new SimplePrefixFormatter(pattern));
			logger.setPrefixString(prefix);
			
			System.out.printf("Pattern : \"%s\"\n", pattern);
			System.out.printf("Prefix  : \"%s\"\n", prefix);
			System.out.println();
			
			logger.info("Test logging");
		}
		System.out.println("==========================PrefixTest==========================");
	}

}
