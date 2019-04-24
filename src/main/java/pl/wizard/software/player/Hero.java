package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

class Hero {

    private final List<Creature> creatures;

    Hero(){
        creatures = new ArrayList<>();
    }

    Hero(List<Creature> aCreatures) {
        creatures = aCreatures;
    }

    void addCreature(Creature aCreature) {
        if(creatures.size()>=5){
            throw new IllegalStateException("Hero doesn't have empty slot for next creature");
        }
        creatures.add(aCreature);
    }
}
