package Exc_2;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Human {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDay, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public int getWeight() {
        return weight;
    }
}


class operator {

    String getBigStringFromNames(List<Human> list) {
        var ref = new Object() {
            String result = "";
        };

        Stream<Human> stream = list.stream();
        list.stream()
                .forEach((o1) -> {
                    ref.result +=o1.getFirstName()+" ";
                });

        return ref.result;
    }

    List<Human> sortByWeight(List<Human> list) {
        Stream<Human> stream = list.stream();
        return list.stream().sorted((o1, o2) -> Integer.compare(o1.getWeight(), o2.getWeight()))
                .collect(Collectors.toList());
    }

    List<Human> filterByAge(List<Human> list) {
        Stream<Human> stream = list.stream();
        return list.stream().filter((e) -> e.getAge() < 50)
                .collect(Collectors.toList());
    }

    List<Human> sortByBirthDay(List<Human> list) {
        Stream<Human> stream = list.stream();
        return list.stream().sorted((o1, o2) -> o1.getBirthDay().compareTo(o2.getBirthDay()))
                .collect(Collectors.toList());
    }

    List<Human> generateHumanList(int n) {
        List<Human> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Human buff = new Human(
                    (int) Math.round(Math.random() * 100),
                    generateRandomWords(1)[0],
                    generateRandomWords(1)[0],
                    generateDate(),
                    (int) Math.round(Math.random() * 100)
            );
            result.add(buff);
        }

        return result;
    }

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }


    LocalDate generateDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return randomDate;
    }

    void outList(List<Human> list) {
        for (int i = 0; i < list.size(); i++) {
            Human buff = list.get(i);
            System.out.println("Age: " + buff.getAge());
            System.out.println("Name: " + buff.getLastName() + " " + buff.getFirstName());
            System.out.println("Birthday: " + buff.getBirthDay());
            System.out.println("Weight: " + buff.getWeight());
            System.out.println();
        }
    }
}
