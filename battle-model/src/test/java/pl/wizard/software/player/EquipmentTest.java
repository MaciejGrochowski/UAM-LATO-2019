package pl.wizard.software.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import static pl.wizard.software.player.Equipment.slots.*;
import static pl.wizard.software.player.HeroTest.*;


class EquipmentTest {

    private Hero hero1;
    private Artefact item1;
    private Artefact item2;
    private Artefact item3;
    private Artefact item4;
    private Artefact item5;
    private Artefact item6;
    private Equipment eq;
    static final int IMPROVED = 1;


    @Test
    void heroShouldHaveBetterStatsBecauseOfArtefacts(){
        hero1 = new Hero(NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item1 = new Artefact(BOOTS, IMPROVED, NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item2 = new Artefact(ARMOR, NOT_IMPROVED, IMPROVED, NOT_IMPROVED, IMPROVED);
        eq = new Equipment();
        eq.add(item1);
        eq.add(item2);


        hero1.setEq(eq);
        assertEquals(1, hero1.getAttack());
        assertEquals(1, hero1.getDefence());
        assertEquals(0, hero1.getIntelligence());
        assertEquals(1, hero1.getCharisma());
        assertEquals(0.05, hero1.getCriticalChance());
    }

    @Test
    void heroShouldHaveWorseStatsBecauseOfRemoveArtefacts(){
        hero1 = new Hero(NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item1 = new Artefact(BOOTS, IMPROVED, NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item2 = new Artefact(ARMOR, NOT_IMPROVED, IMPROVED, NOT_IMPROVED, IMPROVED);
        eq = new Equipment();
        eq.add(item1);
        eq.add(item2);


        hero1.setEq(eq);
        hero1.getEq().turnOffArtefacts(hero1);
        assertEquals(0, hero1.getAttack());
        assertEquals(0, hero1.getDefence());
        assertEquals(0, hero1.getIntelligence());
        assertEquals(0, hero1.getCharisma());
    }

    @Test
    void heroShouldHaveFiveArtefacts(){
        hero1 = new Hero(NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item1 = new Artefact(BOOTS, IMPROVED, NOT_IMPROVED, NOT_IMPORTANT, NOT_IMPROVED);
        item2 = new Artefact(ARMOR, NOT_IMPROVED, IMPROVED, NOT_IMPROVED, IMPROVED);
        item3 = new Artefact(GLOVES, NOT_IMPROVED, NOT_IMPROVED, IMPROVED, IMPROVED);
        item4 = new Artefact(WEAPON, IMPROVED, IMPROVED, NOT_IMPROVED, NOT_IMPROVED);
        item5 = new Artefact(HELM, NOT_IMPROVED, NOT_IMPROVED, IMPROVED, IMPROVED);
        Set<Artefact> Artefacts = new HashSet<>();
        Artefacts.add(item1);
        Artefacts.add(item2);
        Artefacts.add(item3);
        Artefacts.add(item4);
        Artefacts.add(item5);
        hero1.setEq(new Equipment(Artefacts));
        assertEquals(2, hero1.getAttack());
        assertEquals(2, hero1.getDefence());
        assertEquals(2, hero1.getIntelligence());
        assertEquals(3, hero1.getCharisma());

    }

    @Test
    void heroShouldHaveSomeArtefactsButHeShouldntHaveTwoWeapons(){
        item1 = new Artefact(BOOTS, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        item2 = new Artefact(ARMOR, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        item4 = new Artefact(WEAPON, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        item6 = new Artefact(WEAPON, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        eq = new Equipment();
        eq.add(item1);
        eq.add(item2);
        eq.add(item4);
        assertThrows(IllegalArgumentException.class, () -> eq.add(item6));
    }

    @Test
    void heroShouldHaveEmptyEquipment(){
        item1 = new Artefact(BOOTS, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        item2 = new Artefact(ARMOR, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT, NOT_IMPORTANT);
        eq = new Equipment();
        eq.add(item1);
        eq.add(item2);
        assertFalse(eq.equipment.isEmpty());
        eq.remove(BOOTS);
        eq.remove(ARMOR);
        assertTrue(eq.equipment.isEmpty());

    }



}
