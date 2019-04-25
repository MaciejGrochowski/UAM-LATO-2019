package pl.wizard.software.battlefield;

import javafx.util.Pair;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.util.*;
import java.util.stream.Collectors;

class BattleEngine {

    private final Pair<Hero, Hero> heroes;
    private final Queue<Creature> creaturesQueue;
    private final List<Creature> creatureMovedInThisTurn;
    private Creature currentCreature;

    public BattleEngine(Hero aHero1, Hero aHero2) {
        heroes = new Pair(aHero1, aHero2);
        creaturesQueue = new LinkedList<>();
        creatureMovedInThisTurn = new ArrayList<>();

        List<Creature> allCreatures = sortAllCreaturesBySpeed(aHero1, aHero2);
        creaturesQueue.addAll(allCreatures);

        nextCreature();
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

    private void endOfTrun() {

    }

    Creature getCurrentCreature() {
        return currentCreature;
    }

    void pass() {
        nextCreature();
    }
}
