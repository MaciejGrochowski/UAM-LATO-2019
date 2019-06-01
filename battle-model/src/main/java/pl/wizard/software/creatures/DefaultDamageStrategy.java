package pl.wizard.software.creatures;

public class DefaultDamageStrategy implements CalculateDamageStrategyIf {

    public int calculateDamageToDeal(Creature aAtacker, Creature aDefender){
        int damageToDeal = aAtacker.getAttack().lowerEndpoint() - aDefender.getDefence();

        if (aAtacker.getHero().isPresent()) {
            damageToDeal += aAtacker.getHero().get().getAttack();
        }

        if (aDefender.getHero().isPresent()){
            damageToDeal -= aDefender.getHero().get().getDefence();
        }
        if (damageToDeal > 0) {
            return aAtacker.getCurrentAmount() * damageToDeal;
        } else {
            return aAtacker.getCurrentAmount();
        }

    }
}
