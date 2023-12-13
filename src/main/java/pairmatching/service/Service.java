package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import pairmatching.domain.*;
import pairmatching.dto.PairDto;
import pairmatching.message.ErrorMessage;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingRepository;
import pairmatching.repository.PairRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final MatchingRepository matchingRepository = new MatchingRepository();
    private final CrewRepository crewRepository = new CrewRepository();
    private final PairRepository pairRepository = new PairRepository();
    private int shuffleCount = 0;

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
        List<String> shuffledCrew = shuffle(condition);

        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < shuffledCrew.size(); i += 2) {
            if (i + 3 == shuffledCrew.size()) {
                Crew crew1 = new Crew(condition.getCourse(), shuffledCrew.get(i));
                Crew crew2 = new Crew(condition.getCourse(), shuffledCrew.get(i + 1));
                Crew crew3 = new Crew(condition.getCourse(), shuffledCrew.get(i + 2));
                validateDuplicated(crew1, crew2, crew3, condition.getLevel());
                pairs.add(new Pair(crew1, crew2, crew3));
                break;
            }
            Crew crew1 = new Crew(condition.getCourse(), shuffledCrew.get(i));
            Crew crew2 = new Crew(condition.getCourse(), shuffledCrew.get(i + 1));

            if (pairRepository.isDuplicated(crew1, crew2, condition.getLevel())) {
                if (shuffleCount == 2) {
                    shuffleCount = 0;
                    throw new IllegalArgumentException(ErrorMessage.MATCHING_FAIL.getErrorMessage());
                }
                startMatching(condition);
                shuffleCount++;
            }
            pairs.add(new Pair(crew1, crew2));
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

    private void validateDuplicated(Crew crew1, Crew crew2, Level level) {
        pairRepository.isDuplicated(crew1, crew2, level);
    }

    private void validateDuplicated(Crew crew1, Crew crew2, Crew crew3, Level level) {
        pairRepository.isDuplicated(crew1, crew2, crew3, level);
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
