package br.com.race.ranking;

import br.com.race.ranking.model.Race;
import br.com.race.ranking.model.Statistics;
import br.com.race.ranking.processor.LogProcessor;
import br.com.race.ranking.processor.RaceProcessor;
import br.com.race.ranking.util.DateTimeUtil;

import java.util.List;

public class AppMain {

	public static void main(String[] args) {
		String file = "/home/lucasvs/race.txt";
		LogProcessor logProcessor = new LogProcessor();
		RaceProcessor raceProcessor = new RaceProcessor();
		try {
			Race race = new Race();
			race.getLapList().addAll(logProcessor.logProcessor(file));
			List<Statistics> statisticsList = raceProcessor.getRaceStatistics(race);
			System.out.println("----------------------------------------------------------------------------------");
			statisticsList.forEach(System.out::println);
			Statistics statisctBestLap = raceProcessor.getBestLapRace(statisticsList);
			System.out.println("Best Race Lap: " + DateTimeUtil.getFormatDateTimeHour(statisctBestLap.getBestLapTime())
					+ " " + statisctBestLap.getDriver().getCodDriver() + " - " + statisctBestLap.getDriver().getName());
			System.out.println("----------------------------------------------------------------------------------");
		} catch (Exception e) {		
			System.out.println("Error, check if the log is valid.");
		}
	}
}
