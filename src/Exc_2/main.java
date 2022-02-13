package Exc_2;

import java.util.List;

public class main {

    public static void main(String[] args) {
        operator op = new operator();
        List<Human> list = op.generateHumanList(5);
        op.outList(list);
        System.out.println("\nSorted by birthday:");
        op.outList(op.sortByBirthDay(list));
        System.out.println("\nFiltered by age:");
        op.outList(op.filterByAge(list));
        System.out.println("\nSorted by weight:");
        op.outList(op.sortByWeight(list));
        System.out.println("Get big string: ");
        System.out.println(op.getBigStringFromNames(list));
    }
}
