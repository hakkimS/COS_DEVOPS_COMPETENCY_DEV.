package general;

import org.joda.time.LocalTime;

public class getCurrentTime {
    public static void main(String[] args) {
        LocalTime currentTime = new LocalTime();
        System.out.println("<<<<<The current local time is: " + currentTime + ">>>>>>>");
        System.out.println(HelloWorld.sayHello());
    }
}
