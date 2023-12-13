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
        for (PairDto pairDto : pairDtos) {
            List<String> crewNames = pairDto.getCrewNames();
            if (crewNames.size() == 3) {
                System.out.println(String.format(OutputMessage.MATCHING_RESULT_PAIR_THREE.getMessage(),
                        crewNames.get(0), crewNames.get(1), pairDtos.get(2)));
                continue;
            }
            System.out.println(String.format(OutputMessage.MATCHING_RESULT_PAIR.getMessage(),
                    crewNames.get(0), crewNames.get(1)));
        }
        System.out.println();
    }

    public void printReset() {
        System.out.println(OutputMessage.RESET);
    }
}
