package hu.flow.springtest.repository;

import hu.flow.springtest.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, String> {
}
