package com.akerke.votingservice.poll;

import com.akerke.votingservice.poll.dto.PollDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PollMapper {

    PollDTO toDTO(Poll poll);

}
