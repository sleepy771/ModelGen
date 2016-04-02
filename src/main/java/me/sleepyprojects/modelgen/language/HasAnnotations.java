package me.sleepyprojects.modelgen.language;

public interface HasAnnotations<Language> extends CreateBlock {

    boolean addAnnotation(AnnotationType<Language> annotation);
}
