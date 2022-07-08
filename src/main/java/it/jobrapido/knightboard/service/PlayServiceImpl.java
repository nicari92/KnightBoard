package it.jobrapido.knightboard.service;

import it.jobrapido.knightboard.exceptions.*;
import it.jobrapido.knightboard.interfaces.service.BoardService;
import it.jobrapido.knightboard.interfaces.service.CommandsService;
import it.jobrapido.knightboard.interfaces.service.CommandsValidator;
import it.jobrapido.knightboard.interfaces.service.PlayService;
import it.jobrapido.knightboard.model.Board;
import it.jobrapido.knightboard.model.Coordinate;
import it.jobrapido.knightboard.model.Knight;
import it.jobrapido.knightboard.model.commands.*;
import it.jobrapido.knightboard.model.enums.CommandEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ConfigurationException;
import javax.validation.ConstraintViolationException;

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
            board = boardService.getBoard();
            commands = commandsService.getCommands();
        } catch (ConfigurationException | ConstraintViolationException | IllegalArgumentException ex) {
            throw new GenericException();
        }
        if (board == null || commands == null) {
            throw new GenericException();
        }

        //assuming start position if the FIRST command, as per specification
        knight = ((StartCommand)commands.getMoves().get(0)).getKnight();
        if (knight == null) {
            throw new GenericException();
        }

        try {
            commandsValidator.validatePosition(knight, board);
        } catch (ObstacleHitException | OutOfTheBoardException ex) {
            throw new InvalidStartPositionException();
        }

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

        if (command.getCommand().equals(CommandEnum.MOVE)) {
            try {
                for (int i = 0; i < ((MoveCommand)command).getAmount(); i++) {
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
        } else if (command.getCommand().equals(CommandEnum.ROTATE)) {
            try {
                knight.rotate(((RotateCommand)command).getDirection());
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
