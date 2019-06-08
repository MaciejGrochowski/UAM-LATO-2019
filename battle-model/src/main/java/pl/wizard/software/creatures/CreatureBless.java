package pl.wizard.software.creatures;

import com.google.common.collect.Range;
import pl.wizard.software.player.Hero;

public class CreatureBless extends Creature {

    public CreatureBless(Creature aCreature) {
        super(aCreature.getName(), aCreature.getMaxHp(), aCreature.getAttack(), aCreature.getDefence(), new UpperDamageStrategy(), aCreature.getSpeed(), null, aCreature.getCurrentAmount());
    }


    @Override
    CalculateDamageStrategyIf getDealDamageStrategy() {
        return new UpperDamageStrategy();
    }


}
