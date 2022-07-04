package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.exceptions.InvalidStartPositionException;
import it.jobrapido.knightboard.exceptions.ObstacleHitException;
import it.jobrapido.knightboard.exceptions.OutOfTheBoardException;
import it.jobrapido.knightboard.model.Board;
import it.jobrapido.knightboard.model.Coordinate;

public interface CommandsValidator {
    void validatePosition(Coordinate coordinate, Board board) throws InvalidStartPositionException, ObstacleHitException, OutOfTheBoardException;
}
