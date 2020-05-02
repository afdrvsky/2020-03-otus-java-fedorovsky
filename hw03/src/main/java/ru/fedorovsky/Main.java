package ru.fedorovsky;

import ru.fedorovsky.framework.Starter;

public class Main {

    public static void main(String[] args) throws Exception {
        Starter starter = new Starter();
        starter.getClassByName("ru.fedorovsky.test.Tester");
    }
}
