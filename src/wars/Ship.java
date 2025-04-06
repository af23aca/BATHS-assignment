package wars;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amnafarhan
 */
public abstract class Ship {
    
   private String name;
   private String captain;
   private int battleskill;
   private int commissionFee;
   private ShipState status;
   
   public Ship (String name, String captain, int battleskill, int commissionfee, String status){
       this.name = name;
       this.captain = captain;
       this.battleskill = battleskill;
       this.commissionFee = commissionfee;
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
        return status.toString();
    }

    public void setState(ShipState state){
        this.status = state;
    }
   
    public abstract boolean canFight(String encounterType);
   
   //mutators here
   

   @Override
   public String toString()
    {
        return "\nShip name: " + name +
                "\nCaptain: " + captain +
                "\nBattle Skill: " + battleskill +
                "\nCommission Fee: " + commissionFee +
                "\nShip Status: " + status;
    }
   
}
