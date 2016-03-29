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

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class PartsIterable<T> implements Iterable<String>, Part {

    private final Iterable<T> parts;
    private Filter<T> filter;

    public PartsIterable(final @NotNull  Iterable<T> parts) {
        this.parts = parts;
    }

    @Override
    public Iterator<String> iterator() {
        return new PartIterator(parts.iterator(), this.filter);
    }

    public void setFilter(Filter<T> filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList<>();
        for (final T p : parts) {
            if (filter == null || filter.isAppropriate(p)) {
                list.add(p.toString());
            }
        }
        return String.join(", ", list);
    }

    class PartIterator implements Iterator<String> {

        private final Iterator<T> partIterator;
        private Filter<T> filter;
        private T current;

        PartIterator(Iterator<T> partIterator, Filter<T> filter) {
            this.partIterator = partIterator;
            this.filter = filter;
        }

        private void findNext() {
            if (current != null) {
                return;
            }
            while (partIterator.hasNext()) {
                final T part = partIterator.next();
                if (filter.isAppropriate(part)) {
                    this.current = part;
                    return;
                }
            }
            this.current = null;
        }

        @Override
        public boolean hasNext() {
            if (filter != null) {
                findNext();
                return current != null;
            }
            return partIterator.hasNext();
        }

        @Override
        public String next() {
            if (filter != null) {
                findNext();
                if (current == null) {
                    throw new NoSuchElementException("No more elements left");
                }
                final String out = current.toString();
                current = null;
                return out;
            }
            final Object part = partIterator.next();
            return part.toString();
        }
    }
}
