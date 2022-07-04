package it.jobrapido.knightboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int width;
    private int height;
    private List<Coordinate> obstacles;
}
