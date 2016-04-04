package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.FieldDefinition;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.FieldFactory;
import me.sleepyprojects.modelgen.language.MetaFactory;

public class SimpleFieldFactory implements FieldFactory<JavaFieldType> {

    private final MetaFactory<JavaModifierType> modifierFactory;
    private final MetaFactory<JavaAnnotationType> annotationFactory;

    public SimpleFieldFactory(MetaFactory<JavaModifierType> modifierFactory,
                              MetaFactory<JavaAnnotationType> annotationFactory) {
        this.modifierFactory = modifierFactory;
        this.annotationFactory = annotationFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JavaFieldType create(FieldDefinition definition) {
        JavaFieldType jField = new JavaFieldType();
        jField.setName(definition.getName());
        jField.setType(definition.getType());
        jField.setDeclaringType(definition.getDeclaringType());
        definition.getMetas()
                  .stream()
                  .filter(modifierFactory::is)
                  .forEach(meta -> jField.addModifier(modifierFactory.get(meta)));
        definition.getMetas()
                  .stream()
                  .filter(annotationFactory::is)
                  .forEach(meta -> jField.addAnnotation((AnnotationType<JavaMarker>) annotationFactory.get(meta)));
        return jField;
    }
}
