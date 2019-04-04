package pl.wizard.software.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void centaurAttackImp(){
        Creature centaur = new Creature();
        Creature imp = new Creature();

        centaur.attack(imp);

        assertEquals(2, imp.getCurrentHp());

    }

}