package me.sleepyprojects.modelgen.data;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommonTypeManager {

    private Map<String, MethodCallType> calls;

    CommonTypeManager() {
        calls = new HashMap<>();
    }

    boolean insert(final @NotNull MethodCallType template) {
        final String key = createCall(template);
        if (calls.containsKey(key)) {
            return false;
        }
        calls.put(key, template);
        return true;
     }

    public MethodCallType getMethodCall(String type, String methodName) {
        final String key = createCall(type, methodName);
        if (!calls.containsKey(key)) {
            throw new RuntimeException(String.format("Method %s does not exist", key));
        }
        return calls.get(key);
    }

    private static String createCall(final @NotNull String className, final @NotNull String methodName) {
        return className + "::" + methodName;
    }

    private static String createCall(final @NotNull MethodCallType callType) {
        return createCall(callType.getClassName(), callType.getMethodName());
    }
}
