package edu.mns.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrmenté
    private int id;

    private String content;

    private boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    //@JoinColumn("question_id")
    private Question chainedQuestion;

    @ManyToOne
    private Question question;

    @ManyToOne
    @JsonIgnore
    //@JoinColumn("creator_id")
    private Creator creator;
}
