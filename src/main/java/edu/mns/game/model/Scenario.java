package edu.mns.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.game.view.ViewAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrmenté
    private int id;
    private String name;
    private String description;

    @ManyToOne
    //@JoinColumn(name = "creator_id")
    private Creator creator;

    @ManyToOne
    //@JoinColumn(name = "question_id")
    private Question firstQuestion;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="QuestionsInGame",
            joinColumns=@JoinColumn(name="scenario_id"),
            inverseJoinColumns = @JoinColumn(name="question_id")
    )
    private List<Question> listQuestions = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="GameParticipants",
            joinColumns=@JoinColumn(name="scenario_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private List<User> listParticipants = new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name="media_id")
    private Media media;

    private Boolean isPublished = false;
}
