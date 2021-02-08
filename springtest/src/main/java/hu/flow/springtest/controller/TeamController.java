package hu.flow.springtest.controller;

import hu.flow.springtest.entity.Team;
import hu.flow.springtest.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {
    
    @Autowired
    private TeamService teamService;

    @PostMapping("/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/teams")
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/teams/{id}")
    public Team getTeam(@PathVariable String id) {
        return teamService.getTeam(id);
    }

}
