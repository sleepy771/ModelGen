package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Argument;
import me.sleepyprojects.modelgen.Meta;

public interface Decorator extends HasName {
    boolean addArgument(Argument argument);

    void setType(Meta type);

    Meta getType();
}
