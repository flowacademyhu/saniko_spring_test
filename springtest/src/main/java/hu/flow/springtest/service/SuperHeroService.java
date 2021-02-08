package hu.flow.springtest.service;

import hu.flow.springtest.entity.Kind;
import hu.flow.springtest.entity.SuperHero;
import hu.flow.springtest.entity.Team;
import hu.flow.springtest.exception.ValidationException;
import hu.flow.springtest.repository.SuperHeroRepository;
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
public class SuperHeroService {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    @Autowired
    private TeamRepository teamRepository;

    public SuperHero createSuperHero(SuperHero superHero) {
        validateSuperHero(superHero);
        superHero.setId(UUID.randomUUID().toString());
        return superHeroRepository.save(superHero);
    }

    public SuperHero updateSuperHero(SuperHero superHero) {
        validateSuperHero(superHero);
        String id = superHero.getId();
        Optional<SuperHero> optionalOldSuperHero = superHeroRepository.findById(id);
        if (optionalOldSuperHero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        SuperHero oldSuperHero = optionalOldSuperHero.get();
        oldSuperHero.setName(superHero.getName());
        oldSuperHero.setUniverse(superHero.getUniverse());
        oldSuperHero.setHero(superHero.getHero());
        SuperHero updatedSuperHero = superHeroRepository.save(oldSuperHero);
        return updatedSuperHero;
    }


    public List<SuperHero> getAllSuperHeros() {
        return superHeroRepository.findAll();
    }

    public SuperHero getSuperHero(String id) {
        Optional<SuperHero> optionalSuperHero = superHeroRepository.findById(id);
        if (optionalSuperHero.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optionalSuperHero.get();
    }

    private void validateSuperHero(SuperHero superHero) {
        if (!StringUtils.hasText(superHero.getName())) {
            throw new ValidationException("Missing name");
        }
        if (superHero.getUniverse() == null) {
            throw new ValidationException("Missing universe");
        }
        Optional<Team> optionalTeam = teamRepository.findById(superHero.getTeam().getId());
        if (optionalTeam.isEmpty()) {
            throw new ValidationException("Unexpected team id");
        }
        if (!superHero.getUniverse().equals(superHero.getTeam().getUniverse())) {
            throw new ValidationException("Incorrect universe");
        }
        if (superHero.getHero() && !superHero.getTeam().getKind().equals(Kind.HERO)) {
            throw new ValidationException("Hero-kind problem");
        }
        if (!superHero.getHero() && !superHero.getTeam().getKind().equals(Kind.VILLAIN)) {
            throw new ValidationException("Hero-kind problem");
        }
    }
}
