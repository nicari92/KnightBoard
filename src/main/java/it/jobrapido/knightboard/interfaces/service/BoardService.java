package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.model.Board;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.naming.ConfigurationException;

@Validated
public interface BoardService {
    @Validated
    public Mono<Board> getBoard() throws ConfigurationException;
}
