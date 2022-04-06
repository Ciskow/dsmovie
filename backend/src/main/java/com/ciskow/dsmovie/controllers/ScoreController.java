package com.ciskow.dsmovie.controllers;

import com.ciskow.dsmovie.dto.MovieDTO;
import com.ciskow.dsmovie.dto.ScoreDTO;
import com.ciskow.dsmovie.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    @Autowired
    private ScoreService service;

    @PutMapping
    public MovieDTO saveScore(@RequestBody ScoreDTO dto) {
        return service.saveScore(dto);
    }
}
