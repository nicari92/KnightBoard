package it.jobrapido.knightboard.interfaces.service;

import it.jobrapido.knightboard.model.commands.Commands;
import org.springframework.validation.annotation.Validated;

import javax.naming.ConfigurationException;
import javax.validation.Valid;

@Validated
public interface CommandsService {
    public @Valid Commands getCommands() throws ConfigurationException;
}
