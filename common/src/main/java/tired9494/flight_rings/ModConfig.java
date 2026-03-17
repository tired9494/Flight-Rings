package tired9494.flight_rings;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry() public static FlightPenaltyType basicFlightPenalty = FlightPenaltyType.HUNGER;
    @Entry() public static FlightPenaltyType advancedFlightPenalty = FlightPenaltyType.XP;
    public enum FlightPenaltyType {
        XP, HUNGER
    }
}
