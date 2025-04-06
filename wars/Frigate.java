/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author amnafarhan
 */
public class Frigate extends Ship {
    int numberOfCannons;
    boolean hasPinnace;
    
    public Frigate (String name, String captain, int battleskill, int commissionFee, String status, int numberOfCannons, boolean hasPinnace){
    super(name, captain, battleskill, commissionFee, status);
    this.numberOfCannons = numberOfCannons;
    this.hasPinnace = hasPinnace;
    
    }
    
    
    //accessors 
    public int getNumberOfCannons(){
        return numberOfCannons;
    }
    
    public boolean hasPinnace(){
        return hasPinnace;
    }

    @Override
    public boolean canFight(EncounterType type){
        return type == EncounterType.BATTLE || type == EncounterType.SKIRMISH || (type == EncounterType.BLOCKADE && hasPinnace);
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nType: Frigate" +
                "\nNumber of cannons: " + numberOfCannons + "\nHas Pinnace? " + hasPinnace;
        
    }
      
}

