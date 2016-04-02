package me.sleepyprojects.modelgen.language;

public interface HasModifiers<Language> extends CreateBlock {
    boolean addModifier(ModifierType<Language> modifier);
}
