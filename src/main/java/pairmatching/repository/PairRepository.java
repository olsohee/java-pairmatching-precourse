package pairmatching.repository;

import pairmatching.domain.Crew;
import pairmatching.domain.Level;
import pairmatching.domain.Matching;
import pairmatching.domain.Pair;
import pairmatching.message.ErrorMessage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairRepository {

    private List<Set<Crew>> level1 = new ArrayList<>();
    private List<Set<Crew>> level2 = new ArrayList<>();
    private List<Set<Crew>> level3 = new ArrayList<>();
    private List<Set<Crew>> level4 = new ArrayList<>();
    private List<Set<Crew>> level5 = new ArrayList<>();

    public void save(Matching matching) {
        Level level = matching.getCondition().getLevel();
        List<Pair> pairs = matching.getPairs();
        Set<Crew> crews = new HashSet<>();
        for (Pair pair : pairs) {
            crews.add(pair.getCrew1());
            crews.add(pair.getCrew2());
            crews.add(pair.getCrew3());
        }
        if (level == Level.LEVEL1) {
            level1.add(crews);
        }
        if (level == Level.LEVEL2) {
            level2.add(crews);
        }
        if (level == Level.LEVEL3) {
            level3.add(crews);
        }
        if (level == Level.LEVEL4) {
            level4.add(crews);
        }
        if (level == Level.LEVEL5) {
            level5.add(crews);
        }
    }

    public boolean isDuplicated(Crew crew1, Crew crew2, Level level) {
        Set<Crew> crews = new HashSet<>();
        crews.add(crew1);
        crews.add(crew2);
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

    public boolean isDuplicated(Crew crew1, Crew crew2, Crew crew3, Level level) {
        Set<Crew> crews = new HashSet<>();
        crews.add(crew1);
        crews.add(crew2);
        crews.add(crew3);
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
