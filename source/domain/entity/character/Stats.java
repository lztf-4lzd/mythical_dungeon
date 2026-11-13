package source.domain.entity.character;

import source.domain.util.GameConstants;

public class Stats {
    private double maxHp;
    private double currentHp;
    private double maxStamina;
    private double currentStamina;
    private double physicalAttack;
    private double magicAttack;
    private double physicalDefense;
    private double magicDefense;
    private double speed;
    private double critChancePercent;
    private double baseResistancePercent;
    private double baseAccuracyPercent = GameConstants.ACCURACY_BASE;
    private double baseDodgePercent = GameConstants.DODGE_BASE;
    private double shieldAmount;
    private int currentExp = 0;

    public Stats() {}

    public void restoreFullHpAndStamina() {
        this.currentHp = this.maxHp;
        this.currentStamina = this.maxStamina;
    }

    public void restoreFullHealthAndStamina() {
        restoreFullHpAndStamina();
    }

    public int getCurrentSta() {
        return (int) Math.round(currentStamina);
    }

    public int getMaxSta() {
        return (int) Math.round(maxStamina);
    }

    public int getShield() {
        return (int) Math.round(shieldAmount);
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = Math.max(0, currentExp);
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }

    public double getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public double getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(double maxStamina) {
        this.maxStamina = maxStamina;
    }

    public double getCurrentStamina() {
        return currentStamina;
    }

    public void setCurrentStamina(double currentStamina) {
        this.currentStamina = currentStamina;
    }

    public double getPhysicalAttack() {
        return physicalAttack;
    }

    public void setPhysicalAttack(double physicalAttack) {
        this.physicalAttack = physicalAttack;
    }

    public double getMagicAttack() {
        return magicAttack;
    }

    public void setMagicAttack(double magicAttack) {
        this.magicAttack = magicAttack;
    }

    public double getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(double physicalDefense) {
        this.physicalDefense = physicalDefense;
    }

    public double getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(double magicDefense) {
        this.magicDefense = magicDefense;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCritChancePercent() {
        return critChancePercent;
    }

    public void setCritChancePercent(double critChancePercent) {
        this.critChancePercent = critChancePercent;
    }

    public double getBaseResistancePercent() {
        return baseResistancePercent;
    }

    public void setBaseResistancePercent(double baseResistancePercent) {
        this.baseResistancePercent = baseResistancePercent;
    }

    public double getBaseAccuracyPercent() {
        return baseAccuracyPercent;
    }

    public void setBaseAccuracyPercent(double baseAccuracyPercent) {
        this.baseAccuracyPercent = baseAccuracyPercent;
    }

    public double getBaseDodgePercent() {
        return baseDodgePercent;
    }

    public void setBaseDodgePercent(double baseDodgePercent) {
        this.baseDodgePercent = baseDodgePercent;
    }

    public double getShieldAmount() {
        return shieldAmount;
    }

    public void setShieldAmount(double shieldAmount) {
        this.shieldAmount = shieldAmount;
    }
}
