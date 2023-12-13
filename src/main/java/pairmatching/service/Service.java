package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.domain.Condition;
import pairmatching.domain.mission.Level;
import pairmatching.dto.PairDto;
import pairmatching.message.ErrorMessage;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingRepository;
import pairmatching.repository.PairRepository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private final CrewRepository crewRepository = new CrewRepository();
    private final MatchingRepository matchingRepository = new MatchingRepository();
    private final PairRepository pairRepository = new PairRepository();
    private int shuffleCount = 0;

    public Service() throws IOException {
    }

    public boolean isExistByCondition(Condition condition) {
        return matchingRepository.isExistByCondition(condition);
    }

    public void startMatching(Condition condition) {
        List<String> shuffledCrew = shuffle(condition);
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size(); i += 2) {
            LinkedHashSet<Crew> crews = new LinkedHashSet<>();
            Pair pair;
            if (i + 3 == shuffledCrew.size()) {
                crews.add(new Crew(condition.getCourse(), shuffledCrew.get(i)));
                crews.add(new Crew(condition.getCourse(), shuffledCrew.get(i + 1)));
                crews.add(new Crew(condition.getCourse(), shuffledCrew.get(i + 2)));
                pair = new Pair(crews);
                validateDuplicated(pair, condition);
                pairs.add(pair);
                break;
            } else {
                crews.add(new Crew(condition.getCourse(), shuffledCrew.get(i)));
                crews.add(new Crew(condition.getCourse(), shuffledCrew.get(i + 1)));
                pair = new Pair(crews);
                validateDuplicated(pair, condition);
                pairs.add(pair);
            }
        }

        if (matchingRepository.isExistByCondition(condition)) {
            matchingRepository.deleteByCondition(condition);
            save(new Matching(pairs, condition));
            return;
        }
        save(new Matching(pairs, condition));
    }

    private void validateDuplicated(Pair pair, Condition condition) {
        if (pairRepository.isExistByLevel(pair, condition.getLevel())) {
            if (shuffleCount == 2) {
                shuffleCount = 0;
                throw new IllegalArgumentException(ErrorMessage.MATCHING_FAIL.getErrorMessage());
            }
            startMatching(condition);
            shuffleCount++;
        }
    }

    private void save(Matching matching) {
        matchingRepository.save(matching);
        pairRepository.save(matching);
    }

    private List<String> shuffle(Condition condition) {
        List<String> crewNames = crewRepository.findCrewByCourse(condition.getCourse());
        List<String> shuffledCrew = Randoms.shuffle(crewNames);
        return shuffledCrew;
    }

    public List<PairDto> getMatchingResultDto(Condition condition) {
        List<Pair> pairs = matchingRepository.findByCondition(condition).getPairs();

        return pairs.stream()
                .map(pair -> {
                    List<String> crewNames = new ArrayList<>();
                    for (Crew crew : pair.getCrews()) {
                        crewNames.add(crew.getName());
                    }
                    return new PairDto(crewNames);
                })
                .collect(Collectors.toList());
    }

    public void reset() {
        matchingRepository.reset();
    }
}
