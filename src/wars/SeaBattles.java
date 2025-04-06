package wars;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            return "Not found";
        }
        
        if (!reserveFleet.contains(nme)) {
            return "Not available";
        }
        
        if (warChest < ship.getCommissionFee()) {
            return "Not enough money";
        }
        
        // Deduct commission fee from war chest
        warChest -= ship.getCommissionFee();
        
        // Update ships status
        reserveFleet.remove(nme);
        squadron.add(nme);
        ship.setState(ACTIVE);
        
        return "Ship commissioned";
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
        if (!isInSquadron(nme)) {
            return false;
        }
        
        Ship ship = allShips.get(nme);
        if (ship == null) {
            return false;
        }
        
        // Update ship status
        squadron.remove(nme);
        reserveFleet.add(nme);
        ship.setState(RESERVE);
        
        return true;
    }
    
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
        Ship ship = allShips.get(ref);
        if (ship != null && squadron.contains(ref) && RESTING.equals(ship.getState())) {
            ship.setState(ACTIVE);
        }
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
      public String fightEncounter(int encNo) {
        if (!encounters.containsKey(encNo)) {
            return "No such encounter";
        }
    
        Encounter encounter = encounters.get(encNo);
    
        // Find an active ship that can participate
        Ship chosenShip = null;
        for (String shipName : squadron) {
            Ship ship = allShips.get(shipName);
            if (ship != null && ACTIVE.equals(ship.getState()) && ship.getBattleSkill() >= encounter.getRequiredSkill()) {
                chosenShip = ship;
                break;
            }
        }
    
        StringBuilder result = new StringBuilder();
    
        if (chosenShip == null) {
            // No ship available to handle encounter
            warChest -= encounter.getPrizeMoney();
            result.append("Encounter lost as no ship available");
        } else {
            if (chosenShip.getBattleSkill() >= encounter.getRequiredSkill()) {
                // Win
                warChest += encounter.getPrizeMoney();
                chosenShip.setState(RESTING);
                result.append("Encounter won by ").append(chosenShip.getName())
                      .append(" (").append(chosenShip.getCaptain()).append(")");
            } else {
                // Lose and ship is sunk
                warChest -= encounter.getPrizeMoney();
                chosenShip.setState(SUNK);
                squadron.remove(chosenShip.getName());
                sunkShips.add(chosenShip.getName());
                result.append("Encounter lost on battle skill level and ").append(chosenShip.getName()).append(" sunk/lost");
            }
        }
    
        if (isDefeated()) {
            defeated = true;
            result.append("\nYou have been defeated");
        }
    
        result.append("\nWar Chest: ").append(warChest);
    
        return result.toString();
    }
    

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        Encounter encounter = encounters.get(num);
        if (encounter != null) {
            return encounter.toString();
        }
        
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
        // Create ships from Appendix A
        addShip("Victory", "Alan Aikin", 3, 300, 3, 30, 0, true, MANOWAR);
        addShip("Sophie", "Ben Baggins", 8, 400, 0, 0, 16, true, FRIGATE);
        addShip("Endeavour", "Col Cannon", 4, 275, 2, 20, 0, false, MANOWAR);
        addShip("Arrow", "Dan Dare", 5, 150, 0, 0, 0, true, SLOOP);
        addShip("Bellerophon", "Ed Evans", 8, 450, 3, 50, 0, false, MANOWAR);
        addShip("Surprise", "Fred Fox", 6, 300, 0, 0, 10, false, FRIGATE);
        addShip("Jupiter", "Gil Gamage", 7, 350, 0, 0, 20, false, FRIGATE);
        addShip("Paris", "Hal Henry", 4, 200, 0, 0, 0, false, SLOOP);
        addShip("Beast", "Ian Idle", 5, 400, 0, 0, 0, false, SLOOP);
        addShip("Athena", "John Jones", 6, 100, 0, 0, 0, true, SLOOP);
        
        // Place all ships in reserve fleet initially
        for (String shipName : allShips.keySet()) {
            reserveFleet.add(shipName);
        }
    }
    
    private void addShip(String name, String captain, int battleSkill, int commissionFee, 
                         int decks, int marines, int cannons, boolean hasDoctor, String shipType) {
        Ship newShip = new Ship(name, captain, battleSkill, commissionFee, 
                                decks, marines, cannons, hasDoctor, shipType);
        allShips.put(name, newShip);
    }
     
    private void setupEncounters()
    {
                // Create encounters from Appendix A
        encounters.put(1, new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300));
        encounters.put(2, new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120));
        encounters.put(3, new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150));
        encounters.put(4, new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200));
        encounters.put(5, new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90));
        encounters.put(6, new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45));
        encounters.put(7, new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130));
        encounters.put(8, new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100));
        encounters.put(9, new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200));
        encounters.put(10, new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250));
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
    public void readEncounters(String filename) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int encounterNo = Integer.parseInt(parts[0]);
                    EncounterType type = EncounterType.valueOf(parts[1].toUpperCase());
                    String location = parts[2];
                    int requiredSkill = Integer.parseInt(parts[3]);
                    int prizeMoney = Integer.parseInt(parts[4]);
    
                    Encounter encounter = new Encounter(encounterNo, type, location, requiredSkill, prizeMoney);
                    encounters.put(encounterNo, encounter);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing encounters: " + e.getMessage());
        }
    }
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            out.writeObject(this);
            System.out.println("Game saved successfully to " + fname);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public SeaBattles loadGame(String fname)
    {   // uses object serialisation 
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            SeaBattles game = (SeaBattles) in.readObject();
            System.out.println("Game loaded successfully from " + fname);
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
    
 
}


