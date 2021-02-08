package hu.flow.springtest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team_table")
public class Team {
    @Id
    String id;
    String name;
    Universe universe;
    Kind kind;
}
