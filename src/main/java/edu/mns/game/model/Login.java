package edu.mns.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrmenté
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    //Stocke heure et date
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @ManyToOne
   //@JoinColumn(name="user_id")
    private User user;
}
