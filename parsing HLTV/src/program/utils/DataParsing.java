package program.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import program.models.Match;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataParsing {
    private static String resultsPath = "C:\\Users\\Leo Rozhnov\\Desktop\\matches data\\results\\%s.txt";
    private static String matchesPath = "C:\\Users\\Leo Rozhnov\\Desktop\\matches data\\matches\\%s.txt";


    public static List<Match> getTodayResults() throws Exception {
        Document doc = Jsoup.connect("https://www.hltv.org/results")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        // текущая дата
        Date date = new Date();

        // все результаты со страницы
        Elements allResults = doc.select("div.results-sublist");
        //System.out.println(allResults);

        // результаты за последнюю дату
        Element lastDateResults = allResults.get(0);

        // последняя дата
        Elements dateElements = lastDateResults.select("span.standard-headline");
        Date nextMatchesDate = DateConverter.convert(lastDateResults.text());

        List<Match> matches = new ArrayList<>();
        // результаты матчей последней даты
        Elements allMatches = lastDateResults.select("div.result-con");

        String path = String.format(resultsPath, DateConverter.dateToString(nextMatchesDate));

        StringBuilder allText = new StringBuilder();

        for (Element allMatch : allMatches) {
            Match match = parseHTMLToEndedMatch(allMatch);
            System.out.println(match);
            matches.add(match);
            allText.append(match).append("\n");

        }

        // сохраняем в файл в папке results
        saveToFile(path, allText.toString().toString());


        return matches;
    }

    public static Match parseHTMLToEndedMatch(Element element) {
        Match match = new Match();

        Elements teams = element.select("div.team");
        match.setTeam1(teams.get(0).text());
        match.setTeam2(teams.get(1).text());
        String score = element.select("td.result-score").text();
        String[] scores = score.split(" - ");
        match.setScore1(scores[0]);
        match.setScore2(scores[1]);

        String event = element.select("td.event").text();
        match.setEvent(event);

        String map = element.select("div.map-text").text();
        match.setMap(map);

        int stars = element.select("div.stars").size();
        match.setStars(stars);

        match.setEnded(true);

        return match;
    }

    public static List<Match> getTodayMatches() throws Exception {
        Document doc = Jsoup.connect("https://www.hltv.org/matches")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements days = doc.select("div.upcomingMatchesSection");

        Element today = days.get(0);

        // получаем дату
        Elements dateElement = today.select("span.matchDayHeadline");
        String date = dateElement.text().split(" ")[2];

        // получаем матчи этой даты
        Elements upcomingMatches = today.select("div.upcomingMatch");

        String path = String.format(matchesPath, date);

        List<Match> matches = new ArrayList<>();
        StringBuilder allText = new StringBuilder();
        for (Element upcomingMatch : upcomingMatches) {
            Match match = parseHTMLToUpcomingMatch(upcomingMatch, date);
            System.out.println(match);
            matches.add(match);
            allText.append(match).append("\n");
        }

        saveToFile(path, allText.toString());

        return matches;
    }

    public static Match parseHTMLToUpcomingMatch(Element element, String date) throws Exception {
        Match match = new Match();
        match.setDate(DateConverter.stringToDate(date));

        Elements teams = element.select("div.matchTeamName");
        if (teams.size() == 2) {
            match.setTeam1(teams.get(0).text());
            match.setTeam2(teams.get(1).text());
        } else {
            match.setTeam1(teams.get(0).text());
        }

        match.setMap(element.select("div.matchMeta").text());

        match.setEvent(element.select("div.matchEventName").text());

        match.setStars(element.select("i.fa-star").size() - element.select("i.fa-star.faded").size());

        return match;
    }

    public static void saveToFile(String path, String text) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(text.getBytes());
        fos.flush();
        fos.close();
    }
}
