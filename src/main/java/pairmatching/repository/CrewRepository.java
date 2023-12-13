package pairmatching.repository;

import pairmatching.domain.Course;
import pairmatching.domain.Crew;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {

    private List<Crew> crews = new ArrayList<>();

    public CrewRepository() throws IOException {
        init();
    }

    private void init() throws IOException {
        BufferedReader readerBack = new BufferedReader(new FileReader("/Users/sohee/precourse/clone/java-pairmatching-precourse/src/main/resources/backend-crew.md"));
        String backName = "";
        while ((backName = readerBack.readLine()) != null) {
            crews.add(new Crew(Course.BACKEND, backName));
        }
        BufferedReader readerFront = new BufferedReader(new FileReader("/Users/sohee/precourse/clone/java-pairmatching-precourse/src/main/resources/frontend-crew.md"));
        String frontName = "";
        while ((frontName = readerFront.readLine()) != null) {
            crews.add(new Crew(Course.FRONTEND, frontName));
        }
    }

    public List<String> findCrewByCourse(Course course) {
        return crews.stream()
                .filter(crew -> crew.isCollectByCourse(course))
                .map(crew -> crew.getName())
                .collect(Collectors.toList());
    }
}
