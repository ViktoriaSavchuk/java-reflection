package example1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Class<A> aClass1 = A.class;

        Constructor<A> constructor = aClass1.getDeclaredConstructor();
        constructor.setAccessible(true);
        A a = constructor.newInstance();

        //example1.A a = new example1.A();//is private
        //a.method();
        Class<? extends A> aClass = a.getClass();
        Method method = aClass.getDeclaredMethod("method");
        method.setAccessible(true);
        method.invoke(a);

        Class<B> bClass = B.class;
        Constructor<B> declaredConstructor = bClass.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);



        B b = declaredConstructor.newInstance("example1.A", 1);
        Method message = bClass.getDeclaredMethod("message",int.class);
        message.setAccessible(true);
        Object invoke = message.invoke(b, 2);
        System.out.println(invoke);


    }
}

class A {

    private A() {
    }

    private void method() {
        System.out.println("Hi");
    }

}

class B{
    private String name;
    private int age;

    private B(String name, int age){
        this.name=name;
        this.age=age;
    }

    private String message(int n){

        StringBuilder stringBuilder=new StringBuilder();

        for (int i = 0; i < n; i++) {
            stringBuilder.append(name + " " + age+"\n");
        }

        return stringBuilder.toString();
    }
}
