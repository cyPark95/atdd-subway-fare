package nextstep.subway.domain;

import nextstep.subway.applicaion.dto.PathType;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.persistence.*;

@Entity
public class Section extends DefaultWeightedEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    private int distance;
    private int duration;

    public Section() {

    }

    public Section(final Line line,
                   final Station upStation,
                   final Station downStation,
                   final int distance,
                   final int duration) {
        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isSameUpStation(Station station) {
        return this.upStation.equals(station);
    }

    public boolean isSameDownStation(Station station) {
        return this.downStation.equals(station);
    }

    public boolean hasDuplicateSection(Station upStation, Station downStation) {
        return (this.upStation.equals(upStation) && this.downStation.equals(downStation))
                || (this.upStation.equals(downStation) && this.downStation.equals(upStation));
    }

    public int getTypeValue(final PathType type) {
        if (type.equals(PathType.DISTANCE)) {
            return getDistance();
        }

        return getDuration();
    }

    public int addDistance(final int distance) {
        return this.distance + distance;
    }

    public int addDuration(final int duration) {
        return this.duration + duration;
    }

    public int subDistance(final int distance) {
        return this.distance - distance;
    }

    public int subDuration(final int duration) {
        return this.duration - duration;
    }
}