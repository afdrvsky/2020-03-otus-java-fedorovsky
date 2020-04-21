import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class<Tester> clazz = Tester.class;

        Constructor<Tester> constructor = clazz.getConstructor(String.class);
        Tester tstng = constructor.newInstance("initValue");

        Method beforeTest = clazz.getMethod("beforeTest");
        if (beforeTest.isAnnotationPresent(Before.class)) {
            beforeTest.invoke(tstng);
        }

        for (Method t : clazz.getDeclaredMethods()) {
            if (t.isAnnotationPresent(Test.class)) {
                tests++;
                try {
                    t.invoke(tstng);
                    passed++;
                } catch (InvocationTargetException wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(t + " failed: " + exc);
                }
            }
        }

        Method afterTest = clazz.getMethod("afterTest");
        if (afterTest.isAnnotationPresent(After.class)) {
            afterTest.invoke(tstng);
        }

        System.out.println("--------------------------------------------");
        System.out.println("Всего тестов: " + tests);
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалились: " + (tests - passed));
    }
}
