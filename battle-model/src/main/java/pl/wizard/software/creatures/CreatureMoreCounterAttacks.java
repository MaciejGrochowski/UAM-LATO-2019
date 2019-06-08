package pl.wizard.software.creatures;

import com.google.common.collect.Range;
import pl.wizard.software.player.Hero;

public class CreatureMoreCounterAttacks extends Creature {

    public CreatureMoreCounterAttacks(Creature aCreature) {
        super(aCreature.getName(), aCreature.getMaxHp(), aCreature.getAttack(), aCreature.getDefence(), aCreature.getDealDamageStrategy(), aCreature.getSpeed(), null, aCreature.getCurrentAmount());
    }

    @Override
    protected void counterAttack(Creature aAttacker) {
            int damageToDeal = getDealDamageStrategy().calculateDamageToDeal(this, aAttacker);
            aAttacker.dealDamageToMe(damageToDeal);
    }
}
