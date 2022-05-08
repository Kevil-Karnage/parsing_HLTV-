package program;

import program.models.Match;
import program.utils.DataParsing;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Match> results = DataParsing.getTodayResults();
        List<Match> matches = DataParsing.getTodayMatches();
    }
}
