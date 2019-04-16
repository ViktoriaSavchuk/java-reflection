package reflection;

import reflection.annotation.AfterMethod;
import reflection.annotation.BeforeMethod;
import reflection.annotation.Ignore;
import reflection.annotation.Test;

import java.util.ArrayList;

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

    @Ignore
    public void shouldBeIgnoredTest(){
        System.out.println("it will never be printed");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shoudThrowExeption() {
        System.out.println(new ArrayList<>().get(0));
    }

    @AfterMethod
    public void end(){
        System.out.println("after method");
    }

}
