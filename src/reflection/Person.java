package reflection;

import java.time.LocalDate;

@Investigable
public class Person {

    private String name;
    private String phoneNumber;
    private final LocalDate birthday;
    private int height;
    private int weight;

    public Person(String name, String phoneNumber, LocalDate birthday, int height, int weight) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
    }

    private String getPassword() {
        return "verySecretPass";
    }

    private int countAge(LocalDate birthday) {
        final int birthdayYear = birthday.getYear();
        final int currentYear = LocalDate.now().getYear();
        final int result = currentYear - birthdayYear;
        final int currentMonth = LocalDate.now().getMonthValue();
        final int birthdayMonth = birthday.getMonthValue();
        final int birthdayDate = birthday.getDayOfMonth();
        final int currentDate = LocalDate.now().getDayOfMonth();
        if (currentMonth < birthdayMonth || (currentMonth == birthdayMonth && birthdayDate < currentDate)) {
            return result - 1;
        }
        return result;
    }

    private boolean verifyPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) return false;
        if (phoneNumber.length() != 12) return false;
        if (!phoneNumber.startsWith("+")) return false;
        return phoneNumber.matches("^[0-9+]+$");
    }

    private String checkWeight(int height, int weight) {
       final int index = height - weight;
        if (index > 125) {
            return "Weight deficiency";
        } else if (index < 85) {
            return "Overweight!";
        }
        return "Normal weight";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}