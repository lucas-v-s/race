package br.com.race.ranking.model;

import java.util.ArrayList;
import java.util.List;

public class Race {

    private List<Lap> lapList;

    public List<Lap> getLapList() {
        return lapList == null ? lapList = new ArrayList<Lap>() : lapList;
    }

}
