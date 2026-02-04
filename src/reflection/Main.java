package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Person person = new Person(
                "Alice",
                "6757665",
                LocalDate.of(2024, 6, 15),
                160,
                78
        );
        Class<?> clazz = person.getClass();
        Field[] allFields = clazz.getDeclaredFields();

        for (Field field : allFields) {
            field.setAccessible(true);
            Object value = field.get(person);
            System.out.println("FIELD " + field.getName() + ", TYPE " + field.getType() + ", VALUE: " + value);
        }

        System.out.println("\"");
        System.out.println(person);

        Field nameField = clazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(person, "Mary");
        Field phoneNumberField = clazz.getDeclaredField("phoneNumber");
        phoneNumberField.setAccessible(true);
        phoneNumberField.set(person, "+79568633422");
        Field birthdayField = clazz.getDeclaredField("birthday");
        birthdayField.setAccessible(true);
        birthdayField.set(person, LocalDate.of(2000, 5, 3));
        Field heightField = clazz.getDeclaredField("height");
        heightField.setAccessible(true);
        heightField.set(person, 170);
        Field weightField = clazz.getDeclaredField("weight");
        weightField.setAccessible(true);
        weightField.set(person, 55);
        System.out.println(person);
        System.out.println("\"");

        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method method : allMethods) {
            method.setAccessible(true);
            System.out.println(
                    "METHOD: " + method.getName() +
                            ", REQUIRED TYPE: " + method.getReturnType() +
                            ", PARAMETERS TYPES: " + Arrays.toString(method.getParameterTypes())
            );
        }
        System.out.println("\"");

        Method getPasswordMethod = clazz.getDeclaredMethod("getPassword", new Class[0]);
        getPasswordMethod.setAccessible(true);
        System.out.println(getPasswordMethod.invoke(person, new Object[0]));
        System.out.println("\"");

        Method countAgeMethod = clazz.getDeclaredMethod("countAge", LocalDate.class);
        countAgeMethod.setAccessible(true);
        System.out.println(countAgeMethod.invoke(person, LocalDate.of(1998, 7, 4)));
        System.out.println("\"");

        Method verifyPhoneNumberMethod = clazz.getDeclaredMethod("verifyPhoneNumber", String.class);
        verifyPhoneNumberMethod.setAccessible(true);
        System.out.println(verifyPhoneNumberMethod.invoke(person, "+74564"));
        System.out.println(verifyPhoneNumberMethod.invoke(person, "89143345665"));
        System.out.println(verifyPhoneNumberMethod.invoke(person, "+7 985 5454343"));
        System.out.println(verifyPhoneNumberMethod.invoke(person, "+78153212332"));
        System.out.println("\"");

        Method checkWeightMethod = clazz.getDeclaredMethod("checkWeight", int.class, int.class);
        checkWeightMethod.setAccessible(true);
        System.out.println(checkWeightMethod.invoke(person, 170, 35));
        System.out.println(checkWeightMethod.invoke(person, 200, 150));
        System.out.println(checkWeightMethod.invoke(person, 170, 57));
        System.out.println("\"");

        Constructor<Person> constructor = Person.class.getConstructor(String.class, String.class, LocalDate.class, int.class, int.class);
        Person secondPerson = constructor.newInstance("Lisa", "898394587", LocalDate.of(2002, 1, 23), 176, 54);
        System.out.println(secondPerson);
        System.out.println("\"");
    }
}