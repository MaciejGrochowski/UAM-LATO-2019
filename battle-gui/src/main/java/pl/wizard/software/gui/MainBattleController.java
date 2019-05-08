package pl.wizard.software.gui;

import com.google.common.collect.Range;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

public class MainBattleController {

    private final BattleEngine engine;

    public MainBattleController(){
        Hero p1 = new Hero();
        p1.addCreature(new Creature("P1_C1",1, Range.closed(1,1),1,1));
        p1.addCreature(new Creature("P1_C2",1, Range.closed(2,2),2,2));
        p1.addCreature(new Creature("P1_C3",1, Range.closed(3,3),3,3));
        p1.addCreature(new Creature("P1_C4",1, Range.closed(4,4),4,4));
        p1.addCreature(new Creature("P1_C5",1, Range.closed(5,5),5,5));
        Hero p2 = new Hero();
        p2.addCreature(new Creature("P2_C1",1, Range.closed(1,1),1,1));
        p2.addCreature(new Creature("P2_C2",1, Range.closed(2,2),2,2));
        p2.addCreature(new Creature("P2_C3",1, Range.closed(3,3),3,3));
        p2.addCreature(new Creature("P2_C4",1, Range.closed(4,4),4,4));
        p2.addCreature(new Creature("P2_C5",1, Range.closed(5,5),5,5));

        engine = new BattleEngine(p1,p2);
    }
}
