package pairmatching;

import pairmatching.controller.Controller;
import pairmatching.convertor.InputConvertor;
import pairmatching.service.Service;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {

        Controller controller = new Controller(InputView.getInstance(), InputConvertor.getInstance(),
                OutputView.getInstance(), new Service());
        controller.start();
    }
}
