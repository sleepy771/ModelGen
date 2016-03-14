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
package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.*;

import java.util.List;
import java.util.Map;

public class SuperTypeAssigner implements Assigner<List<Meta>> {
    private Meta meta;

    @Override
    public void assign(final Map<String, Block> blocks, final Map<String, Part> parts, final List<Meta> data) {
        if (meta.getType() == Meta.Type.INTERFACE) {
            final PartsIterable<Meta> superClass = new PartsIterable<>(data);
            superClass.setFilter((final Meta meta) -> meta.getType() == Meta.Type.INTERFACE);
            parts.put("superclass", superClass);
            this.meta = null;
            return;
        }
        if (meta.getType() == Meta.Type.CLASS) {
            final PartsIterable<Meta> superClass = new PartsIterable<>(data);
            superClass.setFilter((final Meta meta) -> meta.getType() == Meta.Type.CLASS);
            final PartsIterable<Meta> interfaces = new PartsIterable<>(data);
            interfaces.setFilter((final Meta meta) -> meta.getType() == Meta.Type.INTERFACE);
            parts.put("superclass", superClass);
            parts.put("interfaces", interfaces);
            this.meta = null;
        }
    }

    public void setCurrentMeta(Meta meta) {
        this.meta = meta;
    }
}
