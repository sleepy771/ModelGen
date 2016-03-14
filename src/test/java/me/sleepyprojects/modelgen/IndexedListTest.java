package me.sleepyprojects.modelgen;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

/* 
 * Copyright (c) 2016 Filip Hornak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */public class IndexedListTest {

    private List<String> testedList;

    @Before
    public void setUp() {
        testedList = new IndexedList<>();
        testedList.add(2, "a");
    }

    @Test
    public void testAddAppend() throws Exception {
        testedList.add(3, "b");
        assertArrayEquals(new String[] {"a", "b"}, testedList.toArray());
    }

    @Test
    public void testAddPrepend() throws Exception {
        testedList.add(1, "b");
        assertArrayEquals(new String[] {"b", "a"}, testedList.toArray());
    }

    @Test
    public void testAddAll() throws Exception {
        List<String> addList = Arrays.asList("c", "d", "e", "f");
        testedList.addAll(addList);
        testedList.add(1, "b");
        testedList.add(0, "q");
        testedList.add(7, "g");
        assertArrayEquals(new String[] {"q", "b", "a", "c", "d", "e", "f", "g"}, testedList.toArray());
    }

    @Test
    public void testAddAll1IndexClash() throws Exception {
        List<String> addList = Arrays.asList("c", "d", "e", "f");
        testedList.add(1, "b");
        testedList.add(0, "q");
        testedList.add(6, "g");
        assertFalse(testedList.addAll(3, addList));
        assertArrayEquals(new String[] {"q", "b", "a", "g"}, testedList.toArray());
    }

    @Test
    public void testAddAll1() throws Exception {
        List<String> addList = Arrays.asList("c", "d", "e", "f");
        testedList.add(1, "b");
        testedList.add(0, "q");
        testedList.add(7, "g");
        assertTrue(testedList.addAll(3, addList));
        assertArrayEquals(new String[] {"q", "b", "a", "c", "d", "e", "f", "g"}, testedList.toArray());
    }

    @Test
    public void testGet() throws Exception {
        testedList.add("b");
        assertEquals("a", testedList.get(2));
    }

    @Test
    public void testGet2() throws Exception {
        testedList.add("b");
        assertEquals("b", testedList.get(3));
    }

    @Test
    public void testAdd1() throws Exception {
        testedList.add("b");
        assertArrayEquals(new String[] {"a", "b"}, testedList.toArray());
    }

    @Test
    public void testListIterator() throws Exception {
        testedList.addAll(Arrays.asList("b", "c", "d", "e", "f"));
        testedList.add(1, "z");
        testedList.add(0, "y");
        ListIterator<String> testedIterator = testedList.listIterator();
        assertTrue(testedIterator.hasNext());
        assertFalse(testedIterator.hasPrevious());
        assertEquals("y", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("z", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("a", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("b", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("c", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("d", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("e", testedIterator.next());
        assertTrue(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
        assertEquals("f", testedIterator.next());
        assertFalse(testedIterator.hasNext());
        assertTrue(testedIterator.hasPrevious());
    }

    @Test
    public void testListIterator1() throws Exception {
        testedList.add(1, "z");
        testedList.add(0, "y");
        testedList.addAll(Arrays.asList("c", "d", "e"));
        ListIterator<String> lstIter = testedList.listIterator(2);
        String[] tpl = new String[] {"a", "c", "d", "e"};
        for (String aTpl : tpl) {
            assertEquals(aTpl, lstIter.next());
        }
    }
}