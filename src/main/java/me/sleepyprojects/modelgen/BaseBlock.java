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

import java.util.Map;
import java.util.Set;

public class BaseBlock implements Block {

    private final Map<String, Block> subBlocks;
    private final Map<String, ?> subParts;
    private final String template;

    public BaseBlock(final Map<String, Block> subBlocks, final Map<String, ?> subParts, final String template) {
        this.subBlocks = subBlocks;
        this.subParts = subParts;
        this.template = template;
    }


    @Override
    public Set<String> getBlockNames() {
        return subBlocks.keySet();
    }


    @Override
    public Set<? extends Map.Entry<String, ?>> getPartIterator() {
        return subParts.entrySet();
    }

    @Override
    public Iterable<Map.Entry<String, Block>> getBlockIterator() {
        return subBlocks.entrySet();
    }

    @Override
    public int getBlockCount() {
        return subBlocks.size();
    }

    @Override
    public boolean hasBlocks() {
        return !subBlocks.isEmpty();
    }

    @Override
    public String useTemplate() {
        return template;
    }
}
