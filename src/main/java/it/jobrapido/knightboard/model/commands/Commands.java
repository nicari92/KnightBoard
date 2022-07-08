package it.jobrapido.knightboard.model.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commands {
    @NotNull(message = "Commands cannot be null. At least the START command must be present")
    @NotEmpty(message = "Commands cannot be empty. At least the START command must be present")
    private List<Command> moves;
}
