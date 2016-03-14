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

import java.util.HashMap;
import java.util.Map;

public abstract class BaseDefinition implements Block.Definition {
    @Override
    public final Block create(Language language) {
        final TemplateId id = getClass().getAnnotation(TemplateId.class);
        if (id == null) {
            throw new RuntimeException("Annotation not defined in definition class");
        }
        Map<String, Part> parts = new HashMap<>();
        Map<String, Block> blocks = new HashMap<>();
        assign(language, parts, blocks);
        return new BaseBlock(blocks, parts, id.value());
    }

    protected abstract void assign(Language language, Map<String, Part> parts, Map<String, Block> blocks);
}
