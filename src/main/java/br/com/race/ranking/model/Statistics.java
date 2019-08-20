package br.com.race.ranking.model;

import br.com.race.ranking.util.DateTimeUtil;

import java.time.LocalTime;

public class Statistics {

    private Driver driver;

    private LocalTime totalRaceTime;

    private int lapCompleted;

    private LocalTime bestLapTime;

    private Float speedAverage;

    private int position;

    private LocalTime timeBehindWinner;

    public Statistics(Driver driver, LocalTime totalRaceTime, int lapCompleted, LocalTime bestLapTime, Float speedAverage) {
        this.driver = driver;
        this.totalRaceTime = totalRaceTime;
        this.lapCompleted = lapCompleted;
        this.bestLapTime = bestLapTime;
        this.speedAverage = speedAverage;
    }

    public LocalTime getTotalRaceTime() {
        return totalRaceTime;
    }

    public void setTotalRaceTime(LocalTime totalRaceTime) {
        this.totalRaceTime = totalRaceTime;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getLapCompleted() {
        return lapCompleted;
    }

    public void setLapCompleted(int lapCompleted) {
        this.lapCompleted = lapCompleted;
    }

    public LocalTime getBestLapTime() {
        return bestLapTime;
    }

    public void setBestLapTime(LocalTime bestLapTime) {
        this.bestLapTime = bestLapTime;
    }

    public Float getSpeedAverage() {
        return speedAverage;
    }

    public void setSpeedAverage(Float speedAverage) {
        this.speedAverage = speedAverage;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public LocalTime getTimeBehindWinner() {
        return timeBehindWinner;
    }

    public void setTimeBehindWinner(LocalTime timeBehindWinner) {
        this.timeBehindWinner = timeBehindWinner;
    }

    @Override
    public String toString() {

        String stastistic = "Position: " + this.getPosition() + " " + this.getDriver().getCodDriver() + " - " +
                this.getDriver().getName() + " " +
                "Laps: " + this.getLapCompleted() + " - " +
                "Total Time: " + DateTimeUtil.getFormatDateTimeHour(this.getTotalRaceTime()) + " - " +
                "Best Lap:" + DateTimeUtil.getFormatDateTimeHour(this.getBestLapTime()) + " - " +
                "Speed Average: " + this.getSpeedAverage();

        if(this.getPosition() != 1){
            stastistic += " - Time Behind Winner: " + DateTimeUtil.getFormatDateTimeHour(this.getTimeBehindWinner());
        }

        return stastistic + "\n";
    }
}
