package wars;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS 
{
    // may have one HashMap and select on stat
    // Ship states
    private static final String ACTIVE = "Active";
    private static final String RESTING = "Resting";
    private static final String SUNK = "Sunk";
    private static final String RESERVE = "Reserve";
    
    // Ship types
    private static final String MANOWAR = "ManOWar";
    private static final String FRIGATE = "Frigate";
    private static final String SLOOP = "Sloop";
    
    // Encounter types
    private static final String BATTLE = "Battle";
    private static final String SKIRMISH = "Skirmish";
    private static final String BLOCKADE = "Blockade";
    
    // Collections to store game data
    private HashMap<String, Ship> allShips; // All ships indexed by name
    private HashMap<Integer, Encounter> encounters; // All encounters indexed by number
    private ArrayList<String> squadron; // Names of ships in admiral's squadron
    private ArrayList<String> reserveFleet; // Names of ships in reserve
    private ArrayList<String> sunkShips; // Names of ships that are sunk
    
    // Game state
    private String admiral;
    private double warChest;
    private boolean defeated;
    private static final double INITIAL_WAR_CHEST = 1000.0;

//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public SeaBattles(String adm)
    {
        this.admiral = adm;
        this.warChest = INITIAL_WAR_CHEST;
        this.defeated = false;
        
        // Initialize collections
        this.allShips = new HashMap<>();
        this.encounters = new HashMap<>();
        this.squadron = new ArrayList<>();
        this.reserveFleet = new ArrayList<>();
        this.sunkShips = new ArrayList<>();
        
        setupShips();
        setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admiral the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
        this.admiral = admir;
        this.warChest = INITIAL_WAR_CHEST;
        this.defeated = false;
        
        // Initialize collections
        this.allShips = new HashMap<>();
        this.encounters = new HashMap<>();
        this.squadron = new ArrayList<>();
        this.reserveFleet = new ArrayList<>();
        this.sunkShips = new ArrayList<>();
        
        setupShips();
        // setupEncounters();
        // uncomment for testing Task 
        readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("\nAdmiral: ").append(admiral);
        result.append("\nWar Chest: ").append(warChest);
        result.append("\nDefeated: ").append(defeated ? "Yes" : "No");
        
        result.append("\nSquadron: ");
        if (squadron.isEmpty()) {
            result.append("No ships");
        } else {
            for (String shipName : squadron) {
                result.append("\n  ").append(allShips.get(shipName));
            }
        }
        
        result.append("\nReserve Fleet: ");
        if (reserveFleet.isEmpty()) {
            result.append("No ships");
        } else {
            for (String shipName : reserveFleet) {
                result.append("\n  ").append(allShips.get(shipName));
            }
        }
        
        return result.toString();
    }
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        if (warChest > 0) {
            return false;
        }
        
        // Check if any ships in squadron can be decommissioned
        for (String shipName : squadron) {
            Ship ship = allShips.get(shipName);
            if (ship != null) {
                return false; // Found a ship that can be decommissioned
            }
        }
        
        return true; // No money and no ships to decommission
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return 0;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
        if (reserveFleet.isEmpty()) {
            return "No ships";
        }
        
        StringBuilder result = new StringBuilder();
        for (String shipName : reserveFleet) {
            Ship ship = allShips.get(shipName);
            result.append("\n").append(ship);
        }
        
        return result.toString();
    }
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
        if (squadron.isEmpty()) {
            return "No ships commissioned";
        }
        
        StringBuilder result = new StringBuilder();
        for (String shipName : squadron) {
            Ship ship = allShips.get(shipName);
            result.append("\n").append(ship);
        }
        
        return result.toString();
    }
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
        if (sunkShips.isEmpty()) {
            return "No ships sunk yet";
        }
        
        StringBuilder result = new StringBuilder();
        for (String shipName : sunkShips) {
            Ship ship = allShips.get(shipName);
            result.append("\n").append(ship);
        }
        
        return result.toString();
    }
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        if (allShips.isEmpty()) {
            return "No ships";
        }

        StringBuilder result = new StringBuilder();
        for (Ship ship : allShips.values()) {
            result.append("\n").append(ship);
        }
        
        return result.toString();
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
        Ship ship = allShips.get(nme);
        if (ship != null) {
            return ship.toString();
        }
        
        
        return "\nNo such ship";
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be comissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        Ship ship = allShips.get(nme);
        if (ship == null) {
            return "Ship not found";
        }


        return "ship commissioned";
    }
        
    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        return squadron.contains(nme);
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
        return false;
    }
    
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {

    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
        return encounters.containsKey(num);
     }
     
     
    /** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter. The results of fighting an encounter will be 
      * one of the following: Encounter won by...(ship reference and name) 
      *  add prize money to War Chest and ship's state is set to RESTING,  Encounter 
      * lost as no ship available  deduct prize money from the War Chest,Encounter 
      * lost on battle skill level and (ship name) sunk/lost" - deduct prize money from 
      * War Chest and ship state set to sunk. If an encounter is lost and admiral 
      * is completely defeated, add �You have been defeated  to the output.
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
       
            
        return "Not done";
    }

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        
        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        if (encounters.isEmpty()) {
            return "No encounters";
        }
        
        StringBuilder result = new StringBuilder();
        for (Encounter encounter : encounters.values()) {
            result.append("\n").append(encounter);
        }
        
        return result.toString();
    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
       

     }
     
    private void setupEncounters()
    {
  
    }
        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param fileName name of the file to be read
     */
    public void readEncounters(String filename)
    { 
      
        
        
    }   
 
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
           
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation 
       
        return null;
    } 
    
 
}
