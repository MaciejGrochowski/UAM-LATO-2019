package pl.wizard.software.creatures;

import java.util.Random;

public class RealDamageStrategy extends DefaultDamageStrategy {

    private Random rand;

    RealDamageStrategy(){
        rand = new Random();
    }

    @Override
    public int calculateDamageToDeal(Creature aAtacker, Creature aDefender){
        int dmg = super.calculateDamageToDeal(aAtacker, aDefender);

        if (aAtacker.getHero().isPresent()) {
            if (rand.nextDouble() < aAtacker.getHero().get().criticalchance) {
                dmg *= 2;
            }
        }
        return dmg;
    }
}
