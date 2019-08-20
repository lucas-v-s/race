package br.com.race.ranking.processor;

import br.com.race.ranking.model.Driver;
import br.com.race.ranking.model.Lap;
import br.com.race.ranking.model.Race;
import br.com.race.ranking.model.Statistics;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class RaceProcessor {

    public List<Statistics> getRaceStatistics(Race race){
        Map<Driver, List<Lap>> mapDriverListMap = race.getLapList().stream()
                .collect(Collectors.groupingBy(Lap::getDriverLap));
        return this.processRaceStatistics(mapDriverListMap);
    }

    public Statistics getBestLapRace(List<Statistics> statisticsList){
        return Collections.min(statisticsList, Comparator.comparing(s -> s.getBestLapTime()));
    }

    private List<Statistics> processRaceStatistics(Map<Driver, List<Lap>> mapDriverListMap){
        List<Statistics> statisticsList = new ArrayList<>();
        for (Map.Entry<Driver, List<Lap>> entry : mapDriverListMap.entrySet()){
            LocalTime localTime = null;
            LocalTime bestLapRace = null;
            Float speedAverage = 0F;
            int lapCompleted = 0;
            for (Lap lap : entry.getValue()){
                if(localTime == null){
                    localTime= lap.getTimeLap();
                }else{
                    localTime = localTime.plusMinutes(lap.getTimeLap().getMinute())
                            .plusSeconds(lap.getTimeLap().getSecond()).plusNanos(lap.getTimeLap().getNano());
                }
                if(bestLapRace == null || lap.getTimeLap().isBefore(bestLapRace)){
                    bestLapRace = lap.getTimeLap();
                }
                speedAverage += lap.getSpeedAvarageLap();
                lapCompleted ++;
            }
            speedAverage = speedAverage/lapCompleted;
            statisticsList.add(new Statistics(entry.getKey(), localTime, lapCompleted, bestLapRace, speedAverage));
        }

        statisticsList.sort((p1, p2) -> {
            if(p1.getLapCompleted() > p2.getLapCompleted()) {
                return -1;
            }else if(p1.getLapCompleted() < p2.getLapCompleted()){
                return 1;
            }
            return p1.getTotalRaceTime().compareTo(p2.getTotalRaceTime());
        });

        int position = 1;
        for (Statistics statistic:statisticsList) {
            statistic.setPosition(position);
            if(position != 1){
                statistic.setTimeBehindWinner(statistic.getTotalRaceTime().minusMinutes(
                        statisticsList.get(0).getTotalRaceTime().getMinute()).minusSeconds(
                        statisticsList.get(0).getTotalRaceTime().getSecond()).minusNanos(
                        statisticsList.get(0).getTotalRaceTime().getNano()));
            }
            position++;
        }

        return statisticsList;
    }
}
