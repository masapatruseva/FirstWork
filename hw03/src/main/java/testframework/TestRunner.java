package testframework;

import annotations.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
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
                    for (Method before : beforeMethods) {
                        before.setAccessible(true);
                        before.invoke(obj);
                    }
                    test.setAccessible(true);
                    test.invoke(obj);
                    System.out.println(test.getName() + " прошел:)");
                    passed++;

                } catch (Throwable e) {
                    System.out.println(test.getName() + " упал:(");
                    failed++;
                } finally {
                    for (Method after : afterMethods) {
                        try {
                            after.setAccessible(true);
                            after.invoke(obj);
                        } catch (Throwable e) {
                            System.out.println("Ошибка в after");
                        }
                    }
                    Thread.sleep(1500);
                }
            }

            int all = passed + failed;
            System.out.println("\nВсего: " + all);
            System.out.println("Успешно: " + passed);
            System.out.println("Упало: " + failed);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
