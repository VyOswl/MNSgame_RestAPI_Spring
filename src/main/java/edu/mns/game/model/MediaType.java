package edu.mns.game.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrémenté
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="mediaType")
    private List<Media> media= new ArrayList<>();
}
