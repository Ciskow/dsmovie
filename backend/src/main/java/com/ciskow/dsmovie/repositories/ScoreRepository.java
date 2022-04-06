package com.ciskow.dsmovie.repositories;

import com.ciskow.dsmovie.entities.Score;
import com.ciskow.dsmovie.entities.ScorePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {
}
