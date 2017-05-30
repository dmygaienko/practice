package com.mygaienko.common.stream;

import com.mygaienko.common.functional.BatchCollector;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.mygaienko.common.util.TestUtils.getArrayList;
import static com.mygaienko.common.util.TestUtils.getArrayListOfInts;
import static java.util.Arrays.asList;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
    public void testMapWithEmptyList() {
        List<Integer> collect = Collections.<Integer>emptyList().stream()
                .map(x -> x + 1)
                .collect(toList());

        assertThat(collect, empty());
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
        List<Pair<Integer, Integer>> pairs = asList(
                Pair.of(0, 1), Pair.of(1, 2), Pair.of(0, 3), Pair.of(0, 4), Pair.of(0, 1), Pair.of(0, 1));

        ConcurrentMap<Integer, List<Integer>> collected = pairs.stream().collect(
                Collectors.groupingByConcurrent(
                        tuple -> tuple.getLeft(),
                        Collectors.mapping(tuple -> tuple.getRight(), Collectors.toList())));

        System.out.println(collected);
    }

    @Test
    public void testStreamWithJoinTypes() {
        List<JoinObject> joins = asList(
                new JoinObject("1", JoinType.INNER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.INNER),

                new JoinObject("2", JoinType.INNER),
                new JoinObject("2", JoinType.OUTER),
                new JoinObject("2", JoinType.INNER),

                new JoinObject("3", JoinType.OUTER),
                new JoinObject("3", JoinType.INNER)
        );

        boolean valid = joins.stream().collect(
                Collectors.groupingBy(
                        JoinObject::getTableId,
                        mapping(e -> e.getType(), toSet())
                ))
                .values().stream()
                .mapToInt(e -> e.size())
                .max().getAsInt() == 1;


        System.out.println(valid);
    }

    @Test
    public void testStreamWithJoinTypes1() {
        List<JoinObject> joins = getJoins();

        Optional<Integer> max = joins.stream().collect(
                Collectors.groupingBy(
                        JoinObject::getTableId,
                        collectingAndThen(
                                mapping(join -> join.getType(), Collectors.toSet()),
                                Set::size)
                ))
                .values()
                .stream()
                .max(naturalOrder());

        System.out.println(max.get());
    }

    @Test(expected = IllegalStateException.class)
    public void testStreamWithFindFirst() {
        List<JoinObject> joins = getJoins();

        joins.stream()
                .collect(
                        Collectors.groupingBy(
                                JoinObject::getTableId,
                                mapping(e -> e.getType(), toSet())
                        ))
                .values()
                .stream()
                .map(set -> set.size() > 1)
                .findFirst()
                .ifPresent(result -> {
                    throw new IllegalStateException();
                });
    }

    private List<JoinObject> getJoins() {
        return asList(
                new JoinObject("1", JoinType.INNER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.OUTER),
                new JoinObject("1", JoinType.INNER),

                new JoinObject("2", JoinType.INNER),
                new JoinObject("2", JoinType.OUTER),
                new JoinObject("2", JoinType.INNER),

                new JoinObject("3", JoinType.OUTER),
                new JoinObject("3", JoinType.INNER),
                new JoinObject("3", JoinType.NO_JOINS)
        );
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

    }

    private enum JoinType {
        INNER, NO_JOINS, OUTER
    }

    @Test
    public void testGroupingWithReducing() {
        List<Pair<Integer, Integer>> pairs = asList(
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
    public void testPartitioningBy() {
        Map<Boolean, List<Integer>> collect = getArrayListOfInts(0, 10).stream()
                .collect(partitioningBy(number -> number < 5, toList()));

        System.out.println(collect);
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
        List<Integer> list = asList(1, 4, 45, 12, 5, 6, 9, 101);
        Comparator<Integer> c1 = (x, y) -> x - y;
        Comparator<Integer> c2 = c1.reversed();
        System.out.println("Smallest = " + list.stream().min(c1).get());
        System.out.println("Largest = " + list.stream().min(c2).get());
    }

    @Test
    public void testForkJoinPool() throws Exception {
        System.out.println("default ForkJoinPool with 4");
        IntStream.range(1, 1_000_000)
                .parallel()
                .mapToObj(integer -> Thread.currentThread().getName())
                .distinct().forEach(System.out::println);

        System.out.println("custom ForkJoinPool");
        ForkJoinPool forkJoinPool = new ForkJoinPool(8);
        forkJoinPool.submit(() -> {
                    System.out.println("> " + Thread.currentThread().getName());
                    IntStream.range(1, 1_000_000)
                            .parallel()
                            .mapToObj(integer -> Thread.currentThread().getName())
                            .distinct().forEach(System.out::println);
                }
        ).get();
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

    @Test
    public void testZip1() {
        List<String> chars = asList("A", "B", "C");
        List<Integer> ints = asList(1, 2, 3);

        List<String> zipped = StreamUtil
                .zip(chars.stream(), ints.stream(), (a, b) -> a + " " + b)
                .collect(toList());

        System.out.println(zipped);
    }

    @Test
    public void testZip2() {
        List<String> chars = asList("A", "B", "C", "D");
        List<Integer> ints = asList(1, 2, 3);

        List<String> zipped = StreamUtil
                .zip(chars.stream(), ints.stream(), (a, b) -> a + " " + b)
                .collect(toList());

        System.out.println(zipped);
    }

    @Test
    public void testGroupingByAndReducing() {

        Map<Key, List<String>> m = getKeyListMap();

       /* Map<String, List<String>> collect = m.entrySet().stream()
                .collect(
                        groupingBy(
                                entry -> entry.getKey().getUserName(),
                                Collectors.mapping(
                                        Map.Entry::getValue,
                                        Collectors.reducing(
                                                new ArrayList<String>(),
                                                (a, b) -> {
                                                    ArrayList<String> strings = new ArrayList<>();
                                                    strings.addAll(a);
                                                    strings.addAll(b);
                                                    return strings;
                                                })
                                )
                        )
                );*/

        Map<String, List<String>> collect = m.entrySet().stream()
                .collect(
                        groupingBy(
                                entry -> entry.getKey().getUserName(),
                                Collectors.reducing(
                                        new ArrayList<String>(),
                                        Map.Entry::getValue,
                                        (a, b) -> {
                                            ArrayList<String> strings = new ArrayList<>();
                                            strings.addAll(a);
                                            strings.addAll(b);
                                            return strings;
                                        })
                        )
                );

        System.out.println(collect);
    }

    @Test
    public void testForEachAndCompute() {
        Map<Key, List<String>> m = getKeyListMap();

        Map<String, List<String>> collect = new HashMap<>();

        m.forEach((key, countries) -> {
            String userName = key.getUserName();
            collect.computeIfPresent(userName, (username, mergedCountries) -> {
                mergedCountries.addAll(countries);
                return mergedCountries;
            });
            collect.computeIfAbsent(userName, username -> new ArrayList<>(countries));
        });

        System.out.println(collect);
    }

    @Test
    public void testForEachAndMerge() {
        Map<Key, List<String>> m = getKeyListMap();

        Map<String, List<String>> collect = new HashMap<>();

        m.forEach((key, value) ->
                collect.merge(key.getUserName(), value, (result, next) -> {
                    result.addAll(next);
                    return result;
                }));

        System.out.println(collect);
    }

    @Test
    public void findFirst() throws Exception {
        ArrayList<Integer> numbers = getArrayListOfInts(1, 5);

        Integer firstMinimal = numbers.stream()
                .peek(System.out::println)
                .filter(number -> number > 3)
                .findFirst()
                .get();

        System.out.println(firstMinimal);
    }

    @Test
    public void anyMatch() throws Exception {
        ArrayList<Integer> numbers = getArrayListOfInts(1, 10);

        Boolean foundMatch = numbers.stream()
                .map(number -> number * 10)
                .anyMatch(number -> number > 50);

        System.out.println(foundMatch);
    }

    @Test
    public void allMatch() throws Exception {
        ArrayList<Integer> numbers = getArrayListOfInts(1, 10);

        Boolean allMatch = numbers.stream()
                .peek(System.out::println)
                .allMatch(number -> number >= 1 && number <= 10);

        System.out.println(allMatch);
    }

    @Test
    public void noneMatch() throws Exception {
        ArrayList<Integer> numbers = getArrayListOfInts(1, 5);

        Boolean noneMatch = numbers.stream()
                .peek(System.out::println)
                .noneMatch(number -> number > 5);

        System.out.println(noneMatch);
    }

    @Test
    public void count() throws Exception {
        ArrayList<Integer> numbers = getArrayListOfInts(1, 10);

        long count = numbers.stream()
                .skip(5)
                .filter(number -> number % 2 == 0)
                .peek(System.out::println)
                .count();

        System.out.println(count);
    }

    @Test
    public void findAnyOnFiniteStream() throws Exception {
        Optional<Integer> first = IntStream.range(1, 100)
                .boxed()
                .skip(10)
                .parallel()
                .filter(number -> number % 2 == 0)
                .findAny();

        System.out.println("first: " + first.orElse(-1));
    }

    @Test
    public void findAny() throws Exception {
        Optional<Integer> first = Stream.iterate(1, i -> i + 1)
                .skip(10)
                .parallel()
                .filter(number -> number % 2 == 0)
                .findAny();

        System.out.println("first: " + first.orElse(-1));

        Optional<Integer> any = Stream.iterate(1, i -> i + 1)
                .skip(10)
                .filter(number -> number % 2 == 0)
                .findAny();

        System.out.println("any: " + any.orElse(-1));
    }

    @Test
    public void limit() throws Exception {
        List<Integer> collect = getArrayListOfInts(1, 10).stream()
                .peek(System.out::println)
                .limit(5)
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void skip() throws Exception {
        List<Integer> collect = getArrayListOfInts(1, 10).stream()
                .peek(System.out::println)
                .skip(5)
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void distinct() throws Exception {
        ArrayList<Integer> list = getArrayListOfInts(1, 10);
        list.addAll(getArrayListOfInts(1, 10));
        System.out.println(list);

        List<Integer> collect = list.stream()
                .peek(System.out::println)
                .distinct()
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void sorted() throws Exception {
        List<Integer> list = asList(10, 9, 8, 1, 2, 3, 4, 5, 5, 4, 11);
        List<Integer> collect = list.stream()
                .peek(System.out::println)
                .sorted()
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void sortedWithNull() throws Exception {
        List<Integer> list = asList(null, 10, 9, 8, 1, 2, 3, 4, 5, 5, 4, 11);
        List<Integer> collect = list.stream()
                .peek(System.out::println)
                .sorted(nullsFirst(naturalOrder()))
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void sortedReversedWithNull() throws Exception {
        List<Integer> list = asList(null, 10, 9, 8, 1, 2, 3, 4, 5, 5, 4, 11);
        List<Integer> collect = list.stream()
                .peek(System.out::println)
                .sorted(nullsLast(reverseOrder()))
                .collect(toList());

        System.out.println(collect);
    }

    @Test
    public void iterate() throws Exception {
        Integer filtered = Stream.iterate(1000, i -> i + 1)
                .filter(i -> i % 2 == 0)
                .peek(i -> System.out.println(i))
                .skip(5)
                .findFirst()
                .orElse(0);

        System.out.println(filtered);
    }

    @Test
    public void batchCollector() throws Exception {
        IntStream.range(0, 50)
                .parallel()
                .boxed()
                .collect(new BatchCollector<>(System.out::println, 5));
    }

    @Test
    public void multiplyLists() throws Exception {
        List<List<String>> pathways1 = new ArrayList<>();
        pathways1.add(getArrayList(1, 3));
        pathways1.add(getArrayList(4, 6));

        List<List<String>> pathways2 = new ArrayList<>();
        pathways2.add(getArrayList(7, 9));
        pathways2.add(getArrayList(10, 12));
        pathways2.add(getArrayList(13, 15));


        List<List<String>> collect = pathways1.stream()
                .flatMap(path1 ->
                        pathways2.stream()
                                .map(path2 -> Stream.concat(path1.stream(), path2.stream()).collect(toList()))
                ).collect(toList());

        System.out.println(collect);
    }

    @Test
    public void testToMapAsGroupingBy() throws Exception {
        final Map<String, List<String>> collect = getArrayList(0, 10).stream()
                .collect(
                        toMap(i -> Integer.valueOf(i) % 2 == 0 ? "0" : "1",
                                Collections::singletonList
                                , (a, b) -> {
                                    List<String> result = new ArrayList<>();
                                    result.addAll(a);
                                    result.addAll(b);
                                    return result;
                                }));
        System.out.println(collect);
        assertThat(collect, hasEntry("0", asList("0", "2", "4", "6", "8", "10")));
        assertThat(collect, hasEntry(is("0"), is(asList("0", "2", "4", "6", "8", "10"))));
        assertThat(collect, hasEntry(is("1"), everyItem(isIn(asList("1", "3", "5", "7", "9")))));
    }

    private Map<Key, List<String>> getKeyListMap() {
        return new HashMap<Key, List<String>>() {{
            put(new Key("u1"), new ArrayList<>(asList("u1a1", "u1a2")));
            put(new Key("u2"), new ArrayList<>(asList("u2a1", "u2a2")));
            put(new Key("u1"), new ArrayList<>(asList("u1a3", "u1a4")));
        }};
    }

    static class Key {
        private String userName;

        public Key(String userName) {
            this.userName = userName;
        }

        String getUserName() {
            return userName;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "userName='" + userName + '\'' +
                    '}';
        }
    }


}
