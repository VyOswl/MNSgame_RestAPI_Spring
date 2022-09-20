package edu.mns.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrmenté
    private int id;

    private String content;

    private float points;

    //@JsonView(ViewAnswer.class)
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name="creator_id")
    private Creator creator;

    @JsonIgnore
    @OneToMany(mappedBy = "firstQuestion", fetch = FetchType.LAZY)
    private List<Scenario> listScenariosWithFirstQ = new ArrayList<>(); //liste des scénarios dont la première question est this.question

    //@JsonView(ViewAnswer.class)
    @JsonIgnore
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>(); //questions qui ont pour question suivante : this.question

    /*@OneToMany(mappedBy = "answer", fetch = FetchType.LAZY)
    private List<Answer> twoAnswers;*/
    //@JsonView(ViewAnswer.class)
    @JsonIgnore
    @OneToOne( cascade = CascadeType.MERGE )
    //@JoinColumn( name="answer_id", nullable=false , insertable = false, updatable = false)
    @JoinColumn( name="correct_answer_id" /* insertable = false, updatable = false*/)
    private Answer answerNbOne;

    //@JsonView(ViewAnswer.class)
    @JsonIgnore
    @OneToOne( cascade = CascadeType.MERGE )
    @JoinColumn( name="other_answer_id" )
    private Answer answerNbTwo;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="QuestionsInGame",
            joinColumns=@JoinColumn(name="question_id"),
            inverseJoinColumns = @JoinColumn(name="scenario_id")
    )
    private List<Scenario> listScenarios = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="MediaOfQuestion",
            joinColumns=@JoinColumn(name="scenario_id"),
            inverseJoinColumns = @JoinColumn(name="media_id")
    )
    private List<Media> listMedias = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="theme_id")
    private Theme theme;

    public Question(String content, float points) {
        this.content = content;
        this.points = points;
    }
}
