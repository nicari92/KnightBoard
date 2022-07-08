package it.jobrapido.knightboard.service;

import it.jobrapido.knightboard.config.ApiPropertiesConfig;
import it.jobrapido.knightboard.interfaces.mapper.CommandsMapper;
import it.jobrapido.knightboard.interfaces.service.CommandsService;
import it.jobrapido.knightboard.model.commands.Commands;
import it.jobrapido.knightboard.model.dto.CommandsDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.naming.ConfigurationException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class CommandsServiceImpl implements CommandsService {

    @Autowired
    ApiPropertiesConfig apiProperties;

    @Autowired
    private WebClient webClient;

    private static final CommandsMapper commandsMapper = Mappers.getMapper(CommandsMapper.class);

    public Commands getCommands() throws ConfigurationException {

        String commandsApiUrl = apiProperties.getBoard();

        try {
            new URL(commandsApiUrl);
        } catch (MalformedURLException malformedURLException) {
            throw new ConfigurationException();
        }

        return webClient.get()
                .uri(apiProperties.getCommands())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CommandsDTO.class)
                .map(commandsMapper::commandsDTOToCommands)
                .block();
    }

}
