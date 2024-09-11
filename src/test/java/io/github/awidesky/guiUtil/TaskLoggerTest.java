package io.github.awidesky.guiUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.awidesky.guiUtil.prefix.SimplePrefixFormatter;
import io.github.awidesky.guiUtil.thread.LoggerThread;
import io.github.awidesky.guiUtil.thread.TaskBufferedLogger;
import io.github.awidesky.guiUtil.thread.TaskLogger;

class TaskLoggerTest {

	private static LoggerThread lt;
	private static StringWriter sw;
	
	private final static int LOGGER_NUM = 10;
	private final static int STRING_NUM = 5;
	/* Just a random Strings */
	private final static List<String> strList = IntStream.range(0, STRING_NUM).mapToObj(String::valueOf).toList();
	

	@BeforeEach
	void setUpBeforeTest() {
		lt = new LoggerThread();
		lt.setPrefixFormatter(new SimplePrefixFormatter("[Thread %p] "));
		sw = new StringWriter();
		lt.setLogDestination(sw, true);
		lt.start();
	}

	@Test
	void TaskLoggertest() {
		System.out.println("\n==========================TaskLoggerTest==========================");
		List<TaskLogger> loggers = new LinkedList<TaskLogger>();
		for(int i = 0; i < LOGGER_NUM; i++) {
			loggers.add(lt.getLoggerBuilder().setPrefixString(String.valueOf(i)).getLogger());
		}
		
		loggers.stream().parallel().forEach(t -> strList.forEach(t::info));
		lt.shutdown(1000);
		System.out.print(sw.toString());
		
		List<String> results = new LinkedList<String>();
		results.addAll(Arrays.asList(sw.toString().split("\n")));
		results.removeIf(s -> s.startsWith("LoggerThread started at "));
		
		for(int i = 0; i < LOGGER_NUM; i++) {
			int ii = i;
			results.removeAll(strList.stream().map(s -> "[Thread " + ii + "] " + s).toList());
		}
		assertTrue(results.isEmpty(), () -> results.stream().collect(Collectors.joining("\n")));
		System.out.println("==========================TaskLoggerTest==========================\n");
	}
	
	@Test
	void BufferedTaskLoggertest() {
		System.out.println("\n==========================BufferedTaskLoggertest==========================");
		List<TaskBufferedLogger> loggers = new LinkedList<TaskBufferedLogger>();
		for(int i = 0; i < LOGGER_NUM; i++) {
			loggers.add(lt.getLoggerBuilder().setPrefixString(String.valueOf(i)).getBufferedLogger());
		}
			
		loggers.stream().parallel().forEach(t -> strList.forEach(t::info));
		loggers.forEach(TaskBufferedLogger::flush);
		lt.shutdown(1000);
		System.out.print(sw.toString());
		
		String str = sw.toString();
		str = str.substring(str.indexOf("\n") + 1);
		assertEquals(IntStream.range(0, LOGGER_NUM).mapToObj(String::valueOf)
						.flatMap(i -> IntStream.range(0, STRING_NUM).mapToObj(j -> "[Thread " + i + "] " + j))
						.collect(Collectors.joining("\n")),
					str.strip());
		System.out.println("==========================BufferedTaskLoggertest==========================\n");
	}

}
