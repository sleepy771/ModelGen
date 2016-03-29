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

import me.sleepyprojects.modelgen.language.BaseType;

import java.util.ArrayList;
import java.util.List;

@TemplateId("class")
public class ClassDefinition extends BaseDefinition {
    public static final BlockType TYPE = BlockType.TYPE;

    private List<Type> superTypes;
    private final List<Meta> metas;
    private final Type thisClass;
    private final ClassCodeDefinition codeDefinition;

    ClassDefinition(Type thisClass, List<Type> superTypes, ClassCodeDefinition definition, List<Meta> metas) {
        this.metas = metas;
        this.superTypes = superTypes;
        this.thisClass = thisClass;
        this.codeDefinition = definition;
    }

    public static class Builder implements Block.Builder<ClassDefinition> {

        private String className;
        private String pkg;
        private List<Type> superClasses;
        private Type.MetaType metaType;
        private List<Meta> metas;
        private ClassCodeDefinition.Builder codeBuilder;

        public Builder() {
            this.superClasses = new ArrayList<>();
            this.metas = new ArrayList<>();
            this.codeBuilder = new ClassCodeDefinition.Builder();
        }

        public void setName(String name) {
            this.className = name;
        }

        public void setClassType(Type.MetaType metaType) {
            this.metaType = metaType;
        }

        public void setPackage(String pkg) {
            this.pkg = pkg;
        }

        public void addMeta(Meta meta) {
            this.metas.add(meta);
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

        public void addSuperClass(Type type) {
            if (type == null || superClasses.contains(type)) {
                return;
            }
            this.superClasses.add(type);
        }

        public void removeSuperClass(Type type) {
            this.superClasses.remove(type);
        }

        @Override
        public ClassDefinition build() {
            Type classType = new BaseType(this.className, this.pkg, this.metaType);
            return new ClassDefinition(classType, superClasses, codeBuilder.build(), metas);
        }
    }
}
