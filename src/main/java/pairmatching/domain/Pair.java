package pairmatching.domain;

import java.util.Objects;

public class Pair {

    private Crew crew1;
    private Crew crew2;
    private Crew crew3;

    public Pair(Crew crew1, Crew crew2, Crew crew3) {
        this.crew1 = crew1;
        this.crew2 = crew2;
        this.crew3 = crew3;
    }

    public Pair(Crew crew1, Crew crew2) {
        this(crew1, crew2, null);
    }

    public Crew getCrew1() {
        return crew1;
    }

    public Crew getCrew2() {
        return crew2;
    }

    public Crew getCrew3() {
        return crew3;
    }
}
