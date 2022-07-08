package it.jobrapido.knightboard.model.commands;

import it.jobrapido.knightboard.model.enums.CommandEnum;
import it.jobrapido.knightboard.model.enums.DirectionEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RotateCommand extends Command{
    public RotateCommand(DirectionEnum direction){
        super(CommandEnum.ROTATE);
        this.direction = direction;
    }

    private DirectionEnum direction;
}
