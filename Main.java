package program.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.hltv.org/results")
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Date date = new Date();
        // все результаты со страницы
        Elements allResults = doc.select("div.results-sublist");
        //System.out.println(allResults);

        // результаты за последнюю дату
        Element lastDateResults = allResults.get(1);

        // последняя дата
        Elements dateElements = lastDateResults.select("span.standard-headline");
        Date lastDate = DateConverter.convert(lastDateResults.text());

        List<Match> matches = new ArrayList<>();
        if (DateConverter.equalsWithoutTime(lastDate, date)) {
            System.out.println("===| Last date not parsed |===");
            // результаты матчей последней даты
            Elements allLinksToMatches = lastDateResults.select("a.a-reset");
            Elements allMatches = lastDateResults.select("div.result-con");

            String path = "C:\\Users\\Leo\\Desktop\\results\\"
                    + lastDate.getMonth() + "-" + lastDate.getDay() + "-" + (lastDate.getYear() + 1900 + ".txt");

            StringBuilder matchesInString = new StringBuilder();

            for (int i = 0; i < allMatches.size(); i++) {
                Match match = parseHTMLToMatch(allMatches.get(i));

                String link = "https://hltv.org" + allLinksToMatches.get(i).attr("href");

                List<PlayedMap> maps = parseHTMLToMaps(link);
                match.setMap(maps);
                System.out.println(match);

                matches.add(match);
                matchesInString.append(match).append("\n");


            }

            // сохраняем в файл в папке results
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(matchesInString.toString().getBytes());
            fos.flush();
            fos.close();

        } else {
            System.out.println("All results parsed");
        }
    }

    public static Match parseHTMLToMatch(Element element) {
        Match match = new Match();

        Elements teams = element.select("div.team");
        match.setTeam1(teams.get(0).text());
        match.setTeam2(teams.get(1).text());
        String teamWon = teams.select("div.team-won").text();
        String score = element.select("td.result-score").text();
        String[] scores = score.split(" - ");
        match.setScore1(scores[0]);
        match.setScore2(scores[1]);

        String event = element.select("td.event").text();
        match.setEvent(event);

        return match;
    }

    public static List<PlayedMap> parseHTMLToMaps(String link) throws Exception {
        //System.out.println(link);
        Document doc = Jsoup.connect(link)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements matchInfo = doc.select("div.flexbox-column");

        //System.out.println(matchInfo.text());

        List<PlayedMap> maps = new ArrayList<>();

        Elements mapsInfo = matchInfo.select("div.mapholder");
        for (int i = 0; i < mapsInfo.size(); i++) {
            PlayedMap playedMap = new PlayedMap();
            Element mapInfo = mapsInfo.get(i);

            String mapName = mapInfo.select("div.mapname").text();
            playedMap.setMap(mapName);

            Elements scores = mapInfo.select("div.results-team-score");
            if (!scores.get(0).text().equals("-")) {
                int score1 = Integer.parseInt(scores.get(0).text());
                playedMap.setScoreTeam1(score1);
                int score2 = Integer.parseInt(scores.get(1).text());
                playedMap.setScoreTeam2(score2);

                maps.add(playedMap);
            }
        }

        return maps;
    }
}
