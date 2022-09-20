package edu.mns.game.dao;

import edu.mns.game.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {
      Role findByName(String name);
      Optional<Role> findById(Integer id);
}