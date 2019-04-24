package pl.wizard.software.battlefield;

import javafx.util.Pair;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.util.*;
import java.util.stream.Collectors;

class BattleEngine {

    private final Pair<Hero, Hero> heroes;
    private final Queue<Creature> creaturesQueue;
    private Creature currentCreature;

    public BattleEngine(Hero aHero1, Hero aHero2) {
        heroes = new Pair(aHero1, aHero2);
        creaturesQueue = new LinkedList<>();
        List<Creature> allCreatures = new ArrayList<>();
        allCreatures.addAll(aHero1.getCreatures());
        allCreatures.addAll(aHero2.getCreatures());
        allCreatures = allCreatures.stream().sorted(Comparator.comparingInt(Creature::getSpeed).reversed()).collect(Collectors.toList());
        creaturesQueue.addAll(allCreatures);

        nextCreature();
    }

    private void nextCreature() {
        currentCreature = creaturesQueue.poll();
        creaturesQueue.add(currentCreature);
    }

    Creature getCurrentCreature() {
        return currentCreature;
    }

    void pass() {
        nextCreature();
    }


}
