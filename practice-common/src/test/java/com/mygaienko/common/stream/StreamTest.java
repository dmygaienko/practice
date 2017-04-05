package com.mygaienko.common.stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by dmygaenko on 28/01/2016.
 */
public class StreamTest {

    private List<String> strings;

    private Stream<String> stringStream;

    @Before
    public void init() {
        strings = new ArrayList<String>();
        strings.add("string 1");
        strings.add("string 2");
        strings.add("string 3");
        strings.add("string 4");
        strings.add("string 5");
        strings.add("string 6");

        stringStream = strings.stream();
    }

    @Test
    public void testForEach() {
        stringStream.forEach(x -> System.out.println(x));
    }

    @Test
    public void testMap() {
        Stream<String> mappedStream = stringStream.map(x -> x + 1);

        System.out.println(Arrays.toString(mappedStream.toArray()));
    }

    @Test
    public void testFlatMap() {
        Stream<String> mappedStream = stringStream.flatMap(x -> Arrays.stream(x.split(" ")));

        System.out.println(Arrays.toString(mappedStream.toArray()));
    }

    @Test
    public void testFilter() {
        Stream<String> mappedStream = stringStream.filter(x -> x.matches("string [1|2|3|4|5]"));

        System.out.println(Arrays.toString(mappedStream.toArray()));
    }

    @Test
    public void testMatching() {
        "string 6".matches("string [1|2|3|4|5]");

    }

    @Test
    public void testDistinct() {
        strings.add("string 6");
        strings.add("string 6");

        stringStream = strings.stream();

        System.out.println(Arrays.toString(stringStream.distinct().toArray()));
    }

