package com.example.demo.service;

import com.example.demo.domain.League;
import com.example.demo.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LeagueService {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));
    @Autowired
    private TeamService teamService;
    private List<League> leagues = new ArrayList<>();

    public List<League> listLeagues() {
        return this.leagues;
    }

    public League getLeague(Integer id) {
        return this.leagues.get(id);
    }

    public League createLeague(Map<String, String> params) throws ParseException {
        League newLeague = new League();
        newLeague.setName(params.get("name"));
        newLeague.setBeginDate(dateFormat.parse(params.get("begin")));
        newLeague.setEndDate(dateFormat.parse(params.get("end")));
        this.leagues.add(newLeague);
        return newLeague;
    }

    public Map<String, String> addTeamOnLeague(Integer id, Map<String, Integer> params) {
        Map<String, String> response = new HashMap<>();
        League league = this.leagues.get(id);
        Integer teamId = params.get("teamId");

        if (Objects.nonNull(league) && Objects.nonNull(teamId)) {
            Team team;
            Integer points = params.get("points");
            if (Objects.nonNull(points)) {
                team = this.teamService.addTeamOnLeague(teamId, league, points);
            } else {
                team = this.teamService.addTeamOnLeague(teamId, league);
            }
            league.addContender(team);
            response.put("message", "Success.");
            return response;
        }
        response.put("message", "Failed.");
        response.put("reason", String.format("%s não pode ser nulo.", Objects.isNull(league) ? "Campeonato" : "Time"));
        return response;
    }

    public List<Map<String, Integer>> getLeagueRanking(Integer id) {
        List<Map<String, Integer>> ranking = new ArrayList<>();
        League currentLeague = this.leagues.get(id);
        List<Team> contenders = currentLeague.getContenders();
//        Collections.sort(contenders, new League.RankingComparator(currentLeague));
        // Modificacao aleatoria
        // Alteração de nova branch
        // Alteração pelo github
        contenders.sort(new League.RankingComparator(currentLeague));
        contenders.forEach(team -> {
            Map<String, Integer> teamPoints = new HashMap<>();
            teamPoints.put(team.getName(), team.getPointsOnLeague(currentLeague));
            ranking.add(teamPoints);
        });
        return ranking;
    }
}
