package flight_rings.flight_rings.config;

public class ConfigInstance {

	public float basicExhaustion;
	public float advancedExhaustion;
	public boolean basicRecipeUsesEnderEye;
	public double damageChancePerTick;
	public String note;
	public boolean pureRingUsesXP;
	public float pureRingXPAmount;
	public String note2;
	public boolean basicRingHasDurability;
	public boolean basicRingEnabled;
	
	public ConfigInstance() {
		basicExhaustion = 0.15F;
		advancedExhaustion = 0.01F;
		basicRingEnabled = true;
		basicRingHasDurability = true;
		basicRecipeUsesEnderEye = false;
		damageChancePerTick = 10.0;
		note = "^ Percentage chance per tick (20 ticks per second) to lose durability of ring ^";
		pureRingUsesXP = false;
		pureRingXPAmount = 0.001F;
		note2 = "^ With the default of 0.001, each XP level should last 50 seconds. Lower values will make each level last longer ^";


	}
	
}
