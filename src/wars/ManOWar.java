package wars;

public abstract class ManOWar extends Ship {
    private int numberOfDecks;
    private int numberOfMarines;

    public ManOWar(String name, String captain, int battleskill, int commissionFee, String status, int numberOfDecks, int numberOfMarines, int cannons, boolean hasDoctor) {
        super(name, captain, battleskill, commissionFee, numberOfDecks, numberOfMarines, cannons, hasDoctor, "MAN-0-WAR");
        this.numberOfDecks = numberOfDecks;
        this.numberOfMarines = numberOfMarines;
    }

    // Accessors
    public int getNumDecks() {
        return numberOfDecks;
    }

    public int numMarines() {
        return numberOfMarines;
    }

    public boolean canFight(EncounterType type) {
        return type == EncounterType.BATTLE || type == EncounterType.BLOCKADE;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nType: MAN-0-WAR" +
                "\nNumber of decks: " + numberOfDecks +
                "\nNumber of Marines: " + numberOfMarines;
    }
}