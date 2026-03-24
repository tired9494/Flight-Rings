package tired9494.flight_rings;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tired9494.flight_rings.registry.ModEffects;
import tired9494.flight_rings.registry.ModItems;

public final class FlightRings {
    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "flight_rings";

    public static void init() {
        MidnightConfig.init(MOD_ID, ModConfig.class);
        ModItems.initItems();
        ModEffects.initEffects();
    }

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(FlightRings.MOD_ID, id);
    }
}
