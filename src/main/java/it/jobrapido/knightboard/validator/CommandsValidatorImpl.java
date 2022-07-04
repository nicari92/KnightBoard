package it.jobrapido.knightboard.validator;

import it.jobrapido.knightboard.exceptions.ObstacleHitException;
import it.jobrapido.knightboard.exceptions.OutOfTheBoardException;
import it.jobrapido.knightboard.interfaces.service.CommandsValidator;
import it.jobrapido.knightboard.model.Board;
import it.jobrapido.knightboard.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommandsValidatorImpl implements CommandsValidator {

    private final Logger logger = LoggerFactory.getLogger(CommandsValidatorImpl.class);

    @Override
    public void validatePosition(Coordinate position, Board board) throws ObstacleHitException, OutOfTheBoardException {

        Optional<Coordinate> hitObstacle = board.getObstacles().stream()
                .filter(o -> o.equals(position))
                .findFirst();

        if (hitObstacle.isPresent()) {
            logger.debug("Obstacle present at location: " + hitObstacle);
            throw new ObstacleHitException();
        }
        if (position.isOutOfTheBoard(board)) {
            logger.debug("Next position will be out of the board");
            throw new OutOfTheBoardException();
        }

    }
}
