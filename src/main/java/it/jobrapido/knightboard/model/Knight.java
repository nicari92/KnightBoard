package it.jobrapido.knightboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.jobrapido.knightboard.model.enums.DirectionEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Knight extends Coordinate {
    private DirectionEnum direction;

    @JsonIgnore
    public void move() {
        switch (direction) {
            case NORTH:
                moveNorth();
                break;
            case SOUTH:
                moveSouth();
                break;
            case EAST:
                moveEast();
                break;
            case WEST:
                moveWest();
                break;
        }
    }

    @JsonIgnore
    public Coordinate getPositionAfterMove() {
        Coordinate coordinate;
        switch (direction) {
            case NORTH:
                coordinate = getNorthMoveResult();
                break;
            case SOUTH:
                coordinate = getSouthMoveResult();
                break;
            case EAST:
                coordinate = getEastMoveResult();
                break;
            case WEST:
                coordinate = getWestMoveResult();
                break;
            default:
                coordinate = null;
        }
        return coordinate;

    }

    @JsonIgnore
    public void rotate(DirectionEnum direction) {
        this.direction = direction;
    }

}
