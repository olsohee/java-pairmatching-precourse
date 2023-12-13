package pairmatching.service;

import pairmatching.domain.Condition;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchingRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public void startMatching(Condition condition) {

    }
}
