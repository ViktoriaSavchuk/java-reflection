package reflection;

import reflection.annotation.AfterMethod;
import reflection.annotation.BeforeMethod;
import reflection.annotation.Ignore;
import reflection.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EJUnitRunner {

    public static void main(String[] args) throws Exception {
        Class<TestClass> testClazz = TestClass.class;

        //create object
        Constructor<TestClass> constructor = testClazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        //instance
        TestClass classInstance = constructor.newInstance();

        //methods
        Method[] methods = testClazz.getDeclaredMethods();

        List<Method> beforeMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeMethod.class))
                .collect(toList());
        List<Method> testMethod = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .collect(toList());

        List<Method> afterMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterMethod.class))
                .collect(toList());

        List<Method> ignoredMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Ignore.class))
                .collect(toList());


        ignoreMethod(classInstance, ignoredMethods);

        for (
                int i = 0; i < testMethod.size(); i++)

        {
            runMethods(classInstance, beforeMethods);
            runSingleMethod(classInstance, testMethod.get(i));
            runMethods(classInstance, afterMethods);
        }

    }

    private static void runSingleMethod(TestClass instance, Method testMethod) {
        try {
            testMethod.invoke(instance);
        } catch (Exception e) {

            if (e.getCause().getClass().equals(testMethod.getAnnotation(Test.class).expected())) {
                System.out.println(testMethod.getName() + "method throw expected exception "
                        + testMethod.getAnnotation(Test.class).expected().getName());
            }
        }
    }

    private static void runMethods(TestClass instance, List<Method> repeatMethods) {

        repeatMethods.forEach(method -> runSingleMethod(instance, method));
    }

    private static void ignoreMethod(TestClass instance, List<Method> ignoredMethods) {
        ignoredMethods.forEach(method -> System.out.println(method.getName() + "method is indicated as ignored"));
    }


}
