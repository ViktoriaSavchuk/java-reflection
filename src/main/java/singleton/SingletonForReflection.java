package singleton;

public class SingletonForReflection {

    private static final SingletonForReflection instance = new SingletonForReflection();

    private SingletonForReflection() {
    }

    public static SingletonForReflection getInstance() {
        return instance;
    }
}
