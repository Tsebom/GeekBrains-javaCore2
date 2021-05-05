package ru.geekbrains.javaCore2.nezhdanovsv;

public class TestClass {

    @BeforeSuite
    public static void beforeSuit() {
        System.out.println("BeforeSuite");
    }

//    @BeforeSuite
//    public static void beforeSuit2() {
//        System.out.println("BeforeSuite");
//    }

    @AfterSuite
    public static void afterSuit() {
        System.out.println("AfterSuite");
    }

    @Test(priority = 3)
    public static void test1() {
        System.out.println("test1");
    }

    @Test(priority = 2)
    public static void test2() {
        System.out.println("test2");
    }

    @Test(priority = 2)
    public static void test2_2() {
        System.out.println("test2_2");
    }

    @Test(priority = 1)
    public static void test3() {
        System.out.println("test3");
    }
}
