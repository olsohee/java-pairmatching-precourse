package pairmatching.domain;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Pair {

    private LinkedHashSet<Crew> crews;

    public Pair(LinkedHashSet<Crew> crews) {
        this.crews = crews;
    }

    public Set<Crew> getCrews() {
        return crews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(crews, pair.crews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crews);
    }
}
