package wars;
import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amnafarhan, barbod
 */
public class Encounter implements Serializable {
   private int encounterNo;
   private EncounterType type;
   private String location;
   private int requiredSkill;
   private int prizeMoney;

   public Encounter(int encounterNo, EncounterType type, String location, int requiredSkill, int prizeMoney) {
       this.encounterNo = encounterNo;
       this.type = type;
       this.location = location;
       this.requiredSkill = requiredSkill;
       this.prizeMoney = prizeMoney;
   }

   public int getEncounterNo() {
       return encounterNo;
   }

   public EncounterType getType() {
       return type;
   }

   public String getLocation() {
       return location;
   }

   public int getRequiredSkill() {
       return requiredSkill;
   }

   public int getPrizeMoney() {
       return prizeMoney;
   }

   @Override
   public String toString()
    {
        return "\nEncounter: " + encounterNo +
                "\ntype: " + type +
                "\nlocation: " + location +
                "\nskill: " + requiredSkill +
                "\nPrize: " + prizeMOney;
    }
}
