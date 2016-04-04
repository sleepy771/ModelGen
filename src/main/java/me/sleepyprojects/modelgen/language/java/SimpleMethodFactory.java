package me.sleepyprojects.modelgen.language.java;

import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.language.AnnotationType;
import me.sleepyprojects.modelgen.language.ArgumentType;
import me.sleepyprojects.modelgen.language.MetaFactory;
import me.sleepyprojects.modelgen.language.MethodFactory;
import me.sleepyprojects.modelgen.language.VariableFactory;

//@Singleton
public class SimpleMethodFactory implements MethodFactory<JavaMethodType> {

    private final MetaFactory<JavaModifierType> modifierFactory;
    private final MetaFactory<JavaAnnotationType> annotationFactory;
    private final VariableFactory<JavaArgumentType> argumentFactory;

    // @Inject
    public SimpleMethodFactory(MetaFactory<JavaModifierType> modifierFactory,
                               MetaFactory<JavaAnnotationType> annotationFactory,
                               VariableFactory<JavaArgumentType> argumentFactory) {
        this.modifierFactory = modifierFactory;
        this.annotationFactory = annotationFactory;
        this.argumentFactory = argumentFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JavaMethodType create(MethodDefinition definition) {
        JavaMethodType jMethod = new JavaMethodType();
        jMethod.setName(definition.getName());
        jMethod.setType(definition.getReturnType());
        definition.getMetas()
                  .stream()
                  .filter(modifierFactory::is)
                  .forEach(meta -> jMethod.addModifier(modifierFactory.get(meta)));
        definition.getMetas()
                  .stream()
                  .filter(annotationFactory::is)
                  .forEach(meta -> jMethod.addAnnotation((AnnotationType<JavaMarker>) annotationFactory.get(meta)));
        definition.getArgs().stream().forEach(arg -> jMethod.addArgument((ArgumentType<JavaMarker>) argumentFactory.create(arg)));
        jMethod.setDeclaringType(definition.getDeclaringType());
        return jMethod;
    }
}
