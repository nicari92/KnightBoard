package it.jobrapido.knightboard.interfaces.mapper;

import it.jobrapido.knightboard.model.Command;
import it.jobrapido.knightboard.model.Commands;
import it.jobrapido.knightboard.model.Knight;
import it.jobrapido.knightboard.model.dto.CommandsDTO;
import it.jobrapido.knightboard.model.enums.CommandEnum;
import it.jobrapido.knightboard.model.enums.DirectionEnum;
import it.jobrapido.knightboard.util.Commons;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
@Validated
public interface CommandsMapper {

    @Mapping(target = "startPosition", source = "commands", qualifiedByName = "startPositionMapping")
    @Mapping(target = "moves", source = "commands", qualifiedByName = "movesMapping")
    Commands commandsDTOToCommands(@Valid CommandsDTO commandsDTO);

    @Named("startPositionMapping")
    static Knight startPositionStringToPosition(List<String> commands) {

        if (commands == null || commands.isEmpty()) {
            return null;
        }
        Optional<String> optionalStartCommand = commands.stream().filter(c -> c.startsWith(CommandEnum.START.name())).findFirst();
        if (optionalStartCommand.isEmpty()) {
            return null;
        }

        String startCommand = optionalStartCommand.get();
        String startPosition = startCommand.split(Commons.COMMAND_SEPARATOR)[1];
        String[] splitStartPosition = startPosition.split(",");

        if (splitStartPosition.length != 3) {
            return null;
        } else {
            return Knight.builder()
                    .x(Integer.parseInt(splitStartPosition[0]))
                    .y(Integer.parseInt(splitStartPosition[1]))
                    .direction(DirectionEnum.valueOf(splitStartPosition[2]))
                    .build();
        }
    }

    @Named("movesMapping")
    static List<Command> listOfStringCommandsToListOfCommands(List<String> commands) {
        if (commands == null || commands.size() <= 2) {
            return Collections.emptyList();
        }
        List<String> commandsWithoutStart = commands.stream().skip(1).collect(Collectors.toList());
        if (commandsWithoutStart.size() <= 1) {
            return Collections.emptyList();
        }
        return commandsWithoutStart.stream().map(Command::new).collect(Collectors.toList());

    }
}
