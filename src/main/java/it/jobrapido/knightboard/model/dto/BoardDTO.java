package it.jobrapido.knightboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO implements Serializable {
    private int width;
    private int height;
    private Set<ObstacleDTO> obstacles;
}
