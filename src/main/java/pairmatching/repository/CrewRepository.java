package pairmatching.repository;

import pairmatching.domain.Crew;

import java.util.ArrayList;
import java.util.List;

public class CrewRepository {

    private List<Crew> backendCrew = new ArrayList<>();
    private List<Crew> frontendCrew = new ArrayList<>();

    public void saveBackendCrew(Crew crew) {
        backendCrew.add(crew);
    }

    public void saveFrontendCrew(Crew crew) {
        frontendCrew.add(crew);
    }
}
