package it.jobrapido.knightboard.model;

import it.jobrapido.knightboard.model.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameResult {
    private Knight position;
    private StatusEnum status;
}
