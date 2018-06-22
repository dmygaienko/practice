package com.mygaienko.common.pivot;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

/**
 * Created by enda1n on 28.05.2017.
 */
public class PivotTest {

    @Test
    public void testPivot() throws Exception {
        final Map<Team, Map<Position, List<Player>>> grouped = groupByTeamAndPosition(buildPlayers());
        System.out.println(grouped);
        assertThat(grouped, hasKey(Team.DYNAMO));
        assertThat(grouped, hasKey(Team.SHAKHTAR));
    }

    @Test
    public void testPivotBy() throws Exception {
        final Map<Team, Map<Position, Long>> grouped = groupByTeamAndPositionAndFunction(buildPlayers());
        System.out.println(grouped);
        assertThat(grouped, hasKey(Team.DYNAMO));
        assertThat(grouped, hasKey(Team.SHAKHTAR));
    }

    @Test
    public void testGroupByClassifiers() throws Exception {
        final Map<Position, Map<Team, Long>> grouped =
                groupByClassifiers(buildPlayers(),
                        Player::getPosition,
                        Player::getTeam);

        System.out.println(grouped);
        assertThat(grouped, hasKey(Position.DEFENDER));
        assertThat(grouped, hasKey(Position.GOALKEEPER));
    }

    @Test
    public void testComparing() throws Exception {
        final Player mostPaid = buildPlayers().stream()
                .collect(
                        collectingAndThen(
                                maxBy(Comparator.comparing(Player::getSalary)),
                                optional -> optional.orElse(new Player())
                        )
                );
        System.out.println(mostPaid);
        assertThat(mostPaid.getSurname(), is("Yarmolenko"));
    }

    static <T1, T2> Map<T1, Map<T2, Long>> groupByClassifiers(
            List<Player> players,
            Function<Player, T1> classifier1,
            Function<Player, T2> classifier2) {

        return players.stream().collect(
                groupingBy(
                        classifier1,
                        groupingBy(
                                classifier2,
                                mapping(
                                        player -> player.getSalary(),
                                        collectingAndThen(
                                                Collectors.maxBy(Comparator.naturalOrder()),
                                                (Optional<Long> optional) -> optional.orElse(-1L)
                                        )
                                )
                        )
                )
        );
    }

    static Map<Team, Map<Position, Long>> groupByTeamAndPositionAndFunction(List<Player> players) {
        return players.stream().collect(
                groupingBy(
                        player -> player.getTeam(),
                        groupingBy(
                                player -> player.getPosition(),
                                mapping(
                                        player -> player.getSalary(),
                                        collectingAndThen(
                                                Collectors.maxBy(Comparator.naturalOrder()),
                                                (Optional<Long> optional) -> optional.orElse(-1L)
                                        )
                                )
                        )
                )
        );
    }

    static Map<Team, Map<Position, List<Player>>> groupByTeamAndPosition(List<Player> players) {
        return players.stream().collect(
                groupingBy(
                        player -> player.getTeam(),
                        groupingBy(
                                player -> player.getPosition()
                        )
                )
        );
    }

    static List<Player> buildPlayers() {
        return Arrays.asList(
                new Player("Shovkovskiy", Team.DYNAMO, Position.GOALKEEPER, 1_200_000),
                new Player("Vashyk", Team.DYNAMO, Position.DEFENDER, 500_000),
                new Player("Hacheridi", Team.DYNAMO, Position.DEFENDER, 1_500_000),
                new Player("Vida", Team.DYNAMO, Position.DEFENDER, 1_300_000),
                new Player("Gusev", Team.DYNAMO, Position.MIDFIELDER, 1_500_000),
                new Player("Garmash", Team.DYNAMO, Position.MIDFIELDER, 1_200_000),
                new Player("Sidorchyk", Team.DYNAMO, Position.MIDFIELDER, 1_300_000),
                new Player("Milevskiy", Team.DYNAMO, Position.FORWARD, 1_000_000),
                new Player("Yarmolenko", Team.DYNAMO, Position.FORWARD, 4_500_000),

                new Player("Pyatov", Team.SHAKHTAR, Position.GOALKEEPER, 1_200_000),
                new Player("Kycher", Team.SHAKHTAR, Position.DEFENDER, 800_000),
                new Player("Kobin", Team.SHAKHTAR, Position.DEFENDER, 900_000),
                new Player("Rakitskiy", Team.SHAKHTAR, Position.DEFENDER, 1_600_000),
                new Player("Stepanenko", Team.SHAKHTAR, Position.MIDFIELDER, 2_200_000),
                new Player("Fred", Team.SHAKHTAR, Position.MIDFIELDER, 2_600_000),
                new Player("Bernard", Team.SHAKHTAR, Position.MIDFIELDER, 1_900_000),
                new Player("Gustavo", Team.SHAKHTAR, Position.FORWARD, 2_000_000),
                new Player("Fakundo", Team.SHAKHTAR, Position.FORWARD, 2_400_000)
        );
    }
}


class Player {

    private String surname;
    private Team team;
    private Position position;
    private long salary;

    public Player(String surname, Team team, Position position, long salary) {
        this.surname = surname;
        this.team = team;
        this.position = position;
        this.salary = salary;
    }

    public Player() {

    }

    public String getSurname() {
        return surname;
    }

    public Team getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public long getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Player{" +
                "surname='" + surname + '\'' +
                ", team=" + team +
                ", position=" + position +
                ", salary=" + salary +
                '}';
    }
}

enum Team {
    DYNAMO,
    SHAKHTAR
}

enum Position {
    GOALKEEPER,
    DEFENDER,
    MIDFIELDER,
    FORWARD
}

