package it.jobrapido.knightboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObstacleDTO implements Serializable {
    private int x;
    private int y;
}
