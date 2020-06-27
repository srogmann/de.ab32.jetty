package de.ab32.jetty;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit-test of the class {@link AsyncRequestShortenerLogWriter}.
 */
public class AsyncRequestShortenerLogWriterTest {

	/**
	 * JUnit-Tests of the method {@link AsyncRequestShortenerLogWriter#shortenLine(String)}.
	 */
	@Test
	public void testShortenLine() {
		Assert.assertEquals("129.[...]:43489 -> 80 - - [25/Jun/2020:01:01:58 +0000] \"GET /shell.php HTTP/1.1\" 404 241 D0",
				AsyncRequestShortenerLogWriter.shortenLine("SHORT(129.1.2.3):43489 -> 80 - - [25/Jun/2020:01:01:58 +0000] \"GET /shell.php HTTP/1.1\" 404 241 D0"));
	}

}
