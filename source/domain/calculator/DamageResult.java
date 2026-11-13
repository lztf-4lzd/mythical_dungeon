package source.domain.calculator;

public class DamageResult {
    private final int finalDamage;
    private final boolean criticalHit;
    private final double elementalMultiplier;
    private final String triggeredMechanicNote;

    public DamageResult(
            int finalDamage,
            boolean criticalHit,
            double elementalMultiplier,
            String triggeredMechanicNote) {
        this.finalDamage = finalDamage;
        this.criticalHit = criticalHit;
        this.elementalMultiplier = elementalMultiplier;
        this.triggeredMechanicNote = triggeredMechanicNote == null ? "" : triggeredMechanicNote;
    }

    public int getFinalDamage() {
        return finalDamage;
    }

    public boolean isCriticalHit() {
        return criticalHit;
    }

    public boolean isCrit() {
        return criticalHit;
    }

    public double getElementalMultiplier() {
        return elementalMultiplier;
    }

    public String getTriggeredMechanicNote() {
        return triggeredMechanicNote;
    }
}
