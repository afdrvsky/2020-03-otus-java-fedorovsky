package ru.fedorovsky.framework;

import ru.fedorovsky.framework.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Starter {

    ArrayList<Method> beforeMethod = new ArrayList<>();
    ArrayList<Method> testMethods = new ArrayList<>();
    ArrayList<Method> afterMethod = new ArrayList<>();
    int tests = 0;
    int passed = 0;

    public void getClassByName(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        getTests(clazz);
    }

    public void getTests(Class<?> clazz) throws Exception {

        Method[] t = clazz.getDeclaredMethods();

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
        testing(clazz);
    }

    private void testing(Class<?> clazz) throws Exception {

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
                Object testInstance = clazz.getDeclaredConstructor().newInstance();
                for (Method aftMtd : afterMethod) {
                    aftMtd.invoke(testInstance);
                }
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println("Всего тестов: " + tests);
        System.out.println("Пройдено: " + passed);
        System.out.println("Провалились: " + (tests - passed));
    }
}

