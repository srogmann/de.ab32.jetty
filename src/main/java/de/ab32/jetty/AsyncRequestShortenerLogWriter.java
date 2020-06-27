package de.ab32.jetty;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jetty.server.AsyncRequestLogWriter;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

/**
 * AsyncRequestLogWriter for writing shortened ip-addresses.
 *
 * <p>Example of a format-string:
 * </p>
 * <code>SHORT(%{client}a):%{client}p -> %{server}p - %u %t "%r" %s %O D%D}</code>
 */
public class AsyncRequestShortenerLogWriter extends AsyncRequestLogWriter {
	/** Logger */
    protected static final Logger LOG = Log.getLogger(AsyncRequestShortenerLogWriter.class);

	/** Pattern to recognize SHORT(...), convert "SHORT(192.168.1.5)" into "192" */
	private static final Pattern P_IP = Pattern.compile("SHORT\\(([0-9a-fA-F]*[^0-9a-fA-F]?)[^)]*\\)");

	/**
	 * Constructor
	 * @param filename filename
	 * @param queue queue or <code>null</code>
	 */
    public AsyncRequestShortenerLogWriter(String filename, BlockingQueue<String> queue) {
    	super(filename, queue);
    	
    	LOG.info("File: %s", filename);
    }

    @Override
    public void write(String log) throws IOException {
		if (log != null) {
			super.write(shortenLine(log));
		}
	}

    /**
     * Shortens a log-line.
     * @param line log-line
     * @return shortened log-line
     */
    static final String shortenLine(final String line) {
    	final Matcher m = P_IP.matcher(line);
    	final StringBuffer sb = new StringBuffer(line.length());
    	while(m.find()) {
    		m.appendReplacement(sb, m.group(1));
    		sb.append("[...]");
    	}
    	m.appendTail(sb);
    	return sb.toString();
    }

}
