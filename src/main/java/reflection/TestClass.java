package reflection;

import reflection.annotation.AfterMethod;
import reflection.annotation.BeforeMethod;
import reflection.annotation.Test;

public class TestClass {

    @BeforeMethod
    public void init(){
        System.out.println("before method");
    }

    @Test
    public void test1(){
        System.out.println("test 1");
    }

    @Test
    public void test2(){
        System.out.println("test 2");
    }

    @AfterMethod
    public void end(){
        System.out.println("after method");
    }

}
