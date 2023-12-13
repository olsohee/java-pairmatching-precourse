package pairmatching.repository;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {

    private List<Crew> backendCrew = new ArrayList<>();
    private List<Crew> frontendCrew = new ArrayList<>();

    public void saveBackendCrew(Crew crew) {
        backendCrew.add(crew);
    }

    public void saveFrontendCrew(Crew crew) {
        frontendCrew.add(crew);
    }

    public List<String> findCrewByCourse(Course course) {
        if (course == Course.BACKEND) {
            return backendCrew.stream()
                    .map(crew -> crew.getName())
                    .collect(Collectors.toList());
        }
        return frontendCrew.stream()
                .map(crew -> crew.getName())
                .collect(Collectors.toList());
    }
}
