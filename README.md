# de.ab32.jetty

## Project description

This project contains the class AsyncRequestShortenerLogWriter, an sub-class of jetty's org.eclipse.jetty.server.AsyncRequestLogWriter.

It supports the pattern `SHORT(ip-address)` for shortening an ip-address.

### Example
```
SHORT(%{client}a):%{client}p -> %{server}p - %u %t "%r" %s %O D%D}
```
may lead to the following line in the request-log:
```
129.[...]:43489 -> 80 - - [25/Jun/2020:01:01:58 +0000] "GET /shell.php HTTP/1.1" 404 241 D0
```

## Building

You may use maven for building the project.
``` shell
  mvn package
```

## Installation
Copy shortrequestlog.mod into the modules-folder, e.g. /usr/share/jetty9/modules.

_I'm not happy with that. I would prefer to find a custom-place for shortrequestlog.mod instead of the installation's modules-folder._

Copy the jetty-shortrequestlog.xml into the etc-folder, e.g. /etc/jetty9.

Copy the jar containing the plugin, e.g. de.ab32.jetty-0.1.jar, to some folder, e.g. /usr/local/share/jetty.

## Usage
Example of a configuration in jetty's `start.ini`:
```
--module=[...],shortrequestlog,[...],logging-jetty

[...]

--lib=/usr/local/share/jetty/de.ab32.jetty.jar
jetty.shortrequestlog.formatString=SHORT(%{client}a):%{client}p -> %{server}p - %u %t "%r" %s %O D%D
```

