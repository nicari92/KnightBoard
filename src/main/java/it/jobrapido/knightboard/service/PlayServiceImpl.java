package it.jobrapido.knightboard.service;

import it.jobrapido.knightboard.exceptions.*;
import it.jobrapido.knightboard.interfaces.service.BoardService;
import it.jobrapido.knightboard.interfaces.service.CommandsService;
import it.jobrapido.knightboard.interfaces.service.CommandsValidator;
import it.jobrapido.knightboard.interfaces.service.PlayService;
import it.jobrapido.knightboard.model.*;
import it.jobrapido.knightboard.model.enums.CommandEnum;
import it.jobrapido.knightboard.model.enums.DirectionEnum;
import it.jobrapido.knightboard.util.Commons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ConfigurationException;

@Service
public class PlayServiceImpl implements PlayService {

    private final Logger logger = LoggerFactory.getLogger(PlayServiceImpl.class);
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommandsService commandsService;

    @Autowired
    private CommandsValidator commandsValidator;

    private static Board board = null;
    private static Commands commands = null;
    private static Knight knight = null;

    @Override
    public void initGame() throws InvalidStartPositionException, GenericException {
        try {
            board = boardService.getBoard().block();
            commands = commandsService.getCommands().block();
        } catch (ConfigurationException ex) {
            throw new GenericException();
        }

        if (board == null || commands == null) {
            throw new GenericException();
        }

        try {
            commandsValidator.validatePosition(commands.getStartPosition(), board);
        } catch (ObstacleHitException | OutOfTheBoardException e) {
            throw new InvalidStartPositionException();
        }

        knight = Knight.builder()
                .x(commands.getStartPosition().getX())
                .y(commands.getStartPosition().getY())
                .direction(commands.getStartPosition().getDirection())
                .build();

        logger.debug("Knight start position: " + knight);

    }

    public Knight playGame() throws GenericException, InvalidCommandException, OutOfTheBoardException {
        if (board == null || commands == null) {
            throw new GenericException();
        }
        for (Command move : commands.getMoves()) {
            executeCommand(move);
        }
        return knight;
    }

    private void executeCommand(Command command) throws OutOfTheBoardException, InvalidCommandException {
        logger.debug("Executing command: " + command.getCommand());

        if (command.getCommand().startsWith(CommandEnum.MOVE.name())) {
            try {
                int amount = Integer.parseInt(command.getCommand().split(Commons.COMMAND_SEPARATOR)[1]);
                for (int i = 0; i < amount; i++) {
                    try {
                        tryNextMove();
                        knight.move();
                        logger.debug("Knight new location: " + knight);
                    } catch (ObstacleHitException ex) {
                        break;
                    }
                }
            } catch (NumberFormatException | InvalidStartPositionException ex) {
                throw new InvalidCommandException();
            }
        } else if (command.getCommand().startsWith(CommandEnum.ROTATE.name())) {
            try {
                DirectionEnum newDirection = DirectionEnum.valueOf(command.getCommand().split(Commons.COMMAND_SEPARATOR)[1]);
                knight.rotate(newDirection);
                logger.debug("Knight new orientation: " + knight.getDirection());
            } catch (IllegalArgumentException ex) {
                throw new InvalidCommandException();
            }
        }
    }

    private void tryNextMove() throws ObstacleHitException, OutOfTheBoardException, InvalidStartPositionException {
        Coordinate nextPosition = knight.getPositionAfterMove();
        logger.debug("Knight future position: " + nextPosition);
        commandsValidator.validatePosition(nextPosition, board);
    }
}
