package me.sleepyprojects.modelgen.data;

import java.util.HashMap;

/**
 * Created by qualityunit on 15.3.2016.
 */
public abstract class ClassBinder {

  private HashMap<String, Class<?>> bindingMap;
  private static ClassBinder INSTANCE;


  protected ClassBinder() {
    if (INSTANCE != null) {
      throw new RuntimeException("Can not instantiate another binder");
    }
    bindingMap = new HashMap<>();
    init();
    INSTANCE = this;
  }

  public abstract void init();

  protected void register(String name, Class<?> type) {
    if (bindingMap.containsKey(name)) {
      if (bindingMap.get(name) != type) {
        throw new RuntimeException("can not rebind");
      }
    }
    bindingMap.put(name, type);
  }

  protected void register(Class<?> type) {
    this.register(type.getSimpleName(), type);
  }

  public Class<?> get(String bound) {
    if (bound.endsWith("[]")) {
      bound = bound.substring(0, bound.length() - 2);
    }
    if (!bindingMap.containsKey(bound)) {
      throw new RuntimeException("Can not find class");
    }
    return this.bindingMap.get(bound);
  }

  public static ClassBinder getInstance() {
    return INSTANCE;
  }
}
