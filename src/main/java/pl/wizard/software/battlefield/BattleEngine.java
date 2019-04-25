package pl.wizard.software.battlefield;

import javafx.util.Pair;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.stream.Collectors;

public class BattleEngine {

    public static final String END_OF_TURN = "END_OF_TURN";
    private final Pair<Hero, Hero> heroes;
    private final Queue<Creature> creaturesQueue;
    private final List<Creature> creatureMovedInThisTurn;
    private Creature currentCreature;
    private final PropertyChangeSupport listenersSupport;

    public BattleEngine(Hero aHero1, Hero aHero2) {
        heroes = new Pair(aHero1, aHero2);
        creaturesQueue = new LinkedList<>();
        creatureMovedInThisTurn = new ArrayList<>();

        List<Creature> allCreatures = sortAllCreaturesBySpeed(aHero1, aHero2);
        listenersSupport = new PropertyChangeSupport(this);
        allCreatures.forEach(c -> c.subscribeMe(this));
        creaturesQueue.addAll(allCreatures);

        nextCreature();
    }

    public void addPropertyChangeListener(String aPropertyName, PropertyChangeListener aListener){
        listenersSupport.addPropertyChangeListener(aPropertyName, aListener);
    }

    private List<Creature> sortAllCreaturesBySpeed(Hero aHero1, Hero aHero2) {
        List<Creature> allCreatures = new ArrayList<>();
        allCreatures.addAll(aHero1.getCreatures());
        allCreatures.addAll(aHero2.getCreatures());
        allCreatures = allCreatures.stream().sorted(Comparator.comparingInt(Creature::getSpeed).reversed()).collect(Collectors.toList());
        return allCreatures;
    }

    private void nextCreature() {
        currentCreature = creaturesQueue.poll();
        creatureMovedInThisTurn.add(currentCreature);

        if (creaturesQueue.isEmpty()) {
            endOfTrun();
            creaturesQueue.addAll(creatureMovedInThisTurn);
            creatureMovedInThisTurn.clear();
        }
    }

    void endOfTrun() {
        listenersSupport.firePropertyChange(END_OF_TURN,null,null);
    }

    Creature getCurrentCreature() {
        return currentCreature;
    }

    void pass() {
        nextCreature();
    }

    void attack(Creature aTarget) {
        currentCreature.attack(aTarget);
        nextCreature();
    }
}
