package it.jobrapido.knightboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO implements Serializable {
    @NotNull
    @Size(message = "Board width must be greater than 0", min = 1)
    private int width;
    @NotNull
    @Size(message = "Board height must be greater than 0", min = 1)
    private int height;

    private Set<ObstacleDTO> obstacles;
}
