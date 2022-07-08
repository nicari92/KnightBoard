package it.jobrapido.knightboard;


import com.fasterxml.jackson.core.type.TypeReference;
import it.jobrapido.knightboard.interfaces.service.BoardService;
import it.jobrapido.knightboard.interfaces.service.CommandsService;
import it.jobrapido.knightboard.model.dto.BoardDTO;
import it.jobrapido.knightboard.model.dto.CommandsDTO;
import it.jobrapido.knightboard.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@TestPropertySource(properties = {
        "api.board=https://storage.googleapis.com/jobrapido-backend-test/board.json",
        "api.commands=https://storage.googleapis.com/jobrapido-backend-test/commands.json"
})
@ActiveProfiles("test") //disable "Play" bean loading
@Profile("logback-log-level-debug")
class KnightBoardIntegrationTests {

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriMock;
    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersMock;
    @Mock
    private WebClient.ResponseSpec responseMock;
    @MockBean
    private WebClient webClientMock;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommandsService commandsService;

    @BeforeEach
    void init() {
        doReturn(requestHeadersUriMock).when(webClientMock).get();
        doReturn(requestHeadersMock).when(requestHeadersUriMock).uri(anyString());
        doReturn(requestHeadersMock).when(requestHeadersMock).accept(any());
        doReturn(responseMock).when(requestHeadersMock).retrieve();
    }

    @Test
    void expect_throws_ConstraintViolationException() throws Exception {
        BoardDTO mockBoardDTO = TestUtils.readJson("/mocks/beanValidation/InvalidBoard1_zeroWidth.json", new TypeReference<>() {});
        doReturn(Mono.just(mockBoardDTO)).when(responseMock).bodyToMono(BoardDTO.class);

        ConstraintViolationException thrownException =
                Assertions.assertThrows(ConstraintViolationException.class, () ->
                        boardService.getBoard()
                );
        Assertions.assertNotNull(thrownException.getConstraintViolations());
        Assertions.assertEquals("Board width must be greater than 0",
                Objects.requireNonNull(thrownException.getConstraintViolations().stream()
                        .findFirst()
                        .orElse(null)).getMessage());

    }

    @Test
    void expect_throws_Illegal_Argument() throws Exception {
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/beanValidation/InvalidCommands1_wrongStartDirection.json", new TypeReference<>() {});
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> commandsService.getCommands());
    }

    @Test
    void expect_throws_Illegal_Argument2() throws Exception {
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/beanValidation/InvalidCommands2_wrongCommand.json", new TypeReference<>() {});
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> commandsService.getCommands());
    }

    @Test
    void expect_throws_ConstraintViolationException2() throws Exception {
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/beanValidation/InvalidCommands3_emptyCommands.json", new TypeReference<>() {});
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        ConstraintViolationException thrownException =
                Assertions.assertThrows(ConstraintViolationException.class, () ->
                        commandsService.getCommands()
                );
        Assertions.assertNotNull(thrownException.getConstraintViolations());
        Assertions.assertEquals("Commands cannot be empty. At least the START command must be present",
                Objects.requireNonNull(thrownException.getConstraintViolations().stream()
                        .findFirst()
                        .orElse(null)).getMessage());
    }

    @Test
    void expect_throws_ConstraintViolationException3() throws Exception {
        CommandsDTO mockCommandsDTO = TestUtils.readJson("/mocks/beanValidation/InvalidCommands4_wrongStartDirection.json", new TypeReference<>() {});
        doReturn(Mono.just(mockCommandsDTO)).when(responseMock).bodyToMono(CommandsDTO.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> commandsService.getCommands());
    }
}
