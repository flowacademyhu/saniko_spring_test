package hu.flow.springtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SuperHero {
    @Id
    String id;
    String name;
    Universe universe;
    @OneToOne
    Team team;
    Boolean hero;
}
