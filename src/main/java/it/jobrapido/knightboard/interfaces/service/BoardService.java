package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.model.Board;
import org.springframework.validation.annotation.Validated;

import javax.naming.ConfigurationException;
import javax.validation.Valid;
@Validated
public interface BoardService {
    public @Valid Board getBoard() throws ConfigurationException;
}
