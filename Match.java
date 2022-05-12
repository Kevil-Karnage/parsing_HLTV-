package program.parsing;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String event;
    private List<PlayedMap> maps;

    public Match() {
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public List<PlayedMap> getMaps() {
        return maps;
    }

    public void setMap(List<PlayedMap> maps) {
        this.maps = maps;
    }

    public void addMap(PlayedMap map) {
        if (maps == null) {
            maps = new ArrayList<>();
        }
        maps.add(map);
    }

    public String toString() {
        String s = team1 + " " + score1 + " - " + score2 + " " + team2 + "\nEvent: " + event + "\n";
        s += "Maps: \n";
        for (PlayedMap map :
                maps) {
            s += map.toString() + "\n";
        }
        return s;
    }
}
