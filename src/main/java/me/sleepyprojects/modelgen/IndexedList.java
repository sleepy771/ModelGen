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
 */
package me.sleepyprojects.modelgen;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class IndexedList<T> implements List<T> {

    private TreeMap<Integer, T> listMap;

    public IndexedList() {
        listMap = new TreeMap<>();
    }

    @Override
    public int size() {
        return listMap.size();
    }

    @Override
    public boolean isEmpty() {
        return listMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return listMap.containsValue(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIter(this.listMap);
    }

    @Override
    public Object[] toArray() {
        return listMap.values().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return listMap.values().toArray(a);
    }

    @Override
    public boolean add(T t) {
        listMap.put(createIndex(), t);
        return true;
    }

    private Integer createIndex() {
        if (listMap.isEmpty()) {
            return 0;
        }
        final int lastIndex = listMap.lastKey();
        return lastIndex + 1;
    }

    @Override
    public boolean remove(Object o) {
        final int index = indexOf(o);
        return index >= 0 && listMap.remove(index, o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean containsAll = true;
        for (Object o : c){
            containsAll &= listMap.containsValue(o);
        }
        return containsAll;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = true;
        for (T element : c) {
            added &= add(element);
        }
        return added;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        final int last = index + c.size();
        if (!isEmpty()) {
            final int lastFloorStored = listMap.floorKey(last);
            final int firstCeilStored = listMap.ceilingKey(index);
            if ((lastFloorStored < last && lastFloorStored > index) || (firstCeilStored > index && firstCeilStored < lastFloorStored)) {
                return false;
            }
        }
        int idx = index;
        for (T element : c) {
            add(idx, element);
            idx++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = true;
        for (Object o : c) {
            removed &= remove(o);
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        final Set<Integer> leave = new TreeSet<>();
        for (final Object o : c) {
            final int index = indexOf(o);
            if (index > -1) {
                leave.add(index);
            }
        }
        final Set<Integer> currentKeys = listMap.keySet();
        final boolean removed = currentKeys.removeAll(leave);
        for (final Integer rem : currentKeys) {
            listMap.remove(rem);
        }
        return removed;
    }

    @Override
    public void clear() {
        this.listMap.clear();
    }

    @Override
    public T get(int index) {
        return listMap.get(index);
    }

    @Override
    public T set(int index, T element) {
        return this.listMap.put(index, element);
    }

    @Override
    public void add(int index, T element) {
        if (listMap.containsKey(index)) {
            throw new RuntimeException("Clashing indices: " + index);
        }
        this.listMap.put(index, element);
    }

    @Override
    public T remove(int index) {
        return this.listMap.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        for (Map.Entry<Integer, T> entry : listMap.entrySet()) {
            if (entry.getValue().equals(o)) {
                return entry.getKey();
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (Map.Entry<Integer, T> entry : listMap.entrySet()) {
            if (entry.getValue().equals(o)) {
                index = entry.getKey();
            }
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIter(this.listMap);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIter(this.listMap, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new NotImplementedException();
    }

    private class ListIter implements ListIterator<T> {

        final ListIterator<T> innerIterator;

        ListIter(Map<Integer, T> map, int fromIndex) {
            innerIterator = new ArrayList<>(map.values()).listIterator(fromIndex);
        }

        ListIter(Map<Integer, T> map) {
            innerIterator = new ArrayList<>(map.values()).listIterator();
        }

        @Override
        public boolean hasNext() {
            return innerIterator.hasNext();
        }

        @Override
        public T next() {
            return innerIterator.next();
        }

        @Override
        public boolean hasPrevious() {
            return innerIterator.hasPrevious();
        }

        @Override
        public T previous() {
            return innerIterator.previous();
        }

        @Override
        public int nextIndex() {
            return innerIterator.nextIndex();
        }

        @Override
        public int previousIndex() {
            return innerIterator.previousIndex();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Iterator can not change indexed list");
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException("Iterator can not change indexed list");
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException("Iterator can not change indexed list");
        }
    }
}
