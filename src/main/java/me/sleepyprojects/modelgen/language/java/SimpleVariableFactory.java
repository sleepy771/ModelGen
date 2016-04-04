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
package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.VariableDefinition;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.HasAssignment;
import me.sleepyprojects.modelgen.language.MetaFactory;
import me.sleepyprojects.modelgen.language.VariableFactory;

public class SimpleVariableFactory implements VariableFactory<JavaVariableType> {

    private final MetaFactory<JavaAnnotationType> annotationFactory;
    private final MetaFactory<JavaModifierType> modifierFactory;
    private boolean isArgument;

    public SimpleVariableFactory(MetaFactory<JavaAnnotationType> annotationFactory,
                                 MetaFactory<JavaModifierType> modifierFactory) {
        this.annotationFactory = annotationFactory;
        this.modifierFactory = modifierFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JavaVariableType create(VariableDefinition definition) {
        final JavaVariableType jVar = createVariable();
        jVar.setName(definition.getName());
        jVar.setType(definition.getType());
        definition.getMetas()
                  .stream()
                  .filter(annotationFactory::is)
                  .forEach(meta -> jVar.addAnnotation((AnnotationType<JavaMarker>) annotationFactory.get(meta)));
        definition.getMetas()
                  .stream()
                  .filter(modifierFactory::is)
                  .forEach(meta -> jVar.addModifier(modifierFactory.get(meta)));
        if (!isArgument && definition instanceof HasAssignment) {
            JavaVariableTypeImpl jVarType = (JavaVariableTypeImpl) jVar;
            jVarType.setValue(((HasAssignment) definition).getValue());
        }
        return jVar;
    }

    final void setIsArgument(boolean argument) {
        this.isArgument = argument;
    }

    private JavaVariableType createVariable() {
        if (this.isArgument) {
            return new JavaArgumentType();
        }
        return new JavaVariableTypeImpl();
    }
}
