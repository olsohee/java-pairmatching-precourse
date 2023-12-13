package pairmatching.controller;

import pairmatching.convertor.InputConvertor;
import pairmatching.domain.command.FunctionCommand;
import pairmatching.domain.Condition;
import pairmatching.domain.command.RematchingCommand;
import pairmatching.service.Service;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final InputConvertor inputConvertor;
    private final OutputView outputView;
    private final Service service;
    private FunctionCommand functionCommand;
    private boolean isEnd = false;

    public Controller(InputView inputView, InputConvertor inputConvertor, OutputView outputView, Service service) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
        this.outputView = outputView;
        this.service = service;
    }

    public void start() {
        while (!isEnd) {
            readCommand();
            if (functionCommand == FunctionCommand.MATCHING) {
                outputView.printNotice();
                startMatching();
            }
            if (functionCommand == FunctionCommand.CHECK) {
                outputView.printNotice();
                startCheck();
            }
            if (functionCommand == FunctionCommand.RESET) {
                startReset();
            }
            if (functionCommand == FunctionCommand.QUIT) {
               isEnd = true;
            }
        }
    }

    private void readCommand() {
        try {
            functionCommand = FunctionCommand.getCommand(inputView.readCommand());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            readCommand();
        }
    }

    private void startMatching() {
        try {
            Condition condition = inputConvertor.convertStringToCondition(inputView.readMatchingCondition());
            validateRecordByCondition(condition);
            service.startMatching(condition);
            outputView.printMatchingResult(service.getMatchingResultDto(condition));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            startMatching();
        }
    }

    private void validateRecordByCondition(Condition condition) {
        if (service.isExistByCondition(condition)) {
            RematchingCommand rematchingCommand = RematchingCommand.getRematchingCommand(inputView.readRematching());
            if (rematchingCommand == RematchingCommand.END) {
                startMatching();
            }
        }
    }

    private void startCheck() {
        try {
            Condition condition = inputConvertor.convertStringToCondition(inputView.readMatchingCondition());
            outputView.printMatchingResult(service.getMatchingResultDto(condition));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            startCheck();
        }
    }

    private void startReset() {
        service.reset();
        outputView.printReset();
    }
}
