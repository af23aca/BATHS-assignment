package wars;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amnafarhan
 */
public class Ship {
    
   private String name;
   private String captain;
   private int battleskill;
   private int commissionFee;
   private ShipState status;
   
   public Ship (String name, String captain, int battleskill, int commissionFee, String status){
       this.name = name;
       this.captain = captain;
       this.battleSkill = battleskill;
       this.comissionFee = comissionfee;
       this.status = ShipState.valueOf(status.toUpperCase());
   }
   
   
   //Acessors here 
   
   public String getShipName()
    {
        return name;
    }
   public String getCaptain()
    {
        return captain;
    }
   
   public int getbattleskill()
    {
        return battleskill;
    }
   
   public int getCommissionFee()
    {
        return commissionFee;
    }
   
   public String getStatus()
    {
        return status;
    }

    public void setState(ShipState state){
        this.state = state;
    }
   
    public abstract boolean canFight(String encounterType);
   
   //mutators here
   
   public String toString()
    {
        return "\nShip name: " + name +
                "\nCaptain: " + captain +
                "\nBattle Skill: " + battleskill +
                "\nCommission Fee: " + commissionFee +
                "\nShip Status: " + status;
    }
   
}
