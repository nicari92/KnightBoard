package it.jobrapido.knightboard;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "api.board=https://storage.googleapis.com/jobrapido-backend-test/board.json",
        "api.commands=https://storage.googleapis.com/jobrapido-backend-test/commands.json"
})
@ActiveProfiles("test")
@EnableAsync
@ExtendWith(MockitoExtension.class)
class KnightBoardApplicationTests {

    /*@Test
    void contextLoads() {
    }

    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private ExchangeFunction exchangeFunction;

    @Mock
    private WebClient webClient;

    @Mock
    private ApiPropertiesConfig apiProperties;

    @BeforeEach
    void init() {
        WebClient webClient = WebClient.builder()
                .exchangeFunction(exchangeFunction)
                .build();
    }

    @Test
    public void gameShouldRun() throws Exception {
        BoardDTO board = TestUtils.readJson("/mocks/ValidBoard1.json", new TypeReference<>() {});
        when(boardService.getBoard()).thenReturn(Mono.just(board));
        playService.initGame();
    }*/

    /*@Test
    public void boardValidationShouldWork() throws Exception {
        BoardDTO board = TestUtils.readJson("/mocks/InvalidBoard1.json", new TypeReference<>() {});
        when(webClient.get().uri(apiProperties.getBoard())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(BoardDTO.class)).thenReturn(Mono.just(board));
        //when(boardService.getBoard()).thenReturn(Mono.just(board));
        assertThatThrownBy(() -> boardService.getBoard())
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessageContaining("my error message");
    }*/

}
