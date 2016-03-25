package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Modifier;

public interface HasModifiers extends CreateBlock {
    boolean addModifier(Modifier modifier);
}
