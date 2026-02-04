package reflection;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException {

        Reflector reflector = new Reflector();
        Person person = new Person(
                "Alice",
                "89853815877",
                LocalDate.of(1987, 2, 13),
                189,
                33
        );
        AnotherClass anotherClass = new AnotherClass(
                "lalala",
                LocalDate.of(1678, 2, 4),
                3278678
        );

        reflector.printAllFields(person);
        System.out.println();
        reflector.printAllMethods(person);
        System.out.println();
        reflector.setFieldValue(person, "height", 211);
        System.out.println();
        reflector.callMethodWithNoParameters(person, "getPassword");
        reflector.callMethodWithOneParameter(person,
                "verifyPhoneNumber",
                String.class,
                "+7434354"
        );
        reflector.callMethodWithManyParameters(
                person,
                "checkWeight",
                new Class<?>[]{int.class, int.class},
                154,
                76
        );
        Person alex = (Person) reflector.createNewInstance(
                person,
                "Alex",
                "+893478597",
                LocalDate.of(2000, 6, 12),
                122,
                45
        );
        System.out.println();
        System.out.println(alex);
        reflector.printAllMethods(anotherClass);
    }
}