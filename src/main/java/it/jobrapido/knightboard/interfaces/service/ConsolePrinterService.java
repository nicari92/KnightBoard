package it.jobrapido.knightboard.interfaces.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.jobrapido.knightboard.model.Knight;

public interface ConsolePrinterService {
    void printGenericErrorResultAsJson() throws JsonProcessingException;

    public void printInvalidStartPositionResultAsJson() throws JsonProcessingException;

    public void printOutOfTheBoardResultAsJson() throws JsonProcessingException;

    public void printSuccessResultAsJson(Knight finalKnightPosition) throws JsonProcessingException;
}
