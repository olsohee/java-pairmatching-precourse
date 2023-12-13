package pairmatching.view;

import pairmatching.dto.PairDto;
import pairmatching.message.OutputMessage;

import java.util.List;

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

    public void printMatchingResult(List<PairDto> pairDtos) {
        System.out.println(OutputMessage.MATCHING_RESULT.getMessage());
        for (int i = 0; i < pairDtos.size(); i++) {
            PairDto pairDto = pairDtos.get(i);
            if (pairDto.getCrew3() == null) {
                System.out.println(String.format(OutputMessage.MATCHING_RESULT_PAIR.getMessage(),
                        pairDtos.get(i).getCrew1(),
                        pairDtos.get(i).getCrew2()));
                continue;
            }
            System.out.println(String.format(OutputMessage.MATCHING_RESULT_PAIR_THREE.getMessage(),
                    pairDtos.get(i).getCrew1(),
                    pairDtos.get(i).getCrew2(),
                    pairDtos.get(i).getCrew3()));
            break;
        }
        System.out.println();
    }

    public void printReset() {
        System.out.println(OutputMessage.RESET);
    }
}
