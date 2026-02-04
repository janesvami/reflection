package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflector {
    public void printAllFields(Object object) throws IllegalAccessException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            final Object value = field.get(object);
            System.out.println("FIELD " + field.getName() + ", TYPE " + field.getType() + ", VALUE: " + value);
        }
    }

    public void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
        System.out.println("Field " + fieldName + " has been changed with value " + value);
    }

    public void printAllMethods(Object object) {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            method.setAccessible(true);
            System.out.println(
                    "METHOD: " + method.getName() +
                            ", REQUIRED TYPE: " + method.getReturnType() +
                            ", PARAMETERS TYPES: " + Arrays.toString(method.getParameterTypes())
            );
        }
    }

    public void callMethodWithNoParameters(Object object, String methodName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Method methodField = clazz.getDeclaredMethod(methodName);
        methodField.setAccessible(true);
        final Object invoke = methodField.invoke(object);
        System.out.println(invoke);
    }

    public void callMethodWithOneParameter(Object object, String methodName, Class<?> parameterType, Object value) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Method methodField = clazz.getDeclaredMethod(methodName, parameterType);
        methodField.setAccessible(true);
        final Object invoke = methodField.invoke(object, value);
        System.out.println(invoke);
    }

    public void callMethodWithManyParameters(Object object, String methodName, Class<?>[] parameterTypes, Object... args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Method methodField = clazz.getDeclaredMethod(methodName, parameterTypes);
        methodField.setAccessible(true);
        final Object invoke = methodField.invoke(object, args);
        System.out.println(invoke);
    }

    public Object createNewInstance(Object object, Object... values) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        validateAnnotation(object);
        final Class<?> clazz = object.getClass();
        final Class<?>[] paramTypes = new Class<?>[values.length];
        for (int i = 0; i < values.length; i++) {
            paramTypes[i] = getTypeForConstructor(values[i]);
        }
        final Constructor<?> constructor = clazz.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(values);
    }

    private static void validateAnnotation(Object object) {
        final Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(Investigable.class)) {
            throw new IllegalArgumentException("Class " + clazz + " is not investigable");
        }
    }

    private Class<?> getTypeForConstructor(Object value) {
        if (value instanceof Integer) return int.class;
        if (value instanceof Double) return double.class;
        if (value instanceof Boolean) return boolean.class;
        if (value instanceof Long) return long.class;
        if (value instanceof Float) return float.class;
        if (value instanceof Character) return char.class;
        if (value instanceof Byte) return byte.class;
        if (value instanceof Short) return short.class;
        return value.getClass();
    }
}
