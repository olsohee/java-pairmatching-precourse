package pairmatching.view;

import pairmatching.message.OutputMessage;

public class OutputView {

    private OutputView() {
    }

    private static class OutputViewHolder {
        private static OutputView outputView = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewHolder.outputView;
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printNotice() {
        System.out.println(OutputMessage.LINE.getMessage());
        System.out.println(OutputMessage.COURSE.getMessage());
        System.out.println(OutputMessage.MISSIONG.getMessage());
        System.out.println(OutputMessage.LINE.getMessage());
    }
}
