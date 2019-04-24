package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    private final List<Creature> creatures;

    public Hero(){
        creatures = new ArrayList<>();
    }

    public void addCreature(Creature aCreature) {
        if(creatures.size()>=5){
            throw new IllegalStateException("Hero doesn't have empty slot for next creature");
        }
        creatures.add(aCreature);
    }

    public List<Creature> getCreatures() {
        return creatures;
    }
}
