package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.model.Commands;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.naming.ConfigurationException;

@Validated
public interface CommandsService {
    public Mono<Commands> getCommands() throws ConfigurationException;
}
