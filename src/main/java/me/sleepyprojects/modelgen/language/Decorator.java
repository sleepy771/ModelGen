package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.ArgumentDefinition;
import me.sleepyprojects.modelgen.Type;

public interface Decorator extends HasName {
    boolean addArgument(ArgumentDefinition argument);

    Type getType();

    void setType(Type type);
}
