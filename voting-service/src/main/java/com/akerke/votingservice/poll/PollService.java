package com.akerke.votingservice.poll;

import com.akerke.votingservice.common.KafkaMessageSender;
import com.akerke.votingservice.common.Topic;
import com.akerke.votingservice.poll.dto.PollDTO;
import com.akerke.votingservice.poll.dto.PollSaveDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PollService {

    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;
    private final PollMapper pollMapper;
    private final KafkaMessageSender kafkaMessageSender;

    @Transactional
    Poll savePoll(PollSaveDTO pollSaveDto){
        log.info("Saving poll: {}", pollSaveDto);

        Poll poll = new Poll();
        poll.setTitle(pollSaveDto.name());

        var savedPoll = pollRepository.save(poll);
        savedPoll.setOptions(this.saveOptions(pollSaveDto.options(), savedPoll));

        kafkaMessageSender.sendMessage(Topic.POLL.name(), pollMapper.toDTO(savedPoll));

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
