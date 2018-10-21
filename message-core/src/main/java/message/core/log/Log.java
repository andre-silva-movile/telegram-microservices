package message.core.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

    Logger INFO = LoggerFactory.getLogger("core");

    Logger EXCEPTION = LoggerFactory.getLogger("exceptions");

    Logger SYSTEM = LoggerFactory.getLogger("system");
}
