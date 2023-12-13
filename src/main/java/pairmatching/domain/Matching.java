package pairmatching.domain;

import pairmatching.dto.Condition;

import java.util.List;

public class Matching {

    private List<Pair> pairs;
    private Condition condition;

    public Matching(List<pairmatching.domain.Pair> pairs, Condition condition) {
        this.pairs = pairs;
        this.condition = condition;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public Condition getCondition() {
        return condition;
    }
}
