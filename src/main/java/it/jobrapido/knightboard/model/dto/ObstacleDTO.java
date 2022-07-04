package it.jobrapido.knightboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObstacleDTO implements Serializable {
    @Size(message = "Obstacle x coordinate must be positive", min = 0)
    private int x;
    @Size(message = "Obstacle y coordinate must be positive", min = 0)
    private int y;
}
