package it.jobrapido.knightboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @NotNull(message = "Board width cannot be null")
    @Min(value = 1, message = "Board width must be greater than 0")
    private int width;
    @NotNull(message = "Board height cannot be null")
    @Min(value = 1, message = "Board height must be greater than 0")
    private int height;
    private List<Coordinate> obstacles;
}
