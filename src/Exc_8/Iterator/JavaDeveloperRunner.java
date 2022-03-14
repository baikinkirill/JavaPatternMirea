package Exc_8.Iterator;

public class JavaDeveloperRunner {
    public static void main(String[] args) {
        Console console = new Console();
        String[] skills = {"JS", "TS", "WebPack", "Jake", "React", "NodeJS"};

        Collection developer = new JavaDeveloper("Some Name", skills);

        Iterator iterator = developer.getIterator();
        console.log(((JavaDeveloper) developer).getName());

        while (iterator.hasNext())
            console.log((String) iterator.next());

    }
}

class Console {
    public void log(String str) {
        System.out.println(str);
    }
}