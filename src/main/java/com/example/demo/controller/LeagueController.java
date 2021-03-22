package com.example.demo.controller;

import com.example.demo.domain.Contender;
import com.example.demo.domain.League;
import com.example.demo.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
public class LeagueController {
    @Autowired
    private LeagueService service;

    @GetMapping("/campeonatos")
    public List<League> listLeagues() {
        return this.service.listLeagues();
    }

    @GetMapping("/campeonatos/{id}")
    public League getLeague(@PathVariable("id") Long id) {
        return this.service.getLeague(id);
    }

    @GetMapping("/campeonatos/{id}/tabela")
    public List<Contender> getLeagueRanking(@PathVariable Long id) {
        return this.service.getLeagueRanking(id);
    }

    @PostMapping("/campeonatos")
    public League createLeague(@RequestBody Map<String, String> json) throws ParseException {
        return this.service.createLeague(json);
    }

    @PostMapping("/campeonatos/{id}/times")
    public League addTeamOnLeague(@PathVariable Long id, @RequestBody Map<String, Long> json) {
        return this.service.addTeamOnLeague(id, json);
    }
}
