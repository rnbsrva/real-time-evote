package com.akerke.votingservice.poll;

import com.akerke.votingservice.poll.dto.PollSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PollService {

    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;

    Poll savePoll(PollSaveDTO pollSaveDto){
        log.info("Saving poll: {}", pollSaveDto);

        Poll poll = new Poll();
        poll.setTitle(pollSaveDto.name());

        var savedPoll = pollRepository.save(poll);
        savedPoll.setOptions(this.saveOptions(pollSaveDto.options(), savedPoll));
        return pollRepository.save(savedPoll);
    }

    private List<Option> saveOptions (List<String> optionTexts, Poll poll) {
        return optionTexts.stream()
                .map(optionText -> {
                    Option option = new Option();
                    option.setText(optionText);
                    option.setPoll(poll);
                    return optionRepository.save(option);
                })
                .collect(Collectors.toList());
    }

}
