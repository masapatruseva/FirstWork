package tests;

import annotations.*;
import myarraylist.DIYarrayList;

public class DIYArrayListTest {

    private DIYarrayList<Integer> list;
    private void assertEquals(int a, int b) {
        if ( a != b) {
            throw new AssertionError("Ожидалось " + a + ", а вышло " + b);
        }
    }
    @Before
    public void setUp() {
        list = new DIYarrayList<>();
        System.out.println("тест начался");
    }

    @After
    public void tearDown() {
        list = null;
        System.out.println("тест завершен");
    }

    @Test
    public void addTest() {
        list.add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    public void sizeTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(4, list.size());
    }

    @Test
    public void removeTest() {
        list.add(5);
        list.add(6);
        list.remove(0);

        assertEquals(1, list.size());
        assertEquals(8, list.get(0));
    }

    @Test
    public void getTest() {
        list.add(10);
        assertEquals(10, list.get(0));
    }

}
