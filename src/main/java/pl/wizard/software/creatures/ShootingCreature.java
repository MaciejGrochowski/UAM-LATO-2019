package pl.wizard.software.creatures;

import com.google.common.collect.Range;

class ShootingCreature extends Creature {

    ShootingCreature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence) {
        super(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy());
    }

    @Override
    public void attack(Creature aDefender) {
        int damageToDeal = getDealDamageStrategy().calculateDamageToDeal(this, aDefender);
        aDefender.setCurrentHp(aDefender.getCurrentHp()-damageToDeal);
    }


}
