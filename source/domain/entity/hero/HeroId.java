package source.domain.entity.hero;

public enum HeroId {
    WARRIOR("warrior", "Warrior"),
    MAGE("mage", "Mage"),
    PALADIN("paladin", "Paladin"),
    ARCHER("archer", "Archer");

    private final String id;
    private final String displayName;

    HeroId(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
