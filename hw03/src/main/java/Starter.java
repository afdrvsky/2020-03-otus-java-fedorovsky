import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Starter {

    public void starterTesting(String className) throws Exception {
        int tests = 0;
        int passed = 0;

        Class<?> clazz = Class.forName(className);

        Method[] t = clazz.getDeclaredMethods();

        ArrayList<Method> beforeMethod = new ArrayList<>();
        ArrayList<Method> testMethods = new ArrayList<>();
        ArrayList<Method> afterMethod = new ArrayList<>();

        for (Method method : t) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Before) {
                    beforeMethod.add(method);
                }
                if (annotation instanceof Test) {
                    testMethods.add(method);
                    tests++;
                }
                if (annotation instanceof After) {
                    afterMethod.add(method);
                }
            }
        }

        for (Method testMethod : testMethods) {
            try {
                Object testInstance = clazz.getDeclaredConstructor().newInstance();

                for (Method bfrMtd : beforeMethod) {
                    bfrMtd.invoke(testInstance);
                }

                testMethod.invoke(testInstance);

                for (Method aftMtd : afterMethod) {
                    aftMtd.invoke(testInstance);
                }
                passed++;
            } catch (InvocationTargetException wrappedExc) {
                Throwable exc = wrappedExc.getCause();
                System.out.println(testMethod + " failed: " + exc);
            }
        }

        System.out.println("--------------------------------------------");
        System.out.println("Всего тестов: " + tests);
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалились: " + (tests - passed));
    }
}
