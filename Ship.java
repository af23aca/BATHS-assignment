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
   private String status;
   
   public Ship (String name, String captain, int battleskill, int commissionFee, String status){
       name = this.name;
       captain = this.captain;
       battleskill = this.battleskill;
       commissionFee = this.commissionFee;
       status = this.status;  
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
