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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class FlatPart implements Part, Iterable<String> {
    private List<String> lines;
    private String prefix;
    private String postfix;

    public FlatPart(List<String> lines) {
        this.lines = lines;
        this.prefix = "";
        this.postfix = "";
    }

    public FlatPart(final String code) {
        this(Arrays.asList(code.split("\n")));
        if (this.lines.size() > 1) {
            this.postfix = "\n";
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new FlatPartIterator(this.lines, this.prefix, "\n" + this.postfix);
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String toString() {
        StringBuilder codeBuilder = new StringBuilder();
        for (final String line : lines) {
            codeBuilder.append(this.prefix).append(line).append(this.postfix);
        }
        return codeBuilder.toString();
    }

    private class FlatPartIterator implements Iterator<String> {

        private final Iterator<String> iterator;
        private final String prefix;
        private final String postfix;

        FlatPartIterator(final @NotNull Iterable<String> iterable, String prefix, String postfix) {
            this.iterator = iterable.iterator();
            this.prefix = prefix != null ? prefix : "";
            this.postfix = postfix != null ? postfix : "";
        }


        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public String next() {
            return this.prefix + this.iterator.next() + this.postfix;
        }
    }
}
