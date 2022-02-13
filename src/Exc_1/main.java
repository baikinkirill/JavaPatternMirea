package Exc_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class main {

    static class Student{
        private String fio = "";
        Student(String initFio){
            this.fio=initFio;
        }

        public String getFio() {
            return fio;
        }
    }

    public static void main(String[] args) {

        Function<String[], Map<String,List<Student>>> func = x -> {
            ArrayList result = new ArrayList();
            for (int i = 0; i < x.length; i++) {
                Student buff = new Student((String) x[i]);
                result.add(buff);
            }
            HashMap map = new HashMap<String, List>();
            map.put("Some group",result);
            return map;
        };

        String[] array = {"fd","dfdsf"};
        var res = func.apply(array);
        System.out.println(res);
    }
}