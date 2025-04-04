/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author amnafarhan
 */
public class Sloop extends Ship {
    boolean hasDoctor;
    
    public Sloop (String name, String captain, int battleskill, int commissionFee, String status,
                  boolean hasDoctor){
    super(name, captain, battleskill, commissionFee, status);
    hasDoctor = this.hasDoctor;
    
    }
    
    
    //accesors
    public boolean hasDoctor(){
        return hasDoctor;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Has Doctors? " + hasDoctor;
    }
    
}
