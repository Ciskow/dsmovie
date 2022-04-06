package com.ciskow.dsmovie.services;

import com.ciskow.dsmovie.dto.MovieDTO;
import com.ciskow.dsmovie.dto.ScoreDTO;
import com.ciskow.dsmovie.entities.Movie;
import com.ciskow.dsmovie.entities.Score;
import com.ciskow.dsmovie.entities.User;
import com.ciskow.dsmovie.repositories.MovieRepository;
import com.ciskow.dsmovie.repositories.ScoreRepository;
import com.ciskow.dsmovie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public MovieDTO saveScore(ScoreDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(dto.getEmail());
            userRepository.saveAndFlush(user);
        }

        Movie movie = movieRepository.findById(dto.getMovieId()).get();

        Score score = new Score();
        score.setMovie(movie);
        score.setUser(user);
        score.setValue(dto.getScore());
        scoreRepository.saveAndFlush(score);

        Double avg = movie.getScores().stream().mapToDouble(Score::getValue).sum() / movie.getScores().size();

        movie.setScore(avg);
        movie.setCount(movie.getScores().size());
        movieRepository.save(movie);

        return new MovieDTO(movie);
    }

}
