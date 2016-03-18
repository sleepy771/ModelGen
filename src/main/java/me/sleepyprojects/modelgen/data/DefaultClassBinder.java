package me.sleepyprojects.modelgen.data;

import me.sleepyprojects.modelgen.ArgumentsDefinition;
import me.sleepyprojects.modelgen.Meta;
import me.sleepyprojects.modelgen.MethodDefinition;

public class DefaultClassBinder extends ClassBinder {

  static {
    new DefaultClassBinder();
  }

  @Override
  public void init() {
    register(MethodDefinition.class);
    register(Meta.class);
    register(ArgumentsDefinition.class);
    register(String.class);
  }
}
