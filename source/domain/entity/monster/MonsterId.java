package source.domain.entity.monster;

public enum MonsterId {

    SOLDIER("soldier", "Soldier", "Outpost Soldier", "Outpost Soldier [MINI-BOSS]"),
    ORC("orc", "Orc", "Orc Raider", "Orc Raider [MINI-BOSS]"),
    ELITE_ORC("elite_orc", "Elite Orc", "Elite Orc Berserker", "Elite Orc Berserker [MINI-BOSS]"),
    ARMORED_ORC("armored_orc", "Armored Orc", "Armored Orc Bulwark", "Armored Orc Bulwark [MINI-BOSS]"),
    SKELETON("skeleton", "Skeleton", "Restless Skeleton", "Restless Skeleton [MINI-BOSS]"),
    ARMORED_SKELETON("armored_skeleton", "Armored Skeleton", "Armored Halberdier", "Armored Halberdier [MINI-BOSS]"),
    GREATSWORD_SKELETON("greatsword_skeleton", "Greatsword Skeleton", "Dreadblade Skeleton", "Dreadblade Skeleton [MINI-BOSS]"),
    SKELETON_ARCHER("skeleton_archer", "Skeleton Archer", "Skeleton Bonebow", "Skeleton Bonebow [MINI-BOSS]"),
    SLIME("slime", "Slime", "Acid Slime", "Acid Slime [MINI-BOSS]"),
    BAT("bat", "Bat", "Cave Bat", "Cave Bat [MINI-BOSS]"),
    ORC_RIDER("orc_rider", "Orc Rider", "Orc Boar Rider", "Orc Boar Rider [MINI-BOSS]"),
    WEREBEAR("werebear", "Werebear", "Grizzled Werebear", "Grizzled Werebear [MINI-BOSS]"),
    WEREWOLF("werewolf", "Werewolf", "Silverclaw Werewolf", "Silverclaw Werewolf [MINI-BOSS]"),
    BLACK_KNIGHT_A("black_knight_a", "Black Knight A", "Ashen Spike Knight", "Ashen Spike Knight [MINI-BOSS]"),
    BLACK_KNIGHT_B("black_knight_b", "Black Knight B", "Bastion Gun-Knight", "Bastion Gun-Knight [MINI-BOSS]"),
    BLACK_KNIGHT_C("black_knight_c", "Black Knight C", "Dread Cavalier Lord", "Dread Cavalier Lord [BOSS]"),
    BLOOD_MONSTER_A("blood_monster_a", "Blood Monster A", "Crimson Fleshspawn", "Crimson Fleshspawn [MINI-BOSS]"),
    BLOOD_MONSTER_B("blood_monster_b", "Blood Monster B", "Sanguine Eye-Maw", "Sanguine Eye-Maw [MINI-BOSS]"),
    DEMON_A("demon_a", "Demon A", "Infernal Imp", "Infernal Imp [MINI-BOSS]"),
    DEMON_B("demon_b", "Demon B", "Hellbow Fiend", "Hellbow Fiend [MINI-BOSS]"),
    DEMON_C("demon_c", "Demon C", "Winged Trident Fiend", "Winged Trident Fiend [MINI-BOSS]"),
    DEMON_D("demon_d", "Demon D", "Abyssal Maul Juggernaut", "Abyssal Maul Juggernaut [MINI-BOSS]"),
    DEMON_E("demon_e", "Demon E", "Archfiend Blademaster", "Archfiend Blademaster [MINI-BOSS]"),
    DEMONESS_A("demoness_a", "Demoness A", "Crimson Reaper Demoness", "Crimson Reaper Demoness [MINI-BOSS]"),
    DEMONESS_B("demoness_b", "Demoness B", "Lash Succubus", "Lash Succubus [MINI-BOSS]"),
    EYEBALL_MONSTER("eyeball_monster", "Eyeball Monster", "Ocular Watcher", "Ocular Watcher [MINI-BOSS]"),
    FLAME_GOLEM("flame_golem", "Flame Golem", "Obsidian Flame Golem", "Obsidian Flame Golem [BOSS]"),
    GHOSTFIRE("ghostfire", "Ghostfire", "Ghostfire Skull", "Ghostfire Skull [MINI-BOSS]"),
    HELLBAT("hellbat", "Hellbat", "Blazing Hellbat", "Blazing Hellbat [MINI-BOSS]"),
    HELLHOUND("hellhound", "Hellhound", "Infernal Hellhound", "Infernal Hellhound [MINI-BOSS]"),
    LAVA_SLIME("lava_slime", "Lava Slime", "Magma Crust Slime", "Magma Crust Slime [MINI-BOSS]"),
    MINOTAUR("minotaur", "Minotaur", "Labyrinth Minotaur", "Labyrinth Minotaur [MINI-BOSS]"),
    WARLOCK("warlock", "Warlock", "Hellfire Warlock", "Hellfire Warlock [MINI-BOSS]");
    
    private final String id;
    private final String assetName;
    private final String displayName;
    private final String bossDisplayName;

    MonsterId(String id, String assetName, String displayName, String bossDisplayName) {
        this.id = id;
        this.assetName = assetName;
        this.displayName = displayName;
        this.bossDisplayName = bossDisplayName;
    }
    
    // GETTERS
    public String getId() {
        return id;
    }
    public String getAssetName() {
        return assetName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getBossDisplayName() {
        return bossDisplayName;
    }
}
