package pl.wizard.software.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.wizard.software.player.Equipment.slots;

import java.util.HashSet;
import java.util.Set;

import static pl.wizard.software.player.Equipment.slots.*;



class EquipmentTest {

    private Hero hero1;
    private Artefact item1;
    private Artefact item2;
    private Artefact item3;
    private Artefact item4;
    private Artefact item5;
    private Artefact item6;
    private Equipment eq;

    @BeforeEach
    void prepareHeroesAndArtefacts(){
        hero1 = new Hero(0,0,0,0);
        item1 = new Artefact(BOOTS, 1, 0, 0, 0);
        item2 = new Artefact(ARMOR, 0, 3, 0, 1);
        item3 = new Artefact(GLOVES, 0, 0, 2, 1);
        item4 = new Artefact(WEAPON, 7, 1, 0, 0);
        item5 = new Artefact(HELM, 0, 0, 4, 3);
        item6 = new Artefact(WEAPON, 100, 0, 0, 0);


        eq = new Equipment();
        eq.add(item1);
        eq.add(item2);


    }

    @Test
    void heroShouldHaveBetterStatsBecauseOfArtefacts(){
        hero1.setEq(eq);
        assertEquals(1, hero1.getAttack());
        assertEquals(3, hero1.getDefence());
        assertEquals(0, hero1.getIntelligence());
        assertEquals(1, hero1.getCharisma());
        assertEquals(0.05, hero1.getCriticalChance());
    }

    @Test
    void heroShouldHaveWorseStatsBecauseOfRemoveArtefacts(){
        hero1.setEq(eq);
        hero1.getEq().turnOffArtefacts(hero1);
        assertEquals(0, hero1.getAttack());
        assertEquals(0, hero1.getDefence());
        assertEquals(0, hero1.getIntelligence());
        assertEquals(0, hero1.getCharisma());
    }

    @Test
    void heroShouldHaveFiveArtefacts(){
        Set<Artefact> Artefacts = new HashSet<>();
        Artefacts.add(item1);
        Artefacts.add(item2);
        Artefacts.add(item3);
        Artefacts.add(item4);
        Artefacts.add(item5);
        hero1.setEq(new Equipment(Artefacts));
        assertEquals(8, hero1.getAttack());
        assertEquals(4, hero1.getDefence());
        assertEquals(6, hero1.getIntelligence());
        assertEquals(5, hero1.getCharisma());
        assertEquals(60, hero1.getActualMana());
        assertEquals(0.25, hero1.getCriticalChance());

    }

    @Test
    void heroShouldntHaveTwoWeapons(){
        eq.add(item4);
        assertThrows(IllegalArgumentException.class, () -> eq.add(item6));
    }

    @Test
    void heroShouldHaveEmptyEquipment(){
        hero1.getEq().remove(BOOTS);
        hero1.getEq().remove(ARMOR);

        assertTrue(hero1.getEq().equipment.isEmpty());

    }



}
