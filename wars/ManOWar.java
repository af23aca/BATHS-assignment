package wars;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amnafarhan, barbod
 */
public abstract class ManOWar extends Ship {
    private int numberOfDecks;
    private int numberOfMarines;
    
    public ManOWar (String name, String captain, int battleskill, int commissionFee, String status, int numberOfDecks, int numberofMarines){
    super(name, captain, battleskill, commissionFee, status);
    numberOfDecks = this.numberOfDecks;
    this.numberOfMarines = numberOfMarines;
    
    }
    
    
// accessors
public int getNumDecks(){
    return numberOfDecks;
}
public int numMarines(){
    return numberOfMarines;
}

public boolean canFight(EncounterType type){
    return type == EncounterType.BATTLE || type == EncounterType.BLOCKADE;
}


    
 @Override
    public String toString() {
        return super.toString() + 
                "\nType: MAN-0-WAR" +
                "\nNumber of decks: " + numberOfDecks +
                "\nNumber of Marines: " + numberOfMarines;
    }
      
}
