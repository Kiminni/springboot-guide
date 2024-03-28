package org.springboot.jpa;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
        System.out.println();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("Test 1");
        System.out.println();
    }


    @Test
    void test2() {
        System.out.println("Test 2");
        System.out.println();
    }


    @Test
    @Disabled
    void test3() {
        System.out.println("Test 3");
        System.out.println();
    }


}
