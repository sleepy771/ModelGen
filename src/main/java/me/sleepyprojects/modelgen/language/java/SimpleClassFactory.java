package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.ClassDefinition;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.ClassFactory;
import me.sleepyprojects.modelgen.language.FieldFactory;
import me.sleepyprojects.modelgen.language.MetaFactory;
import me.sleepyprojects.modelgen.language.MethodFactory;
import me.sleepyprojects.modelgen.language.TypeProvider;

public class SimpleClassFactory implements ClassFactory<JavaClassType> {

    private final MetaFactory<JavaModifierType> modifierFactory;
    private final MetaFactory<JavaAnnotationType> annotationFactory;
    private final MethodFactory<JavaMethodType> methodFactory;
    private final FieldFactory<JavaFieldType> fieldFactory;
    private final TypeProvider<JavaMarker> typeProvider;

    public SimpleClassFactory(MetaFactory<JavaModifierType> modifierFactory,
                              MetaFactory<JavaAnnotationType> annotationFactory,
                              MethodFactory<JavaMethodType> methodFactory,
                              FieldFactory<JavaFieldType> fieldFactory,
                              TypeProvider<JavaMarker> typeProvider) {
        this.modifierFactory = modifierFactory;
        this.annotationFactory = annotationFactory;
        this.methodFactory = methodFactory;
        this.fieldFactory = fieldFactory;
        this.typeProvider = typeProvider;
    }


    @Override
    @SuppressWarnings("unchecked")
    public JavaClassType create(ClassDefinition definition) {
        final JavaClassType jClass = new JavaClassType();
        jClass.setName(definition.getName());
        // set supetypes
        definition.getSuperTypes().forEach(type -> jClass.addSuperType(typeProvider.get(type)));
        definition.getMetas()
                  .stream()
                  .filter(annotationFactory::is)
                  .forEach(meta -> jClass.addAnnotation((AnnotationType<JavaMarker>) annotationFactory.get(meta)));
        definition.getMetas()
                  .stream()
                  .filter(modifierFactory::is)
                  .forEach(meta -> jClass.addModifier(modifierFactory.get(meta)));
        // TODO create constructor
        definition.getFields().stream().forEach(field -> jClass.addField(fieldFactory.create(field)));
        definition.getMethods().stream().forEach(method -> jClass.addMethod(methodFactory.create(method)));
        return jClass;
    }
}
