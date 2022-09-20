package edu.mns.game.dao;

import edu.mns.game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer > {

    @Query("FROM User u JOIN FETCH u.roleList WHERE u.email = :email")
    Optional<User> findByEmailWithRoles(@Param("email") String email);

    User findByEmail(String email);
    Optional<User> findByLastname(String lastname);
    Optional<User> findByFirstname( String firstname);

    @Query(value="FROM User u INNER JOIN u.listScenarios s WHERE s.id = :scenario_id")
    Optional<List<User>> findAllByScenario(@Param("scenario_id") int scenario_id);
}
