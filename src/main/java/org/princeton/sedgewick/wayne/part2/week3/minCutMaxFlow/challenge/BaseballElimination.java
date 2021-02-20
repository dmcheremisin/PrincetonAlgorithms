package org.princeton.sedgewick.wayne.part2.week3.minCutMaxFlow.challenge;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballElimination {

    private final int v;
    private final Map<String, Integer> teams;
    private final Map<Integer, String> teamsByIndex;
    private final int[][] score;
    private final int[][] schedule;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In in = new In(filename);
        int vertexes = in.readInt();
        v = vertexes;

        teams = new HashMap<>();
        teamsByIndex = new HashMap<>();
        score = new int[vertexes][];
        schedule = new int[vertexes][];

        for (int i = 0; i < vertexes; i++) {
            String teamName = in.readString();
            teams.put(teamName, i);
            teamsByIndex.put(i, teamName);

            int[] teamScore = new int[3];
            score[i] = teamScore;
            for (int j = 0; j < 3; j++)
                teamScore[j] = in.readInt();

            int[] teamSchedule = new int[vertexes];
            schedule[i] = teamSchedule;
            for (int j = 0; j < vertexes; j++)
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

    private int getTeamIndex(String team) {
        Integer index = teams.get(team);
        if (index == null)
            throw new IllegalArgumentException(String.format("Team with name %s is not found", team));

        return index;
    }

    // number of wins for given team
    public int wins(String team) {
        int teamIndex = getTeamIndex(team);
        return score[teamIndex][0];
    }

    // number of losses for given team
    public int losses(String team) {
        int teamIndex = getTeamIndex(team);
        return score[teamIndex][1];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        int teamIndex = getTeamIndex(team);
        return score[teamIndex][2];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        int i1 = getTeamIndex(team1);
        int i2 = getTeamIndex(team2);
        return schedule[i1][i2];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        List<String> trivialElimination = getTrivialElimination(team);
        if (!trivialElimination.isEmpty())
            return true;

        List<String> nonTrivialElimination = getNonTrivialElimination(team);
        return !nonTrivialElimination.isEmpty();
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        List<String> trivialElimination = getTrivialElimination(team);
        if (!trivialElimination.isEmpty())
            return trivialElimination;

        List<String> nonTrivialElimination = getNonTrivialElimination(team);
        return nonTrivialElimination.isEmpty() ? null : nonTrivialElimination;
    }

    private List<String> getTrivialElimination(String team) {
        int teamIndex = getTeamIndex(team);
        int possibleWins = getPossibleWins(teamIndex);

        List<String> eliminatingTeams = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (i == teamIndex)
                continue;

            int otherWins = score[i][0];
            if (otherWins > possibleWins)
                eliminatingTeams.add(teamsByIndex.get(i));
        }

        return eliminatingTeams;
    }

    private int getPossibleWins(int teamIndex) {
        int[] teamScore = score[teamIndex];
        int wins = teamScore[0];
        int remaining = teamScore[2];
        return wins + remaining;
    }

    private List<String> getNonTrivialElimination(String team) {
        int teamIndex = getTeamIndex(team);
        int possibleWins = getPossibleWins(teamIndex);

        int totalGameVertexes = getTotalGameVertexes();
        int totalVertexes = 1 + totalGameVertexes + v + 1;
        FlowNetwork flowNetwork = new FlowNetwork(totalVertexes);
        List<Integer> teamToTargetEdges = new ArrayList<>();

        int s = 0;
        int t = totalVertexes - 1;

        int gameVertex = 1;
        for (int i = 0; i < v; i++) {
            if (i == teamIndex)
                continue;

            int[] teamSchedule = schedule[i];
            int firstTeamVertex = totalGameVertexes + i + 1;
            teamToTargetEdges.add(firstTeamVertex);

            for (int j = i + 1; j < v; j++) {
                if (j == teamIndex)
                    continue;

                FlowEdge sourceGameEdge = new FlowEdge(s, gameVertex, teamSchedule[j]);
                flowNetwork.addEdge(sourceGameEdge);

                FlowEdge gameFirstTeamEdge = new FlowEdge(gameVertex, firstTeamVertex, Double.POSITIVE_INFINITY);
                flowNetwork.addEdge(gameFirstTeamEdge);

                FlowEdge gameSecondTeamEdge = new FlowEdge(gameVertex, totalGameVertexes + j + 1,
                        Double.POSITIVE_INFINITY);
                flowNetwork.addEdge(gameSecondTeamEdge);

                gameVertex++;
            }

            int firstTeamWins = score[i][0];
            FlowEdge firstTeamToTargetEdge = new FlowEdge(firstTeamVertex, t, possibleWins - firstTeamWins);
            flowNetwork.addEdge(firstTeamToTargetEdge);
        }
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);

        List<String> inCutTeams = new ArrayList<>();
        for (int teamToTargetEdge : teamToTargetEdges)
            if (fordFulkerson.inCut(teamToTargetEdge))
                inCutTeams.add(teamsByIndex.get(teamToTargetEdge - totalGameVertexes - 1));

        return inCutTeams;
    }

    private int getTotalGameVertexes() {
        int gameVertexes = 0;
        for (int i = v; i - 2 > 0; i--)
            gameVertexes += (i - 2);
        return gameVertexes;
    }

    public static void main(String[] args) {
        // teams4.txt
        BaseballElimination elimination = new BaseballElimination(args[0]);

        for (String team : elimination.teams())
            System.out.println(team);
        // Atlanta
        // New_York
        // Montreal
        // Philadelphia

        System.out.println(elimination.numberOfTeams()); // 4
        System.out.println(elimination.wins("Philadelphia")); // 80
        System.out.println(elimination.losses("Philadelphia")); // 79
        System.out.println(elimination.remaining("Philadelphia")); // 3
        System.out.println(elimination.against("Atlanta", "Philadelphia")); // 1

        System.out.println(elimination.isEliminated("Montreal")); // true
        System.out.println(elimination.certificateOfElimination("Montreal")); // [Atlanta]

        System.out.println(elimination.isEliminated("New_York")); // false
        System.out.println(elimination.certificateOfElimination("New_York")); // null

        System.out.println(elimination.isEliminated("Philadelphia")); // true
        System.out.println(elimination.certificateOfElimination("Philadelphia")); // [Atlanta, New_York]

    }

}
