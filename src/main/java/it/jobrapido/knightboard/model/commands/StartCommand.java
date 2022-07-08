package it.jobrapido.knightboard.model.commands;

import it.jobrapido.knightboard.model.Knight;
import it.jobrapido.knightboard.model.enums.CommandEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StartCommand extends Command {

    private Knight knight;

    public StartCommand(Knight startPosition){
        super(CommandEnum.START);
        knight = startPosition;
    }
    private Knight startPosition;
}
