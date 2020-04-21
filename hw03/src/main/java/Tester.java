import annotations.After;
import annotations.Before;
import annotations.Test;

public class Tester {

    private String value = "initValue";
    public Tester(String value) {
        this.value = value;
    }


    @Before
    public void beforeTest() {
        System.out.println("К тестированию готов!");
    }

    @Test
    public void test1() {
        System.out.println("Тест №1 пройден");
    }

    @Test
    public void test2() {
        throw new RuntimeException("Тест №2 упал!");
    }

    @Test
    public void test3() {
        System.out.println("Тест №3 пройден");
    }

    @Test
    public void test4() {
        System.out.println("Тест №4 пройден");
    }

    @Test
    public void test5() {
        System.out.println("Тест №5 пройден");
    }

    @Test
    public void test6() {
        throw new RuntimeException("Тест №6 упал!");
    }

    @Test
    public void test7() {
        System.out.println("Тест №7 пройден");
    }

    @Test
    public void test8() {
        System.out.println("Тест №8 пройден");
    }

    @Test
    public void test9() {
        throw new RuntimeException("Тест №9 упал!");
    }

    @Test
    public void test10() {
        System.out.println("Тест №10 пройден");
    }

    @After
    public void afterTest() {
        System.out.println("Тестирование завершено!");
    }
}
