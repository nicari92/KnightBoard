package it.jobrapido.knightboard.interfaces.mapper;

import it.jobrapido.knightboard.model.Knight;
import it.jobrapido.knightboard.model.commands.*;
import it.jobrapido.knightboard.model.dto.CommandsDTO;
import it.jobrapido.knightboard.model.enums.CommandEnum;
import it.jobrapido.knightboard.model.enums.DirectionEnum;
import it.jobrapido.knightboard.util.Commons;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
@Validated
public interface CommandsMapper {

    @Mapping(target = "moves", source = "commands", qualifiedByName = "commandsMapping")
    Commands commandsDTOToCommands(@Valid CommandsDTO commandsDTO);

    @Named("commandsMapping")
    static List<Command> mapCommands(List<String> commands) {
        if (commands == null || commands.isEmpty()) {
            return null;
        } else {
            return commands.stream().map(commandString -> {
                CommandEnum command = CommandEnum.valueOf(commandString.split(Commons.COMMAND_SEPARATOR)[0]);
                String commandValue = commandString.split(Commons.COMMAND_SEPARATOR)[1];

                if (command.equals(CommandEnum.START)) {
                    String[] splitStartPosition = commandValue.split(",");
                    return new StartCommand(Knight.builder()
                            .x(Integer.parseInt(splitStartPosition[0]))
                            .y(Integer.parseInt(splitStartPosition[1]))
                            .direction(DirectionEnum.valueOf(splitStartPosition[2]))
                            .build());
                } else if (command.equals(CommandEnum.MOVE)) {
                    return new MoveCommand(Integer.parseInt(commandValue));
                } else if (command.equals(CommandEnum.ROTATE)) {
                    return new RotateCommand(DirectionEnum.valueOf(commandValue));
                } else {
                    return null;
                }

            }).collect(Collectors.toList());
        }
    }
}
