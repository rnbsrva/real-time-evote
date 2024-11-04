package com.akerke.votingservice.poll;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document
public class Option {

    @Id
    private String id;

    @Field("text")
    private String text;

    @DBRef
    private Poll poll;
}