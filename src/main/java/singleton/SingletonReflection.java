package singleton;

import java.lang.reflect.Constructor;

public class SingletonReflection {

    public static void main(String[] args) throws Exception {

        SingletonForReflection singletonForReflection = SingletonForReflection.getInstance();
        SingletonForReflection reflectionSingletonForReflection = null;

        Constructor[] constructors = SingletonForReflection.class.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            constructor.setAccessible(true);
            reflectionSingletonForReflection = (SingletonForReflection) constructor.newInstance();
        }
    }

}
