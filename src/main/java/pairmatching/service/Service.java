package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.domain.Condition;
import pairmatching.dto.PairDto;
import pairmatching.message.ErrorMessage;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingRepository;
import pairmatching.repository.PairRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            Set<Crew> crews = new HashSet<>();
            if (i + 3 == shuffledCrew.size()) {
                Crew crew1 = new Crew(condition.getCourse(), shuffledCrew.get(i));
                Crew crew2 = new Crew(condition.getCourse(), shuffledCrew.get(i + 1));
                Crew crew3 = new Crew(condition.getCourse(), shuffledCrew.get(i + 2));
                crews.add(crew1);
                crews.add(crew2);
                crews.add(crew3);
            } else {
                Crew crew1 = new Crew(condition.getCourse(), shuffledCrew.get(i));
                Crew crew2 = new Crew(condition.getCourse(), shuffledCrew.get(i + 1));
                crews.add(crew1);
                crews.add(crew2);
            }

            if (pairRepository.isExistByLevel(crews, condition.getLevel())) {
                if (shuffleCount == 2) {
                    shuffleCount = 0;
                    throw new IllegalArgumentException(ErrorMessage.MATCHING_FAIL.getErrorMessage());
                }
                startMatching(condition);
                shuffleCount++;
            }
            pairs.add(new Pair(crews));
        }

        if (matchingRepository.isExistByCondition(condition)) {
            matchingRepository.deleteByCondition(condition);
            save(new Matching(pairs, condition));
            return;
        }
        save(new Matching(pairs, condition));
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
