package source.domain.entity.character;

public class Attributes {
    private int strength;
    private int dexterity;
    private int intelligence;
    private int mind;
    private int vitality;
    private int endurance;

    public Attributes(
            int strength,
            int dexterity,
            int intelligence,
            int mind,
            int vitality,
            int endurance) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.mind = mind;
        this.vitality = vitality;
        this.endurance = endurance;
        }
    
    //GETTERS
    public int getStrength() {
        return strength;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getMind() {
        return mind;
    }
    public int getVitality() {
        return vitality;
    }
    public int getEndurance() {
        return endurance;
    }
    //SETTERS
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setMind(int mind) {
        this.mind = mind;
    }
    public void setVitality(int vitality) {
        this.vitality = vitality;
    }
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
}
