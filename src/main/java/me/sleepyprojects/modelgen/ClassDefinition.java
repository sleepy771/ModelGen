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
import me.sleepyprojects.modelgen.language.BaseMeta;
import me.sleepyprojects.modelgen.language.SuperTypeAssigner;

import java.util.*;

@TemplateId("class")
public class ClassDefinition extends BaseDefinition {
    public static final BlockType TYPE = BlockType.TYPE;

    private final List<Meta> superTypes;
    private final ModifiersDefinition modifiers;
    private final Meta thisClass;
    private ClassCodeDefinition code;

    ClassDefinition(Meta thisClass, List<Meta> superTypes, ModifiersDefinition modifiers, ClassCodeDefinition definition) {
        this.thisClass = thisClass;
        this.superTypes = superTypes;
        this.modifiers = modifiers;
        this.code = definition;
    }

    @Override
    protected void assign(Language language, Map<String, Part> parts, Map<String, Block> blocks) {
        SuperTypeAssigner superType = language.getAssigner(SuperTypeAssigner.class);
        superType.setCurrentMeta(thisClass);
        superType.assign(blocks, parts, superTypes);
        parts.put("name", new FlatPart(this.thisClass.getName()));
        blocks.put("code", code.create(language));
        blocks.put("modifiers", modifiers.create(language)); // TODO: 14.3.2016 create assigner for modifiers
    }

    public static class Builder implements Block.Builder<ClassDefinition> {

        private String className;
        private String pkg;
        private List<Meta> superClasses;
        private Meta.Type type;
        private ModifiersDefinition.Builder modifiers;
        private ClassCodeDefinition.Builder codeBuilder;

        public Builder() {
            this.superClasses = new ArrayList<>();
            this.modifiers = new ModifiersDefinition.Builder(ClassDefinition.TYPE);
            this.codeBuilder = new ClassCodeDefinition.Builder();
        }

        public void setName(String name) {
            this.className = name;
        }

        public void setClassType(Meta.Type type) {
            this.type = type;
        }

        public void setPackage(String pkg) {
            this.pkg = pkg;
        }

        public void addModifier(Modifier modifier) {
            modifiers.add(modifier);
        }

        public void addField() {
        }

        public void addConstructor() {
        }

        public void addStaticInitializer() {
        }

        public void addInnerClass(ClassDefinition innerClass) {
        }

        public void addInitializer() {
        }

        public void addMethod(MethodDefinition method) {
            this.codeBuilder.addMethod(method);
        }

        public void addSuperClass(Meta meta) {
            if (meta == null || superClasses.contains(meta)) {
                return;
            }
            this.superClasses.add(meta);
        }

        public void removeSuperClass(Meta meta) {
            this.superClasses.remove(meta);
        }

        @Override
        public ClassDefinition build() {
            Meta classMeta = new BaseMeta(this.className, this.pkg, this.type);
            return new ClassDefinition(classMeta, superClasses, modifiers.build(), codeBuilder.build());
        }
    }
}
