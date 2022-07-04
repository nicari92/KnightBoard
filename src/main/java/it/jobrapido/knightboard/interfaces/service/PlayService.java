package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.exceptions.GenericException;
import it.jobrapido.knightboard.exceptions.InvalidCommandException;
import it.jobrapido.knightboard.exceptions.InvalidStartPositionException;
import it.jobrapido.knightboard.exceptions.OutOfTheBoardException;
import it.jobrapido.knightboard.model.Knight;

public interface PlayService {

    void initGame() throws InvalidStartPositionException, GenericException;

    /**
     * @return
     */
    Knight playGame() throws GenericException, InvalidCommandException, OutOfTheBoardException;
}
