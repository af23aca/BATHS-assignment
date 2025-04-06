/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

/**
 *
 * @author amnafarhan
 */
public abstract class Sloop extends Ship {
    private boolean hasDoctor;
    
    public Sloop(String name, String captain, int battleskill, int commissionFee, String status, boolean hasDoctor) {
        super(name, captain, battleskill, commissionFee, 1, 10, 2, hasDoctor, "Sloop");
        this.hasDoctor = hasDoctor;
    }
    
    
    //accesors
    public boolean hasDoctor(){
        return hasDoctor;
    }

    public boolean canFight(EncounterType type){
        return type == EncounterType.BATTLE || type == EncounterType.SKIRMISH;
    }
    
    @Override
    public String toString() {
        return super.toString() + "Has Doctors? " + hasDoctor;
    }
    
}
