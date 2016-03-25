package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Meta;

public interface HasMetaType extends HasName {
    void setType(Meta meta);
}
