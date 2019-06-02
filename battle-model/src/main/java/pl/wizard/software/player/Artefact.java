package pl.wizard.software.player;

import pl.wizard.software.player.Equipment.slots;

import static pl.wizard.software.player.Equipment.slots.NOTHING;


public class Artefact {

    private final slots type;
    private int attack;
    private int defence;
    private int intelligence;
    private int charisma;

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

    slots getType() {
        return type;
    }
}
