package edu.mns.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class KeyPlayerResponses implements Serializable{
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "scenario_id")
    private Integer scenarioId;

    @Column(name = "question_id")
    private int questionId;

    @Column(name = "answer_id")
    private int answerId;

}
