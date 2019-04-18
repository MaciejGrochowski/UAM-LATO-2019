package pl.wizard.software.creatures;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    Creature centaur;
    Creature imp;


    @BeforeEach
    void prepareCreatures(){
        centaur = prepareCentaur();
        imp = prepareImp();
    }

    private Creature prepareImp() {
        return new Creature("Imp",4, Range.closed(2,3),3, new LowerDamageStragegy());
    }

    private Creature prepareCentaur() {
        return new Creature("Centaur", 10,Range.closed(5,7),3, new LowerDamageStragegy());
    }


    @Test
    void impShouldLostTwoHpAfterCentaurAttack(){
        centaur.attack(imp);

        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void centaurShouldLostOnlyOneHpBecauseHisDefenseIsGreaterThanImpAttack(){
        imp.attack(centaur);

        assertEquals(9, centaur.getCurrentHp());
    }

    @Test
    void impShouldCounterAttack(){
        imp.attack(centaur);

        //then
        assertEquals(9, centaur.getCurrentHp());
        assertEquals(2, imp.getCurrentHp());
    }

    @Test
    void impShouldCounterAttackOnlyOnce(){
        centaur.attack(imp);
        centaur.attack(imp);

        assertEquals(9, centaur.getCurrentHp());
    }

    @Test
    void shootingCreatureShouldNotBeCounterAttacked(){
        Creature shootingCreature = new ShootingCreature("Shooting", 10, Range.closed(5,7), 3);

        shootingCreature.attack(imp);

        assertEquals(shootingCreature.getMaxHp(), shootingCreature.getCurrentHp());
    }

    @Test
    void shouldLostOneCreatureFtomStack(){
        centaur.addCreaturesToStack(1);
        imp.addCreaturesToStack(1);

        centaur.attack(imp);

        assertEquals(imp.getMaxHp(), imp.getCurrentHp());
        assertEquals(imp.getStartAmount() - 1,imp.getCurrentAmount());
        assertTrue(imp.isAlive());
    }

    @Test
    void cretureShouldDieIfHisCurrentHpIsNotMax(){
        imp.addCreaturesToStack(2);
        centaur.attack(imp);

        assertEquals(imp.getStartAmount(), imp.getCurrentAmount());
        assertEquals(2, imp.getCurrentHp());

        centaur.addCreaturesToStack(3);
        centaur.attack(imp);

        assertEquals(1, imp.getCurrentAmount());
        assertEquals(2, imp.getCurrentHp());
    }
}