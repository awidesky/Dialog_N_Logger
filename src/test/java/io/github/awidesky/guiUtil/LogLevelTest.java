package io.github.awidesky.guiUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.github.awidesky.guiUtil.level.Level;
import io.github.awidesky.guiUtil.prefix.SimplePrefixFormatter;
import io.github.awidesky.guiUtil.simple.SimpleLogger;
import io.github.awidesky.guiUtil.simple.StringLogger;

class LogLevelTest {
	
	private static SimpleLogger debugLogger = new SimpleLogger(System.out);
	static {
		debugLogger.setPrefixFormatter(new SimplePrefixFormatter());
		debugLogger.setPrefixString("[LogLevelTest debug] ");
		debugLogger.setLogLevel(Level.DEBUG); // delete this line or set higher level to omit test logs
	}
	
	@BeforeAll
	@AfterAll
	static void log_guard() {
		System.out.println("==========================LogLevelTest==========================");
	}

	@Test
	void test() {
		Arrays.stream(Level.values())
			.forEach(eachLevel -> {
				StringLogger sl = new StringLogger();
				levelIterateTest(eachLevel, sl, sl::getString);
			});
	}

	void levelIterateTest(Level eachLevel, Logger logger, Supplier<String> logGobbler) {
		logger.setLogLevel(eachLevel);
		Map.of(
				Level.INFO, logger.isInfoEnabled(),
				Level.DEBUG, logger.isDebugEnabled(),
				Level.TRACE, logger.isTraceEnabled(),
				Level.WARNING, logger.isWarningEnabled(),
				Level.ERROR, logger.isErrorEnabled(),
				Level.FATAL, logger.isFatalEnabled()
				).entrySet().stream()
		.forEach(e -> {
			assertEquals(eachLevel.includes(e.getKey()), e.getValue().booleanValue(),
					() -> "level " + e.getKey() + " must be " + eachLevel.includes(e.getKey()) + ", since set level is : " + eachLevel);
			assertEquals(e.getKey().ordinal() <= eachLevel.ordinal(), e.getValue().booleanValue(),
					() -> "level " + e.getKey().ordinal() + "(" + e.getKey() + ") <= " + "level " + eachLevel.ordinal() + "(" + eachLevel
					+ ") must be true, since set level is : " + eachLevel);
		});

		Arrays.stream(Level.values()).forEach(le -> logger.logInLevel(le, le.name()));

		String output = logGobbler.get();
		String nonmatch = output.lines().map(Level::valueOf).filter(l -> !eachLevel.includes(l)).map(Level::name).collect(Collectors.joining(", "));

		debugLogger.debug("Level Iterate Test for " + logger.toString());
		output.lines().forEach(debugLogger::debug);
		debugLogger.debug("========================================");
		debugLogger.debug();


		if(!"".equals(nonmatch)) {
			System.out.println("Logger \"" + logger.toString() + "\"'s level is " + logger.getLogLevel());
			System.out.println("========================================");
			System.out.println(output);
			System.out.println("========================================");
			System.out.println("\"" + nonmatch + "\" should not printed!");
			fail("\"" + nonmatch + "\" should not printed since logger level is " + logger.getLogLevel());;
		}
	}

}
