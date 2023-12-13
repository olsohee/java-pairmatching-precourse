package pairmatching.repository;

import pairmatching.domain.Crew;
import pairmatching.domain.mission.Level;
import pairmatching.domain.Matching;
import pairmatching.domain.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairRepository {

    private List<Pair> level1 = new ArrayList<>();
    private List<Pair> level2 = new ArrayList<>();
    private List<Pair> level3 = new ArrayList<>();
    private List<Pair> level4 = new ArrayList<>();
    private List<Pair> level5 = new ArrayList<>();

    public void save(Matching matching) {
        Level level = matching.getCondition().getLevel();
        List<Pair> pairs = matching.getPairs();
        for (Pair pair : pairs) {
            if (level == Level.LEVEL1) {
                level1.add(pair);
            }
            if (level == Level.LEVEL2) {
                level2.add(pair);
            }
            if (level == Level.LEVEL3) {
                level3.add(pair);
            }
            if (level == Level.LEVEL4) {
                level4.add(pair);
            }
            if (level == Level.LEVEL5) {
                level5.add(pair);
            }
        }
    }

    public boolean isExistByLevel(Set<Crew> crews, Level level) {
        if (level == Level.LEVEL1) {
            if (level1.contains(crews)) {
                return true;
            }
        }
        if (level == Level.LEVEL2) {
            if (level2.contains(crews)) {
                return true;
            }
        }
        if (level == Level.LEVEL3) {
            if (level3.contains(crews)) {
                return true;
            }
        }
        if (level == Level.LEVEL4) {
            if (level4.contains(crews)) {
                return true;
            }
        }
        if (level == Level.LEVEL5) {
            if (level5.contains(crews)) {
                return true;
            }
        }
        return false;
    }
}
