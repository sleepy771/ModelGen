package me.sleepyprojects.modelgen.data;

import java.io.File;
import java.util.HashMap;

public class TemplateManager {

  private HashMap<String, TemplateModel> modelMap;
  private static TemplateManager INSTANCE;

  private TemplateManager() {
    modelMap = new HashMap<>();
    INSTANCE = this;
  }

  public TemplateModel get(String id) {
    if (!modelMap.containsKey(id)) {
      throw new RuntimeException(String.format("Template %s does not exist!", id)); // Create manager exception
    }
    return modelMap.get(id);
  }

  void loadTemplates(Iterable<TemplateModel> models) {
    for (TemplateModel model : models) {
      register(model);
    }
  }

  public void register(TemplateModel model) {
    if (modelMap.containsKey(model.getId())) {
      throw new RuntimeException(String.format("Template %s already registered!", model.getId()));
    }
    modelMap.put(model.getId(), model);
  }

  void unregister(TemplateModel model) {
    modelMap.remove(model.getId());
  }

  public static TemplateManager create(File file) {
    if (TemplateManager.INSTANCE != null) {
      return TemplateManager.INSTANCE;
    }
    final XMLLangParser parser = new XMLLangParser(file);
    final TemplateManager manageris = new TemplateManager();
    manageris.loadTemplates(parser.getModels());
    return manageris;
  }

  public static TemplateManager getInstance() {
    if (TemplateManager.INSTANCE == null) {
      throw new RuntimeException("Template manager was not instantiated");
    }
    return TemplateManager.INSTANCE;
  }
}
