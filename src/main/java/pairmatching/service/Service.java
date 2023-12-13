package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.dto.PairDto;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final MatchingRepository matchingRepository = new MatchingRepository();
    private final CrewRepository crewRepository = new CrewRepository();

    public Service() throws IOException {
        initBackendCrew();
        initFrontendCrew();
    }

    private void initBackendCrew() throws IOException {
        BufferedReader readerBack = new BufferedReader(new FileReader("/Users/sohee/precourse/clone/java-pairmatching-precourse/src/main/resources/backend-crew.md"));
        String name = "";
        while ((name = readerBack.readLine()) != null) {
            crewRepository.saveBackendCrew(new Crew(Course.BACKEND, name));
        }
    }

    private void initFrontendCrew() throws IOException {
        BufferedReader readerFront = new BufferedReader(new FileReader("/Users/sohee/precourse/clone/java-pairmatching-precourse/src/main/resources/frontend-crew.md"));
        String name = "";
        while ((name = readerFront.readLine()) != null) {
            crewRepository.saveFrontendCrew(new Crew(Course.FRONTEND, name));
        }
    }

    public boolean isExistByCondition(Condition condition) {
        try {
            matchingRepository.findByCondition(condition);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void startMatching(Condition condition) {

        List<String> crewNames = crewRepository.findCrewByCourse(condition.getCourse());
        List<String> shuffledCrew = Randoms.shuffle(crewNames);

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size(); i += 2) {
            if (i + 3 == shuffledCrew.size()) {
                pairs.add(new Pair(new Crew(condition.getCourse(), shuffledCrew.get(i)),
                        new Crew(condition.getCourse(), shuffledCrew.get(i + 1)),
                        new Crew(condition.getCourse(), shuffledCrew.get(i + 2))));
                break;
            }
            pairs.add(new Pair(new Crew(condition.getCourse(), shuffledCrew.get(i)), new Crew(condition.getCourse(), shuffledCrew.get(i + 1))));
        }

        if (matchingRepository.isExistByCondition(condition)) {
            matchingRepository.deleteByCondition(condition);
            matchingRepository.save(new Matching(pairs, condition));
            return;
        }
        matchingRepository.save(new Matching(pairs, condition));
    }

    public List<PairDto> getMatchingResultDto(Condition condition) {
        List<Pair> pairs = matchingRepository.findByCondition(condition).getPairs();
        List<PairDto> pairDtos = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++) {
            Pair pair = pairs.get(i);
            if (pair.getCrew3() == null) {
                pairDtos.add(new PairDto(pairs.get(i).getCrew1().getName(), pairs.get(i).getCrew2().getName(), null));
                continue;
            }
            pairDtos.add(new PairDto(pairs.get(i).getCrew1().getName(), pairs.get(i).getCrew2().getName(), pairs.get(i).getCrew3().getName()));
        }
        return pairDtos;
    }

    public void reset() {
        matchingRepository.reset();
    }
}
