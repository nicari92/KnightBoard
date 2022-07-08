package it.jobrapido.knightboard.service;

import it.jobrapido.knightboard.config.ApiPropertiesConfig;
import it.jobrapido.knightboard.interfaces.mapper.BoardMapper;
import it.jobrapido.knightboard.interfaces.service.BoardService;
import it.jobrapido.knightboard.model.Board;
import it.jobrapido.knightboard.model.dto.BoardDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.naming.ConfigurationException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    ApiPropertiesConfig apiProperties;

    @Autowired
    private WebClient webClient;

    private static final BoardMapper boardMapper = Mappers.getMapper(BoardMapper.class);

    public Board getBoard() throws ConfigurationException {
        String boardApiUrl = apiProperties.getBoard();

        try {
            new URL(boardApiUrl);
        } catch (MalformedURLException malformedURLException) {
            throw new ConfigurationException();
        }

        return webClient.get()
                .uri(boardApiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BoardDTO.class)
                .map(boardMapper::boardDTOtoBoard)
                .block();
    }


}

