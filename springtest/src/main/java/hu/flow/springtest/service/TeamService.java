package hu.flow.springtest.service;

import hu.flow.springtest.entity.Team;
import hu.flow.springtest.exception.ValidationException;
import hu.flow.springtest.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team createTeam(Team team) {
        validateTeam(team);
        team.setId(UUID.randomUUID().toString());
        return teamRepository.save(team);
    }

    public Team updateTeam(Team team) {
        validateTeam(team);
        String id = team.getId();
        Optional<Team> optionalOldTeam = teamRepository.findById(id);
        if (optionalOldTeam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Team oldTeam = optionalOldTeam.get();
        oldTeam.setName(team.getName());
        oldTeam.setUniverse(team.getUniverse());
        oldTeam.setKind(team.getKind());
        Team updatedTeam = teamRepository.save(oldTeam);
        return updatedTeam;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeam(String id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalTeam.get();
    }

    private void validateTeam(Team team) {
        if (!StringUtils.hasText(team.getName())) {
            throw new ValidationException("Missing name");
        }
        if (team.getUniverse() == null) {
            throw new ValidationException("Missing universe");
        }
        if (team.getKind() == null) {
            throw new ValidationException("Missing kind");
        }

    }
}
