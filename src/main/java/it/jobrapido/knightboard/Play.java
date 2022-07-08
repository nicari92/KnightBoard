package it.jobrapido.knightboard;

import it.jobrapido.knightboard.exceptions.GenericException;
import it.jobrapido.knightboard.exceptions.InvalidCommandException;
import it.jobrapido.knightboard.exceptions.InvalidStartPositionException;
import it.jobrapido.knightboard.exceptions.OutOfTheBoardException;
import it.jobrapido.knightboard.interfaces.service.ConsolePrinterService;
import it.jobrapido.knightboard.interfaces.service.PlayService;
import it.jobrapido.knightboard.model.Knight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("!test")
public class Play implements CommandLineRunner {

    @Autowired
    private PlayService playService;
    @Autowired
    private ConsolePrinterService consolePrinterService;

    @Override
    public void run(String... args) throws Exception {
        try {
            playService.initGame();
            Knight knightFinalPosition = playService.playGame();
            if (knightFinalPosition != null) {
                consolePrinterService.printSuccessResultAsJson(knightFinalPosition);
            } else {
                consolePrinterService.printGenericErrorResultAsJson();
            }
        } catch (InvalidStartPositionException ex) {
            consolePrinterService.printInvalidStartPositionResultAsJson();
        } catch (InvalidCommandException |GenericException ex) {
            consolePrinterService.printGenericErrorResultAsJson();
        } catch (OutOfTheBoardException ex) {
            consolePrinterService.printOutOfTheBoardResultAsJson();
        }
    }
}
