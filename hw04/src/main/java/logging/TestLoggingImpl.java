package logging;

import annotation.Log;

public class TestLoggingImpl implements TestLogging {

    @Log
    public void calculation(int param) {
        System.out.println(param + " * 2 = " + (param * 2));
    }

    @Log
    public void calculation(int param1, int param2) {
        int res = param1 + param2;
        System.out.println(String.format("Сумма %d и %d равна: %d", param1, param2, res));
    }

    @Log
    public void calculation(int param1, int param2, String param3) {
        System.out.println("Метод сработал");
    }
}