    @Test
    public void testReduce() {
        Pattern pattern = Pattern.compile("(^string\\s)(\\d)");

        final BigDecimal total = strings.stream()
                .map(getNumericValue(pattern))
                .filter(price -> price.compareTo(BigDecimal.valueOf(3)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertEquals(new BigDecimal("13.5"), total);
    }

    private Function<String, BigDecimal> getNumericValue(Pattern pattern) {
        return value -> {
            Matcher matcher = pattern.matcher(value);
            matcher.matches();
            return new BigDecimal(matcher.toMatchResult().group(2));
        };
    }

    @Test
    public void testReduceOptional() {
        Pattern pattern = Pattern.compile("(^string\\s)(\\d)");

        final Optional<BigDecimal> total = strings.stream()
                .map(getNumericValue(pattern))
                .filter(price -> price.compareTo(BigDecimal.valueOf(3)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal::add);

        assertEquals(new BigDecimal("13.5"), total.orElse(BigDecimal.ZERO));
    }

    @Test
    public void testGroupingWithMapping() {
        List<Pair<Integer, Integer>> pairs = Arrays.asList(
                Pair.of(0, 1), Pair.of(1, 2), Pair.of(0, 3), Pair.of(0, 4), Pair.of(0, 1), Pair.of(0, 1));

        ConcurrentMap<Integer, List<Integer>> collected = pairs.stream().collect(
                Collectors.groupingByConcurrent(
                        tuple -> tuple.getLeft(),
                        Collectors.mapping(tuple -> tuple.getRight(), Collectors.toList())));

        System.out.println(collected);
    }

    @Test
    public void testStreamWithJoinTypes() {
        List<JoinObject> joins = Arrays.asList(
                new JoinObject("1", JoinType.INNER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.INNER),

                new JoinObject("2", JoinType.INNER),
                new JoinObject("2", JoinType.OUTER),
                new JoinObject("2", JoinType.INNER),

                new JoinObject("3", JoinType.OUTER)
                );

        Map<String, Integer> collected = joins.stream().collect(
                Collectors.groupingBy(
                        JoinObject::getTableId,
                        collectingAndThen(toSet(), Set::size)
                ));

        System.out.println(collected);
    }

    private static class JoinObject {
        private String tableId;
        private JoinType type;

        public JoinObject(String tableId, JoinType type) {
            this.tableId = tableId;
            this.type = type;
        }

        public String getTableId() {
            return tableId;
        }

        public JoinType getType() {
            return type;
        }

        @Override
        public String toString() {
            return "JoinObject{" +
                    "tableId='" + tableId + '\'' +
                    ", type=" + type +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JoinObject that = (JoinObject) o;

            if (tableId != null ? !tableId.equals(that.tableId) : that.tableId != null) return false;
            return type == that.type;

        }

        @Override
        public int hashCode() {
            int result = tableId != null ? tableId.hashCode() : 0;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }
    }

    private enum JoinType {
        INNER, OUTER
    }

    @Test
    public void testGroupingWithReducing() {
        List<Pair<Integer, Integer>> pairs = Arrays.asList(
                Pair.of(0, 1), Pair.of(1, 2), Pair.of(0, 3), Pair.of(0, 4), Pair.of(0, 1), Pair.of(0, 1));

        Map<Integer, Integer> collect = pairs.stream().collect(
                groupingBy(tuple -> tuple.getLeft(),
                        reducing(0,                             // identity
                                tuple -> tuple.getRight(),      // mapper
                                (a, b) -> a > b ? a : b         // comparator
                        )
                ));

        System.out.println(collect);
    }

    @Test
    public void testFilterAndForeachInPreinitList() {
        List<Person> olderThan20 = new ArrayList<>();

        createPersons().stream()
                .filter(person -> person.getAge() > 20)
                .forEach(olderThan20::add);

        System.out.println("People older than 20: " + olderThan20);
    }

    @Test
    public void testCollect() {
        List<Person> olderThan20 = createPersons().stream()
                .filter(person -> person.getAge() > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 20: " + olderThan20);
    }

    @Test
    public void testCollector() {
        List<Person> olderThan20 = createPersons().stream()
                .filter(person -> person.getAge() > 20)
                .collect(toList());
        System.out.println("People older than 20: " + olderThan20);
    }

    @Test
    public void testCollectGroupingBy() {
        Map<Integer, List<Person>> peopleByAge = createPersons().stream()
                .collect(groupingBy(Person::getAge));
        System.out.println("People grouped by age: " + peopleByAge);
    }

    @Test
    public void testCollectGroupingByAndMapping() {
        Map<Integer, List<String>> nameOfPeopleByAge = createPersons().stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    @Test
    public void testCollectGroupingByAndReducing() {
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonInEachAlphabet = createPersons().stream()
                .collect(groupingBy(person -> person.getName().charAt(0),
                        reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person in each alphabet:");
        System.out.println(oldestPersonInEachAlphabet);
    }

    @Test
    public void testDirectoryListing() throws IOException {
        Files.list(Paths.get(".")).forEach(System.out::println);
        Files.list(Paths.get("src/main")).forEach(System.out::println);
    }

    @Test
    public void testOnlyDirectoryListing() throws IOException {
        Files.list(Paths.get("."))
                .filter(Files::isDirectory) //method reference
                .forEach(System.out::println);
    }

    @Test
    public void testNotDirectoryListing() throws IOException {
        Files.list(Paths.get("."))
                .filter((path) -> !Files.isDirectory(path)) //lambda
                .forEach(System.out::println);
    }

    @Test
    public void testDirectoryStream() throws IOException {
        Files.newDirectoryStream(
                Paths.get("src/test/java/com/mygaienko/common/stream"),
                path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);
    }

    @Test
    public void testStreamWithComparator() throws Exception {
        List<Integer> list = Arrays.asList(1, 4, 45, 12, 5, 6, 9, 101);
        Comparator<Integer> c1 = (x, y) -> x - y;
        Comparator<Integer> c2 = c1.reversed();
        System.out.println("Smallest = " + list.stream().min(c1).get());
        System.out.println("Largest = " + list.stream().min(c2).get());
    }

    //page 58 Functional Programming in Java

    private List<Person> createPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("John", 20));
        persons.add(new Person("Jane", 21));
        persons.add(new Person("Sara", 21));
        persons.add(new Person("Greg", 35));
        return persons;
    }

    private class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


}
