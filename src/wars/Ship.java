package wars;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amnafarhan
 */
class Ship {
    private String name;
    private String captain;
    private int battleSkill;
    private int commissionFee;
    private int decks;
    private int marines;
    private int cannons;
    private boolean hasDoctor;
    private String shipType;
    private String state;
    
    public Ship(String name, String captain, int battleSkill, int commissionFee, 
               int decks, int marines, int cannons, boolean hasDoctor, String shipType) {
        this.name = name;
        this.captain = captain;
        this.battleSkill = battleSkill;
        this.commissionFee = commissionFee;
        this.decks = decks;
        this.marines = marines;
        this.cannons = cannons;
        this.hasDoctor = hasDoctor;
        this.shipType = shipType;
        this.state = "Reserve"; // Default state
    }
    
    public String getName() {
        return name;
    }
    
    public String getCaptain() {
        return captain;
    }
    
    public int getBattleSkill() {
        return battleSkill;
    }
    
    public int getCommissionFee() {
        return commissionFee;
    }
    
    public String getShipType() {
        return shipType;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public boolean hasDoctor() {
        return hasDoctor;
    }
    
    @Override
    public String toString() {
        return name + " (" + shipType + "), Captain: " + captain + 
               ", Battle Skill: " + battleSkill + 
               ", State: " + state;
    }
}