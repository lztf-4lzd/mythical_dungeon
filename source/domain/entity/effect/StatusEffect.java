package source.domain.entity.effect;

public class StatusEffect {
    private String name;
    private EffectType type;
    private int duration;
    private int value;

    public StatusEffect(
        String name,
        EffectType type,
        int duration,
        int value) {
            this.name = name;
            this.type = type;
            this.duration = duration;
            this.value = value;
    }

    //GETTERS AND SETTERS
    public StatusEffect copy() {
        return new StatusEffect(name, type, duration, value);
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void decrementDuration() {
        this.duration--;
    }
    public String getName() {
        return name;
    }
    public EffectType getType() {
        return type;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
