package tired9494.flight_rings;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry() public static FlightPenaltyType flightPenaltyType = FlightPenaltyType.HUNGER;
    public enum FlightPenaltyType {                               // Enums allow the user to cycle through predefined options
        XP, HUNGER
    }
}
