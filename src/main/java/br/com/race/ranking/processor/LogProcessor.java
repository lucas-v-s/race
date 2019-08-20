package br.com.race.ranking.processor;

import br.com.race.ranking.model.Driver;
import br.com.race.ranking.model.Lap;
import br.com.race.ranking.util.Constants;
import br.com.race.ranking.util.DateTimeUtil;
import br.com.race.ranking.util.NumberUtil;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogProcessor {

    public List<Lap> logProcessor(String path) throws IOException, URISyntaxException {

        Stream<String> fileLines = Files.lines(Paths.get(path));
        List<List<String>> logLines = new ArrayList<>();
        fileLines.skip(1).forEach(line -> {
            logLines.add(Arrays.stream(line.split("[\\s]"))
                    .filter(column -> column.trim().length() > 0)
                    .map(String::trim)
                    .collect(Collectors.toList()));
        });
        fileLines.close();
        return logLines.stream().map(LogProcessor::lapsProcessor).filter( lap -> lap != null).collect(Collectors.toList());
    }

    public static Lap lapsProcessor(List<String> line) {
        try {
            LocalTime hourStartLap = DateTimeUtil.parseLocalTimeHour(line.get(Constants.COLUMN_HOUR));
            Float speedAvarageLap = NumberUtil.formatNumberFloat(line.get(Constants.COLUMN_SPEED_AVERAGE));
            LocalTime timeLap = DateTimeUtil.parseLocalTimeMinute(line.get(Constants.COLUMN_LAP_TIME));
            Integer numberLap = Integer.parseInt(line.get(Constants.COLUMN_NUMBER_LAP));
            Driver driverLap = new Driver(line.get(Constants.COLUMN_DRIVER), line.get(Constants.COLUMN_NUMBER_DRIVER));
            return new Lap(hourStartLap, speedAvarageLap, timeLap, numberLap, driverLap);
        } catch (Exception e) {
            return null;
        }
    }
}
