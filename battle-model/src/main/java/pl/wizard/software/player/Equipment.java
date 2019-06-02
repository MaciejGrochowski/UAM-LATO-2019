package pl.wizard.software.player;

import java.util.HashMap;
import java.util.Set;

public class Equipment {

    HashMap<slots, Artefact> equipment;

    public enum slots{ BOOTS, ARMOR, WEAPON, HELM, GLOVES, NOTHING}

    Equipment(){
        equipment = new HashMap<>();
    }

    Equipment(Set<Artefact> aItems){
        equipment = new HashMap<>();
        for (Artefact a : aItems ){
            if (equipment.containsKey(a.getType())){
                throw new IllegalArgumentException("Ekwipunek zawiera juz przedmiot tego rodzaju");
            }
            equipment.put(a.getType(), a);
        }
    }

    void add(Artefact aItem){
        if (equipment.containsKey(aItem.getType())){
            throw new IllegalArgumentException("Ekwipunek zawiera juz przedmiot tego rodzaju");
        }
        equipment.put(aItem.getType(), aItem);
    }

    void remove(slots aType){
        equipment.remove(aType);
    }

    void turnOnArtefacts(Hero aHero){
        equipment.forEach((e, f) -> f.turnOnTheArtefact(aHero));
    }

    void turnOffArtefacts(Hero aHero){
        equipment.forEach((e, f) -> f.turnOffTheArtefact(aHero));
    }


}
