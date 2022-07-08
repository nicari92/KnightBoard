package it.jobrapido.knightboard.model.commands;

import it.jobrapido.knightboard.model.enums.CommandEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Command {
    private CommandEnum command;
}
