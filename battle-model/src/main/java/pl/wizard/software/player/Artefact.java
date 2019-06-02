package pl.wizard.software.player;

import java.util.Set;
import pl.wizard.software.player.Equipment.slots;

import static pl.wizard.software.player.Equipment.slots.*;


public class Artefact {

    private final slots type;
    int attack;
    int defence;
    int intelligence;
    int charisma;

    Artefact(){
        this(NOTHING, 0,0,0,0);
    }

    public Artefact(slots aType, int aAttack, int aDefence, int aIntelligence, int aCharisma){
        type = aType;
        attack = aAttack;
        defence = aDefence;
        intelligence = aIntelligence;
        charisma = aCharisma;
    }

    void turnOnTheArtefact(Hero aHero){
        aHero.improve(attack, defence, intelligence, charisma);
    }

    void turnOffTheArtefact(Hero aHero){
        aHero.worsen(attack, defence, intelligence, charisma);
    }

    public slots getType() {
        return type;
    }
}
