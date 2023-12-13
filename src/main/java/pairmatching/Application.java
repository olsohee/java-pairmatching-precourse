package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.convertor.InputConvertor;
import pairmatching.service.Service;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        Controller controller = new Controller(InputView.getInstance(), InputConvertor.getInstance(),
                OutputView.getInstance(), new Service());
        controller.start();
    }
}
