package wrapper;

import logging.TestLogging;

import java.lang.reflect.Proxy;

public class NewProxy {

    public static <T> T create(T target, Class<T> intrfc) {
        return (T) Proxy.newProxyInstance(intrfc.getClassLoader(),
                new Class<?>[]{intrfc},
                new LogInvocationHandler(target));
    }
}
