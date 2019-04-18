package pl.wizard.software.creatures;

class LowerDamageStragegy implements CalculateDamageStrategyIf {

    @Override
    public int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
        int damageToDeal = aAtacker.getAttack().lowerEndpoint() - aDefender.getDefence();
        if (damageToDeal > 0) {
            return aAtacker.getCurrentAmount() * damageToDeal;
        } else {
            return aAtacker.getCurrentAmount() * 1;
        }
    }
}
