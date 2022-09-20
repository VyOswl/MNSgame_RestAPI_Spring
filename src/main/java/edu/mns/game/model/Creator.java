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

@Entity
/*@Inheritance
@DiscriminatorValue("C")*/
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Creator extends User{
    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Scenario> listScenarios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Question> listQuestions= new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Answer> listAnswers = new ArrayList<>();

    public Creator(int id, String lastname, String firstname, String email, String password, int numberToken) {
        super(id, lastname, firstname, email, password,numberToken);
    }
}
