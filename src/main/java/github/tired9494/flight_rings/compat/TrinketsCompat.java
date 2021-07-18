package github.tired9494.flight_rings.compat;

import dev.emi.trinkets.api.TrinketsApi;
import github.tired9494.flight_rings.FlightRings;
import github.tired9494.flight_rings.rings.TrinketARing;
import github.tired9494.flight_rings.rings.TrinketBRing;

public class TrinketsCompat {
    public static void init() {
        TrinketsApi.registerTrinket(FlightRings.BASIC_RING, new TrinketBRing());
        TrinketsApi.registerTrinket(FlightRings.ADVANCED_RING, new TrinketARing());
    }
}
