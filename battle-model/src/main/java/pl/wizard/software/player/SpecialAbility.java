package pl.wizard.software.player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SpecialAbility {


    public static final String SPEED = "SPEED";
    public static final String MORE_COUNTER_ATTACKS = "MORE_COUNTER_ATTACKS";
    public static final String MAGICAL_SKILL = "MAGICAL_SKILL";

    public String ability;
    PropertyChangeSupport listenersSupport;



    SpecialAbility(String aAbility){
        ability = aAbility;
        listenersSupport = new PropertyChangeSupport(this);

    }



    public void addPropertyChangeListener(String aPropertyName, PropertyChangeListener aListener) {
        listenersSupport.addPropertyChangeListener(aPropertyName, aListener);
    }


    public void UseSpecialAbility(){
        listenersSupport.firePropertyChange(ability, null, null);
    }
//SPEED,

}
