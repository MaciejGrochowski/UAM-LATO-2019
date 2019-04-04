package pl.wizard.software.creatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void impShouldLostTwoHpAfterCentaurAttack(){
        Creature centaur = parepareCentaur();
        Creature imp = prepareImp();

        centaur.attack(imp);

        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void centaurShouldLostOnlyOneHpBecauseHisDefenseIsGreaterThanImpAttack(){
        Creature centaur = parepareCentaur();
        Creature imp = prepareImp();

        imp.attack(centaur);

        assertEquals(9, centaur.getCurrentHp());
    }

    @Test
    void impShouldCounterAttack(){
        Creature centaur = parepareCentaur();
        Creature imp = prepareImp();

        imp.attack(centaur);

        //then
        assertEquals(9, centaur.getCurrentHp());
        assertEquals(2, imp.getCurrentHp());
    }

    private Creature prepareImp() {
        return new Creature("Imp",4,2,3);
    }

    private Creature parepareCentaur() {
        return new Creature("Centaur", 10,5,3);
    }

}