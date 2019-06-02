package pl.wizard.software.creatures;

import java.util.Random;

public class DefaultDamageStrategy implements CalculateDamageStrategyIf {

    public int calculateDamageToDeal(Creature aAtacker, Creature aDefender){
        Random rand = new Random();
        int cleardmg;
        if(aAtacker.getAttack().lowerEndpoint() == aAtacker.getAttack().upperEndpoint()){
            cleardmg = aAtacker.getAttack().lowerEndpoint();
        }
        else {
            cleardmg = rand.nextInt(aAtacker.getAttack().upperEndpoint() - aAtacker.getAttack().lowerEndpoint()) + aAtacker.getAttack().lowerEndpoint();
        }
        int damageToDeal = cleardmg - aDefender.getDefence();

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
