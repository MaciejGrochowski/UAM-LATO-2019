package pl.wizard.software.creatures;

class ShootingCreature extends Creature {

    ShootingCreature(String aName, int aMaxHp, int aDefence, int aAttack) {
        super(aName, aMaxHp, aDefence, aAttack);
    }

    @Override
    void attack(Creature aDefender) {
        int damageToDeal = calculateDamageToDeal(this, aDefender);
        aDefender.setCurrentHp(aDefender.getCurrentHp()-damageToDeal);
    }
}
