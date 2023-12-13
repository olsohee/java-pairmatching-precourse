package pairmatching.repository;

import pairmatching.domain.Condition;
import pairmatching.domain.Matching;
import pairmatching.message.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class MatchingRepository {

    private List<Matching> matchings = new ArrayList<>();

    public void save(Matching matching) {
        matchings.add(matching);
    }

    public boolean isExistByCondition(Condition condition) {
        return matchings.stream()
                .anyMatch(matching -> matching.getCondition().equals(condition));
    }

    public void deleteByCondition(Condition condition) {
        Matching deleteMatching = matchings.stream()
                .filter(matching -> matching.getCondition().equals(condition))
                .findAny().get();
        matchings.remove(deleteMatching);
    }

    public Matching findByCondition(Condition condition) {
        return matchings.stream()
                .filter(matching -> matching.getCondition().equals(condition))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_FOUND_MATCHING.getErrorMessage()));
    }

    public void reset() {
        matchings.clear();
    }
}
