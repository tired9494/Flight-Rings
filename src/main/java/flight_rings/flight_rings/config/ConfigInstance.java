package flight_rings.flight_rings.config;

public class ConfigInstance {

	public int basicHungerLevel;
	public boolean basicRecipeUsesEnderEye;
	public double damageChancePerTick;
	public String note;
	public boolean pureRingUsesXP;
	public float pureRingXPAmount;
	public String note2;
	public boolean basicRingHasDurability;
	
	public ConfigInstance() {
		basicHungerLevel = 70;
		basicRecipeUsesEnderEye = false;
		damageChancePerTick = 8.3;
		note = "^ Percentage chance per tick (20 ticks per second) to lose durability of ring ^";
		pureRingUsesXP = false;
		pureRingXPAmount = 0.001F;
		note2 = "^ With the default of 0.001, each XP level should last 50 seconds. Lower values will make each level last longer ^";
		basicRingHasDurability = false;
	}
	
}
