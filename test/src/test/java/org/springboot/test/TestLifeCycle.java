package org.springboot.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    @DisplayName("Test 2")
    void test2() {
        System.out.println("Test 2");
    }

    @Test
    @Disabled
    void test3() {
        System.out.println("Test 3");
    }

}
