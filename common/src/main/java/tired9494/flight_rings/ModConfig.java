package tired9494.flight_rings;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    public static final String BASIC_FLIGHT_RING = "basic_flight_ring";
    public static final String ADVANCED_FLIGHT_RING = "advanced_flight_ring";

    @Entry(category = BASIC_FLIGHT_RING, min=1) public static int basicRingDurability = 512;
    @Entry(category = BASIC_FLIGHT_RING) public static FlightPenaltyType basicFlightPenalty = FlightPenaltyType.HUNGER;
    @Condition(requiredOption = "basicFlightPenalty", requiredValue = "HUNGER")
    @Entry(category = BASIC_FLIGHT_RING, min=0) public static float basicHungerPenalty = 0.1f;
    @Condition(requiredOption = "basicFlightPenalty", requiredValue = "XP")
    @Entry(category = BASIC_FLIGHT_RING, min=0) public static float basicExperiencePenalty = 0.1f;

    @Entry(category = ADVANCED_FLIGHT_RING, min=1) public static int advancedRingDurability = 2048;
    @Entry(category = ADVANCED_FLIGHT_RING) public static FlightPenaltyType advancedFlightPenalty = FlightPenaltyType.XP;
    @Condition(requiredOption = "advancedFlightPenalty", requiredValue = "HUNGER")
    @Entry(category = ADVANCED_FLIGHT_RING, min=0) public static float advancedHungerPenalty = 0.1f;
    @Condition(requiredOption = "advancedFlightPenalty", requiredValue = "XP")
    @Entry(category = ADVANCED_FLIGHT_RING, min=0) public static float advancedExperiencePenalty = 0.1f;


    public enum FlightPenaltyType {
        XP, HUNGER, NONE
    }
}