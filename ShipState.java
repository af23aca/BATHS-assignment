package wars;

import java.io.*;

/**
 * Enumeration class ShipState - This enum handles all possible states a ship can be in
 * 
 * The four states are:
 * - RESERVE: When the ship is in the reserve fleet, not yet commissioned.
 * - ACTIVE: When the ship is part of the squadron and available for encounters.
 * - RESTING: When the ship has fought a battle and is temporarily inactive.
 * - SUNK: When the ship has been destroyed and cannot be used again.
 * 
 * This enum is Serializable, allowing it to be saved and loaded along with game state.
 * 
 * @author ZeynepsuAtabay
 * @version 12/02/2025
 */
public enum ShipState implements Serializable {

    // Enum values with descriptions
    RESERVE(" In reserve fleet"), 
    ACTIVE(" Active in squadron"), 
    RESTING(" Resting"), 
    SUNK(" Sunk");

    private final String state;

    // Constructor to set the description of each state
    private ShipState(String st) {
        state = st;
    }

    // Returns the description of the state
    @Override
    public String toString() {
        return state;
    }

    // Checks if the ship can be commissioned (only allowed if it's in RESERVE)
    public boolean canBeCommissioned() {
        return this == RESERVE;
    }

    // Checks if the ship is ready to fight (only allowed if it's ACTIVE)
    public boolean canFight() {
        return this == ACTIVE;
    }

    // Checks if the ship can be decommissioned (ACTIVE or RESTING only)
    public boolean canBeDecommissioned() {
        return this == ACTIVE || this == RESTING;
    }
}
