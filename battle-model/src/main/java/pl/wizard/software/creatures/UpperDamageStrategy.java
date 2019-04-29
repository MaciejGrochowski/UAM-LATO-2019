package pl.wizard.software.creatures;

class UpperDamageStrategy implements CalculateDamageStrategyIf {

    @Override
    public int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
        int damageToDeal = aAtacker.getAttack().upperEndpoint() - aDefender.getDefence();
        if (damageToDeal > 0) {
            return aAtacker.getCurrentAmount() * damageToDeal;
        } else {
            return aAtacker.getCurrentAmount() * 1;
        }
    }
}
