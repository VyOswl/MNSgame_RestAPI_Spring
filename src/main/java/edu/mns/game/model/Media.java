package edu.mns.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="media")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrémenté
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="media")
    private List<Scenario> scenarios= new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="MediaOfQuestion",
            joinColumns=@JoinColumn(name="media_id"),
            inverseJoinColumns = @JoinColumn(name="scenario_id")
    )
    private List<Question> listQuestions = new ArrayList<>();

    @ManyToOne
    //@JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    //@JoinColumn("mediaType_id")
    private MediaType mediaType;

}
