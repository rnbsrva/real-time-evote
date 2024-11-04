package com.akerke.votingservice.vote;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "votes")
public class Vote {

    @Id
    private String id;
    @Field("created_date")
    private LocalDateTime createdDate;
    @Field("poll_id")
    private String pollId;

    @Field("option_id")
    private String optionId;

    @Field("email")
    private String email;

}
