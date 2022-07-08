package it.jobrapido.knightboard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.jobrapido.knightboard.model.GameResult;
import it.jobrapido.knightboard.model.dto.BoardDTO;
import it.jobrapido.knightboard.model.dto.CommandsDTO;
import it.jobrapido.knightboard.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { KnightBoardApplication.class },
        initializers = ConfigDataApplicationContextInitializer.class) //prevent "Play" bean from running (but not from being loaded)
@TestPropertySource(properties = {
        "api.board=https://storage.googleapis.com/jobrapido-backend-test/board.json",
        "api.commands=https://storage.googleapis.com/jobrapido-backend-test/commands.json",
        "logging.config=classpath:logback-error-log-level.xml"
})
@ExtendWith(OutputCaptureExtension.class)
@Profile("logback-log-level-error")
public class KnightBoardFunctionalTests {
    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriMock;
    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
    @MockBean
    private WebClient webClientMock;

    @Autowired
    CommandLineRunner commandLineRunner;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void init() {
        doReturn(requestHeadersUriMock).when(webClientMock).get();
        doReturn(requestHeadersMock).when(requestHeadersUriMock).uri(anyString());
        doReturn(requestHeadersMock).when(requestHeadersMock).accept(any());
        doReturn(responseMock).when(requestHeadersMock).retrieve();
    }

    @Test
    void expect_generic_error(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/beanValidation/InvalidBoard1_zeroWidth.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/ValidCommands1.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/InvalidGameResult1_GenericError.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_generic_error2(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard1.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/beanValidation/InvalidCommands1_wrongStartDirection.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/InvalidGameResult1_GenericError.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_invalid_start_position(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard1.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/InvalidCommands1_InvalidStartPosition1.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/InvalidGameResult1_InvalidStartPosition.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_invalid_start_position2(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard1.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/InvalidCommands1_InvalidStartPosition2.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/InvalidGameResult1_InvalidStartPosition.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_out_of_the_board(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard1.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/InvalidCommands1_OutOfTheBoard.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/InvalidGameResult1_OutOfTheBoard.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_valid_result(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard1.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/ValidCommands1.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/ValidGameResult1.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

    @Test
    void expect_valid_result2(CapturedOutput output) throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/games/ValidBoard2.json", new TypeReference<>() {});
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/games/ValidCommands2.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        String gameResultMockString = TestUtils.readFileAsString("/mocks/games/ValidGameResult2.json");
        GameResult gameResultMock = objectMapper.readValue(gameResultMockString, new TypeReference<>() {});

        commandLineRunner.run();

        String gameResultString = output.getOut();
        GameResult gameResult = objectMapper.readValue(gameResultString, new TypeReference<>() {});
        Assertions.assertEquals(gameResultMock, gameResult);
    }

}
