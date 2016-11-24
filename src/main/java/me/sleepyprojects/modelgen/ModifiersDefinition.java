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

import me.sleepyprojects.modelgen.ast.Modifier;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@TemplateId("modifiers")
public class ModifiersDefinition extends BaseDefinition {

    private final Iterable<Modifier> mods;

    private ModifiersDefinition(final Iterable<Modifier> mods) {
        this.mods = mods;
    }

    @Override
    protected void assign(Language language, Map<String, Part> parts, Map<String, Block> blocks) {
        parts.put("modifiers", new PartsIterable<>(mods));
    }

    public static class Builder implements Block.Builder<ModifiersDefinition> {

        private final Set<Modifier> modifierSet;
        private final BlockType type;

        public Builder(BlockType type) {
            modifierSet = new TreeSet<>(Modifier.Comparator.INSTANCE);
            this.type = type;
        }

        public void add(Modifier modifier) {
            if (!modifier.getSupportedTypes().contains(type)) {
                return;
            }
            modifierSet.add(modifier);
        }

        @Override
        public ModifiersDefinition build() {
            return new ModifiersDefinition(this.modifierSet);
        }
    }
}
