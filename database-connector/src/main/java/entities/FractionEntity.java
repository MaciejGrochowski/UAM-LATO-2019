package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FractionEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CreatureEntity> creatures;

    public FractionEntity() {
    }

    public FractionEntity(String aName) {
        name = aName;
        creatures = new HashSet<>();
    }

    public void addCreatureToFraction(CreatureEntity aCreature){
        creatures.add(aCreature);
    }

    @Override
    public String toString() {
        return "FractionEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
