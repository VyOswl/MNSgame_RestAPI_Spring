package edu.mns.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.mns.game.view.ViewAnswer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.annotation.MatchesPattern;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrémenté
    private int id;

    @Column(nullable = false)
    @Pattern(message="Le nom ne peut contenir que des caractères", regexp = "[a-zA-Z]+")
    private String lastname;

    @Column(nullable = false)
    @Pattern(message="Le prénom ne peut contenir que des caractères", regexp = "[a-zA-Z]+")
    private String firstname;

    @JsonView(ViewAnswer.class)
    @Column(unique = true, nullable = false)
    @Pattern(regexp="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;

    // @Column(nullable = false)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
    private String password;

    @ManyToMany // (fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleList = new HashSet<>();


    @JsonIgnore
    private int numberToken;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Login> logins;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Media> listMedias = new ArrayList<>();

    @ManyToOne
    // @Enumerated(EnumType.STRING)
    // @Column(columnDefinition = "ENUM('INTERVENANT', 'STAGIAIRE', 'COLLABORATEUR',
    // 'MNSTEAM')")
    // @JoinColumn(name="title_id")
    private Title title;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "GameParticipants", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "scenario_id"))
    private List<Scenario> listScenarios = new ArrayList<>();

    public void addDefaultRole(Role role) {
        this.roleList.add(role);
    }

    public User(int id, String lastname, String firstname, String email, String password, int numberToken) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.title = title;
        this.numberToken = 1;
    }
}