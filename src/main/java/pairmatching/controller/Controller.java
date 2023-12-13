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
    private boolean isEnd = false;

    public Controller(InputView inputView, InputConvertor inputConvertor, OutputView outputView, Service service) {
        this.inputView = inputView;
        this.inputConvertor = inputConvertor;
        this.outputView = outputView;
        this.service = service;
    }

    public void start() {
        while (!isEnd) {
            readCommmand();
            if (command == Command.MATCHING) {
                outputView.printNotice();
                startMatching();
            }
            if (command == Command.CHECK) {
                outputView.printNotice();
                startCheck();
            }
            if (command == Command.RESET) {
                startReset();
            }
            if (command == Command.QUIT) {
               isEnd = true;
            }
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

    private void startMatching() {
        try {
            Condition condition = inputConvertor.convertStringToCondition(inputView.readMatchingCondition());
//            if (service.isExistByCondition(condition)) {
                // 페어 재매칭 안내 문구 출력
//                return;
//            }
            // 페어매칭하기
            service.startMatching(condition);
            outputView.printMatchingResult(service.getMatchingResultDto(condition));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            startMatching();
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
