package Demonstration;

import logging.TestLogging;
import logging.TestLoggingImpl;
import wrapper.NewProxy;

public class Demo {

    public static void main(String[] args) {

        TestLogging logging = NewProxy.create(new TestLoggingImpl(), TestLogging.class);

        logging.calculation(8);
        logging.calculation(9, 6);
        logging.calculation(7, 1, "xbckjdm");
    }
}
