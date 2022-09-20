package edu.mns.game.dao;

import edu.mns.game.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeDao extends JpaRepository<Theme, Integer> {

}
