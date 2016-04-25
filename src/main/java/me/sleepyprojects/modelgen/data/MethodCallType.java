package me.sleepyprojects.modelgen.data;

import me.sleepyprojects.modelgen.Meta;

public class MethodCallType {
    private final String className;
    private final String methodName;

    private Meta classType;
    private final String template;

    public MethodCallType(String className, String methodName, String template) {
        this.className = className;
        this.methodName = methodName;
        this.template = template;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
