package edu.mns.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(KeyPlayerResponses.class)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter
@Getter
public class PlayerResponses {
    @Id
    private int userId;

    @Id
    private int scenarioId;

    @Id
    private int questionId;

    @Id
    private int answerId;

    @ManyToOne
    @MapsId("user_id")
    private User user;

    @ManyToOne
    @MapsId("scenario_id")
    private Scenario scenario;

    @ManyToOne
    @MapsId("question_id")
    private Question question;

    @ManyToOne
    @MapsId("answer_id")
    private Answer answer;
}
