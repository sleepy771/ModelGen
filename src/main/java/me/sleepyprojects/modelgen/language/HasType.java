package me.sleepyprojects.modelgen.language;

import me.sleepyprojects.modelgen.Meta;

public interface HasType {
    void setMeta(Meta type);

    Meta getMeta();
}
