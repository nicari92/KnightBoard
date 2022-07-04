package it.jobrapido.knightboard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.jobrapido.knightboard.interfaces.service.ConsolePrinterService;
import it.jobrapido.knightboard.model.GameResult;
import it.jobrapido.knightboard.model.Knight;
import it.jobrapido.knightboard.model.enums.StatusEnum;
import org.springframework.stereotype.Service;

@Service
public class ConsolePrinterServiceImpl implements ConsolePrinterService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void printSuccessResultAsJson(Knight finalKnightPosition) throws JsonProcessingException {
        GameResult gameResult = GameResult.builder()
                .position(finalKnightPosition)
                .status(StatusEnum.SUCCESS)
                .build();
        String print = objectMapper.writeValueAsString(gameResult);
        System.out.println(print);
    }

    public void printGenericErrorResultAsJson() throws JsonProcessingException {
        GameResult gameResult = GameResult.builder()
                .status(StatusEnum.GENERIC_ERROR)
                .build();
        String print = objectMapper.writeValueAsString(gameResult);
        System.out.println(print);
    }

    public void printInvalidStartPositionResultAsJson() throws JsonProcessingException {
        GameResult gameResult = GameResult.builder()
                .status(StatusEnum.INVALID_START_POSITION)
                .build();
        String print = objectMapper.writeValueAsString(gameResult);
        System.out.println(print);
    }

    public void printOutOfTheBoardResultAsJson() throws JsonProcessingException {
        GameResult gameResult = GameResult.builder()
                .status(StatusEnum.OUT_OF_THE_BOARD)
                .build();
        String print = objectMapper.writeValueAsString(gameResult);
        System.out.println(print);
    }
}
