package pairmatching.domain;

import java.util.Set;

public class Pair {

    private Set<Crew> crews;

    public Pair(Set<Crew> crews) {
        this.crews = crews;
    }

    public Set<Crew> getCrews() {
        return crews;
    }
}
