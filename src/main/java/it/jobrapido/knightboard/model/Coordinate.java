package it.jobrapido.knightboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Coordinate {
    @NotNull
    @Min(value = 0, message = "X coordinate cannot be negative")
    private int x;
    @NotNull
    @Min(value = 0, message = "Y coordinate cannot be negative")
    private int y;

    public void moveNorth() {
        this.y++;
    }

    public void moveSouth() {
        this.y--;
    }

    public void moveWest() {
        this.x--;
    }

    public void moveEast() {
        this.x++;
    }

    @JsonIgnore
    public Coordinate getNorthMoveResult() {
        return Coordinate.builder()
                .x(x)
                .y(y + 1)
                .build();
    }

    @JsonIgnore
    public Coordinate getSouthMoveResult() {
        return Coordinate.builder()
                .x(x)
                .y(y - 1)
                .build();
    }

    @JsonIgnore
    public Coordinate getWestMoveResult() {
        return Coordinate.builder()
                .x(x - 1)
                .y(y)
                .build();
    }

    @JsonIgnore
    public Coordinate getEastMoveResult() {
        return Coordinate.builder()
                .x(x + 1)
                .y(y)
                .build();
    }

    @JsonIgnore
    public boolean isOutOfTheBoard(Board board) {
        return x >= board.getWidth() ||
                y >= board.getHeight();
    }

}
