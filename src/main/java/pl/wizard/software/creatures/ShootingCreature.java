package pl.wizard.software.creatures;

import com.google.common.collect.Range;

class ShootingCreature extends Creature {

    ShootingCreature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence) {
        super(aName, aMaxHp, aAttack, aDefence);
    }

    @Override
    void attack(Creature aDefender) {
        int damageToDeal = getGeberish().calculateDamageToDeal(this, aDefender);
        aDefender.setCurrentHp(aDefender.getCurrentHp()-damageToDeal);
    }


}
