package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.ArgumentDefinition;
import me.sleepyprojects.modelgen.Meta;

public interface Decorator extends HasName {
    boolean addArgument(ArgumentDefinition argument);

    void setType(Meta type);

    Meta getType();
}
