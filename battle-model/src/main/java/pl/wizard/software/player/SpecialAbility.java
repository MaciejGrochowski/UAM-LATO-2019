package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;
import pl.wizard.software.creatures.CreatureBless;
import pl.wizard.software.creatures.CreatureMoreCounterAttacks;
import pl.wizard.software.creatures.CreatureSpeed;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class SpecialAbility {

    public static final String SPEED = "SPEED";
    public static final String MORE_COUNTER_ATTACKS = "MORE_COUNTER_ATTACKS";
    public static final String BLESS = "BLESS";

    private String ability;
    private PropertyChangeSupport listenersSupport;


    SpecialAbility(String aAbility){
        ability = aAbility;
        listenersSupport = new PropertyChangeSupport(this);

    }

    public void addPropertyChangeListener(String aPropertyName, PropertyChangeListener aListener) {
        listenersSupport.addPropertyChangeListener(aPropertyName, aListener);
    }

    public void setOnSpecialAbility(Hero aHero){
        List <Creature> newCreatures = new ArrayList<>();
        for (Creature aCreature : aHero.getCreatures()){

            if(ability == SPEED){
                newCreatures.add(new CreatureSpeed(aCreature));
                continue;
            }
            if(ability == BLESS){
                newCreatures.add(new CreatureBless(aCreature));
                continue;
            }

            if (ability == MORE_COUNTER_ATTACKS){
                newCreatures.add(new CreatureMoreCounterAttacks(aCreature));
            }


        }

        aHero.getCreatures().clear();

        for (Creature aCreature : newCreatures){
            aCreature.setHero(aHero);
        }

//        aHero.setCreatures(newCreatures);
        //listenersSupport.firePropertyChange(ability, null, null);
    }

    public String getType() {
        return ability;
    }
}
