package pl.wizard.software.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TddExampleTest {

    private final String C1_NAME = "Creature_1";
    private final String C2_NAME = "Creature_2";
    private final int C1_ATTACK = 4;
    private final int C1_DEFENCE = 5;
    private final int C1_HP = 10;
    private final int C2_ATTACK = 2;
    private final int C2_DEFENCE = 2;
    private final int C2_HP = 4;


    @Test
    void firstCreatureShouldMakeDamageForSecond() {
        Creature creature1 = new Creature(C1_NAME, C1_ATTACK, C1_DEFENCE, C1_HP);
        Creature creature2 = new Creature(C2_NAME, C2_ATTACK, C2_DEFENCE, C2_HP);

        creature1.attack(creature2);

        assertEquals(2, creature1.getCurrentHp());
    }
}
