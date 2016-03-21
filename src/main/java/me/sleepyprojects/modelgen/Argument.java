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

import java.util.Map;

@TemplateId("argument")
public class Argument extends BaseDefinition {
    public static final BlockType TYPE = BlockType.VARIABLE;
    private final String name;
    private final Meta type;
    private final ModifiersDefinition modifiers;


    private Argument(String name, Meta type, ModifiersDefinition modifiers) {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
    }

    @Override
    protected void assign(Language language, Map<String, Part> parts, Map<String, Block> blocks) {
        parts.put("name", new FlatPart(name));
        parts.put("type", type);
        blocks.put("mods", modifiers.create(language));
    }

    public String getName() {
        return name;
    }

    public static class Builder implements Block.Builder<Argument> {

        private final String name;
        private Meta argType;
        private final ModifiersDefinition.Builder modifiers;

        public Builder(String name) {
            this.name = name;
            this.modifiers = new ModifiersDefinition.Builder(BlockType.VARIABLE);
        }

        public void setType(final @NotNull Meta argType) {
            this.argType = argType;
        }

        public void addModifier(final @NotNull Modifier modifier) {
            if (!modifier.getSupportedTypes().contains(Argument.TYPE)) {
                throw new RuntimeException("NOPE");
            }
            this.modifiers.add(modifier);
        }

        @Override
        public Argument build() {
            return new Argument(name, argType, modifiers.build());
        }
    }
}
