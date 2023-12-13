package pairmatching.dto;

import java.util.List;

public class PairDto {

    private final List<String> crewNames;

    public PairDto(List<String> crewNames) {
        this.crewNames = crewNames;
    }

    public List<String> getCrewNames() {
        return crewNames;
    }
}
