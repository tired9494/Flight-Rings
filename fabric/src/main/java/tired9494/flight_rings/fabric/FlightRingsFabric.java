package tired9494.flight_rings.fabric;

import tired9494.flight_rings.FlightRings;
import net.fabricmc.api.ModInitializer;

public final class FlightRingsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        FlightRings.init();
    }
}
