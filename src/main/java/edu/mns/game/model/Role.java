package edu.mns.game.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonView(VueMateriel.class)
    private Integer id;

    //@JsonView(VueMateriel.class)
    private String name;

    public Role(Integer id) {
        this.id = id;
    }

    public Role(String name) {
        this.name = name;
    }
}