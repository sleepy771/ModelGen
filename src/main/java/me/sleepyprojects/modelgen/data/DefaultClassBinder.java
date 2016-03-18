package me.sleepyprojects.modelgen.data;

import me.sleepyprojects.modelgen.Argument;
import me.sleepyprojects.modelgen.ArgumentsDefinition;
import me.sleepyprojects.modelgen.ClassCodeDefinition;
import me.sleepyprojects.modelgen.FieldDefinition;
import me.sleepyprojects.modelgen.FlowCode;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.MethodDefinition;
import me.sleepyprojects.modelgen.Modifier;
import me.sleepyprojects.modelgen.ModifiersDefinition;

public class DefaultClassBinder extends ClassBinder {
    @Override
    public void init() {
        register(MethodDefinition.class);
        register(Meta.class);
        register(ArgumentsDefinition.class);
        register(String.class);
        register(ModifiersDefinition.class);
        register(ClassCodeDefinition.class);
        register(FieldDefinition.class);
        register(FlowCode.class);
        register(Modifier.class);
        register(Argument.class);
    }
}
