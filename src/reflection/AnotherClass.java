package reflection;

import java.time.LocalDate;

public class AnotherClass {
    private String someText;
    private LocalDate someDay;
    private int someNumber;

    public AnotherClass(String someText, LocalDate someDay, int someNumber) {
        this.someText = someText;
        this.someDay = someDay;
        this.someNumber = someNumber;
    }

    private String getPassword () {
        return "verySecretPass";
    }
}
