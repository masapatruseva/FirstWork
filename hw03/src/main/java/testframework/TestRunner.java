package testframework;

import annotations.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private static void runBefores(Object obj, List<Method>  beforeMethods) throws Exception {
        for (Method before : beforeMethods) {
            before.setAccessible(true);
            before.invoke(obj);
        }
    }
    private static void runTest(Object obj, Method m) throws Exception {
        m.setAccessible(true);
        m.invoke(obj);
        System.out.println(m.getName() + " прошел:)");
    }
    private static void runAfters(Object obj, List<Method>  afterMethods) {
        for (Method after : afterMethods) {
            try {
                after.setAccessible(true);
                after.invoke(obj);
            } catch (Throwable e) {
                System.out.println("Ошибка в after");
            }
        }
    }
    private static void printResult(int passed, int failed) {
        int all = passed + failed;
        System.out.println("\nВсего: " + all);
        System.out.println("Успешно: " + passed);
        System.out.println("Упало: " + failed);
    }
    public static void run(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            List<Method> beforeMethods = new ArrayList<>();
            List<Method> testMethods = new ArrayList<>();
            List<Method> afterMethods = new ArrayList<>();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethods.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                } else if (method.isAnnotationPresent(After.class)){
                    afterMethods.add(method);
                }
            }

            int passed = 0;
            int failed = 0;

            for (Method test : testMethods) {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                try {
                    runBefores(obj, beforeMethods);
                    runTest(obj, test);
                    passed++;

                } catch (Throwable e) {
                    System.out.println(test.getName() + " упал: " + e.getCause().getMessage());
                    failed++;
                } finally {
                    runAfters(obj, afterMethods);
                    Thread.sleep(1500);
                }
            }

            printResult(passed, failed);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
