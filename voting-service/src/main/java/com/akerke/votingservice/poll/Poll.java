package com.akerke.votingservice.poll;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "poll")
@Getter
@Setter
public class Poll {

    @Id
    private String id;

    @Field("title")
    private String title;

    @DBRef
    private List<Option> options;

}
