package it.jobrapido.knightboard.model.commands;

import it.jobrapido.knightboard.model.enums.CommandEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoveCommand extends Command{
    public MoveCommand(int amount){
        super(CommandEnum.MOVE);
        this.amount = amount;
    }
    int amount;
}
