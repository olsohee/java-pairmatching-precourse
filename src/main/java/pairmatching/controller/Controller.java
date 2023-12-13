package pairmatching.controller;

import pairmatching.convertor.InputConvertor;
import pairmatching.domain.Command;
import pairmatching.domain.Condition;
import pairmatching.service.Service;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final InputConvertor inputConvertor;
    private final OutputView outputView;
    private final Service service;
    private Command command;

    public Controller(InputView inputView, InputConvertor inputConvertor, OutputView outputView, Service service) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
        this.outputView = outputView;
        this.service = service;
    }

    public void start() {
        readCommmand();
        if (command == Command.MATCHING) {
            outputView.printNotice();
            playMatching();
        }
    }

    private void readCommmand() {
        try {
            String input = inputView.readCommand();
            command = Command.getCommand(input);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            readCommmand();
        }
    }

    private void playMatching() {
        try {
            Condition condition = inputConvertor.convertStringToCondition(inputView.readMatchingCondition());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            playMatching();
        }
    }
}
