package pl.wizard.software.battlefield;

import javafx.util.Pair;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class BattleEngine {

    public static final String END_OF_TURN = "END_OF_TURN";
    public static final String CURRENT_CREATURE_MOVED = "CURRENT_CREATURE_MOVED";
    public static final int MAP_MAX_WIDTH = 15;
    public static final int MAP_MAX_HEIGHT = 10;
    public static final String NEXT_CREATURE = "NEXT_CREATURE";
    public static final String CREATURE_ATACKED = "CREATURE_ATTACKED";

    private final Pair<Hero, Hero> heroes;
    private final Queue<Creature> creaturesQueue;
    private final List<Creature> creatureMovedInThisTurn;
    private Creature currentCreature;
    private final PropertyChangeSupport listenersSupport;
    private final BattleMap battleMap;
    private boolean wasMovedInThisTurn;

    public BattleEngine(Hero aHero1, Hero aHero2) {
        heroes = new Pair(aHero1, aHero2);
        battleMap = new BattleMap(aHero1, aHero2);

        creaturesQueue = new LinkedList<>();
        creatureMovedInThisTurn = new ArrayList<>();

        List<Creature> allCreatures = sortAllCreaturesBySpeed(aHero1, aHero2);
        listenersSupport = new PropertyChangeSupport(this);
        allCreatures.forEach(c -> c.subscribeMe(this));
        creaturesQueue.addAll(allCreatures);
        nextCreature();

    }

    public void addPropertyChangeListener(String aPropertyName, PropertyChangeListener aListener) {
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

        if (creaturesQueue.isEmpty()) {
            endOfTrun();
            for (Creature creature: creatureMovedInThisTurn) {
                if(creature.isAlive()){
                    creaturesQueue.add(creature);
                }
            }
            creatureMovedInThisTurn.clear();
        }


        currentCreature = creaturesQueue.poll();
        if (!currentCreature.isAlive()){
            nextCreature();
            return;
        }
        wasMovedInThisTurn = false;
        creatureMovedInThisTurn.add(currentCreature);


        currentCreature.getHero().ifPresent(c -> c.getSpec().useSpecialAbility());

    }

    private void endOfTrun() {
        listenersSupport.firePropertyChange(END_OF_TURN, null, null);
    }

    public Creature getCurrentCreature() {
        return currentCreature;
    }

    public void pass() {
        Creature oldCreature = currentCreature;
        nextCreature();
        listenersSupport.firePropertyChange(NEXT_CREATURE, oldCreature, currentCreature);
    }

    public void attack(Creature aTarget) {
        if (!isAttackPossible(aTarget)) {
            throw new IllegalArgumentException("Creature is out of range");
        }
        if(currentCreature.attack(aTarget) != -1){
        nextCreature();
        listenersSupport.firePropertyChange(CREATURE_ATACKED, null, null);}
    }

    public Optional<Creature> getCreatureByPosition(Point aPosition) {
        return battleMap.getCreatureByPosition(aPosition);
    }

    Point getPositionByCreature(Creature aCreature) {
        return battleMap.getPositionByCreature(aCreature);
    }

    public boolean isMovePossible(Point aPoint) {
        return battleMap.isMovePossible(currentCreature, aPoint);
    }

    public void move(Point aPoint) {
        if (!wasMovedInThisTurn) {
            Point oldPosition = battleMap.getPositionByCreature(currentCreature);
            battleMap.move(currentCreature, aPoint);
            Point newPosition = battleMap.getPositionByCreature(currentCreature);
            wasMovedInThisTurn = true;
            listenersSupport.firePropertyChange(CURRENT_CREATURE_MOVED, oldPosition, newPosition);
        }
    }

    public boolean isAttackPossible(Creature aCreature) {
        Point defenderPosition = battleMap.getPositionByCreature(aCreature);
        Point attackerPosition = battleMap.getPositionByCreature(currentCreature);
        return defenderPosition.distance(attackerPosition) == 1.0;
    }
}
