package wrapper;

import annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogInvocationHandler implements InvocationHandler {

    private final Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method realMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        if(realMethod.isAnnotationPresent(Log.class)) {
            StringBuilder sb = new StringBuilder("executed method: " + method.getName());
            if(args != null) {
                sb.append(", params: ");
                for(int  i = 0; i < args.length; i++) {
                    sb.append(args[i]);
                    if(i < args.length - 1) {
                        sb.append(", ");
                    }
                }
            }
            System.out.println(sb);
        }
        return method.invoke(target, args);
    }
}
