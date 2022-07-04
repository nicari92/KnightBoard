package it.jobrapido.knightboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commands {
    private Knight startPosition;
    private List<Command> moves;
}
