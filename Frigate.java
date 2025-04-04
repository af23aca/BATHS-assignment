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
    
    public Frigate (String name, String captain, int battleskill, int commissionFee, String status, 
                    int numberOfCannons, boolean hasPinnace){
    super(name, captain, battleskill, commissionFee, status);
    
    numberOfCannons = this.numberOfCannons;
    hasPinnace = this.hasPinnace;
    
    }
    
    
    //accessors 
    public int numCannons(){
        return numberOfCannons;
    }
    
    public boolean pinnace(){
        return hasPinnace;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Number of cannons: " + numberOfCannons
                + "\nHas Pinnace? " + hasPinnace;
        
    }
      
}

