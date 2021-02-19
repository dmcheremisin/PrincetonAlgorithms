package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow.challenge;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

public class BaseballElimination {

    private final int v;
    private final Map<String, Integer> teams;
    private final int[][] score;
    private final int[][] schedule;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        int v = in.readInt();
        this.v = v;

        teams = new HashMap<>();
        score = new int[v][];
        schedule = new int[v][];

        for (int i = 0; i < v; i++) {
            teams.put(in.readString(), i);

            int[] teamScore = new int[3];
            score[i] = teamScore;
            for (int j = 0; j < 3; j++)
                teamScore[j] = in.readInt();

            int[] teamSchedule = new int[v];
            schedule[i] = teamSchedule;
            for (int j = 0; j < v; j++)
                teamSchedule[j] = in.readInt();
        }
    }

    // number of teams
    public int numberOfTeams() {
        return v;
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keySet();
    }

    private Integer getTeamIndex(String team) {
        Integer index = teams.get(team);
        if (index == null)
            throw new IllegalArgumentException(String.format("Team with name %s is not found", team));
        return index;
    }

    // number of wins for given team
    public int wins(String team) {
        Integer teamIndex = getTeamIndex(team);
        return score[teamIndex][0];
    }

    // number of losses for given team
    public int losses(String team) {
        Integer teamIndex = getTeamIndex(team);
        return score[teamIndex][1];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        Integer teamIndex = getTeamIndex(team);
        return score[teamIndex][2];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        Integer i1 = getTeamIndex(team1);
        Integer i2 = getTeamIndex(team2);
        return schedule[i1][i2];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    public static void main(String[] args) {
        // teams4.txt
        BaseballElimination elimination = new BaseballElimination(args[0]);

        for (String team : elimination.teams())
            System.out.println(team);
        //Atlanta
        //New_York
        //Montreal
        //Philadelphia

        System.out.println(elimination.numberOfTeams()); // 4
        System.out.println(elimination.wins("Philadelphia")); // 80
        System.out.println(elimination.losses("Philadelphia")); // 79
        System.out.println(elimination.remaining("Philadelphia")); // 3
        System.out.println(elimination.against("Atlanta", "Philadelphia")); // 1

    }

}
