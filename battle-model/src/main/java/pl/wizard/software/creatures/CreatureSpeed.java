package pl.wizard.software.creatures;

public class CreatureSpeed extends Creature {

    public CreatureSpeed(Creature aCreature) {
        super(aCreature.getName(), aCreature.getMaxHp(), aCreature.getAttack(), aCreature.getDefence(), aCreature.getDealDamageStrategy(), aCreature.getSpeed(), null, aCreature.getCurrentAmount());
    }

    @Override
    public int getSpeed(){
        return super.getSpeed()+1;
    }
}
