package com.imdnd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adam on 2017/5/6.
 */
public class FilterExample {


    public static void main(String[] args) {
        // before java8
        List<String> lines = Arrays.asList("spring", "node", "webpack");
        List<String> result = getFilterOutput(lines, "webpack");
        for (String temp : result) {
            System.out.println(temp);    //output : spring, node
        }

        // after java8
        result = lines.stream()                // convert list to stream
                .filter(line -> !"webpack".equals(line))     // we dont like wepback
                .collect(Collectors.toList());              // collect the output and convert streams to a List

        result.forEach(System.out::println);                //output : spring, node


        // filter object before java8
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person person = getStudentByName(persons, "jack");
        // Person{name='jack', age=20}
        System.out.println(person.toString());


        // filter object after java8
        Person result1 = persons.stream()                        // Convert to steam
                .filter(x -> "jack".equals(x.getName()))        // we want "jack" only
                .findAny()                                      // If 'findAny' then return found
                .orElse(null);                                  // If not found, return null
        // Person{name='jack', age=20}
        System.out.println(result1.toString());

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);
        // null
        System.out.println(result2);


        // filter and map
        String name = persons.stream()
                .filter(x -> "jack".equals(x.getName()))
                .map(Person::getName)                        //convert stream to String
                .findAny()
                .orElse("");
        // jack
        System.out.println("name : " + name);

        List<String> collect = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());

        //mkyong jack lawrence
        collect.forEach(System.out::println);

    }

    private static List<String> getFilterOutput(List<String> lines, String filter) {
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (!"webpack".equals(line)) {
                result.add(line);
            }
        }
        return result;
    }


    private static Person getStudentByName(List<Person> persons, String name) {

        Person result = null;
        for (Person temp : persons) {
            if (name.equals(temp.getName())) {
                result = temp;
            }
        }
        return result;
    }

}
