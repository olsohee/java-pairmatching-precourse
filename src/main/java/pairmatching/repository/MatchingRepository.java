package pairmatching.repository;

import pairmatching.domain.Condition;
import pairmatching.domain.Matching;

import java.util.ArrayList;
import java.util.List;

public class MatchingRepository {

    private List<Matching> matchings = new ArrayList<>();

    public void save(Matching matching) {
        matchings.add(matching);
    }

    public Matching findByCondition(Condition condition) {
        return matchings.stream()
                .filter(matching -> matching.getCondition().equals(condition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
