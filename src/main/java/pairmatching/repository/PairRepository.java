package pairmatching.repository;

import pairmatching.domain.Crew;
import pairmatching.domain.mission.Level;
import pairmatching.domain.Matching;
import pairmatching.domain.Pair;

import java.util.*;

public class PairRepository {

    private final Map<Level, List<Pair>> levels = new HashMap<>();

    public PairRepository() {
        levels.put(Level.LEVEL1, new ArrayList<>());
        levels.put(Level.LEVEL2, new ArrayList<>());
        levels.put(Level.LEVEL3, new ArrayList<>());
        levels.put(Level.LEVEL4, new ArrayList<>());
        levels.put(Level.LEVEL5, new ArrayList<>());
    }

    public void save(Matching matching) {
        Level level = matching.getCondition().getLevel();
        List<Pair> pairs = levels.get(level);
        for (Pair pair : matching.getPairs()) {
            pairs.add(pair);
        }
        levels.put(matching.getCondition().getLevel(), pairs);
    }

    public boolean isExistByLevel(Pair pair, Level level) {
        List<Pair> pairs = levels.get(level);
        return pairs.contains(pair);
    }
}
