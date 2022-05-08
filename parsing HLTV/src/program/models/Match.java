package program.models;

import program.utils.DateConverter;

import java.util.Date;

public class Match {
    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String event;
    private String map;
    private int stars;
    private boolean isEnded;
    private Date date;

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

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        if (isEnded) {
            return "Результаты матча: \n" + team1 + " " + score1 + " - " + score2 + " " + team2 +
                    "\nEvent: " + event +
                    "\nMap: " + map +
                    "\nStars: " + stars +
                    "\nDate: " + DateConverter.dateToString(date) + "\n";
        }

        return "Будущий матч: \n" +
                team1 + " " + score1 + " - " + score2 + " " + team2 +
                "\nEvent: " + event +
                "\nMap: " + map +
                "\nStars: " + stars +
                "\nDate: " + DateConverter.dateToString(date) + "\n";
    }
}
