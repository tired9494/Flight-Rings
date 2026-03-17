package tired9494.flight_rings.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;
import tired9494.flight_rings.items.BasicFlightRing;

import java.util.function.Supplier;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(FlightRings.MOD_ID, Registries.ITEM);

    public static Supplier<Item> BASIC_FLIGHT_RING;

    public static void initItems() {
        BASIC_FLIGHT_RING = registerItem("basic_ring", () ->
                new BasicFlightRing(new Item.Properties()
                        .durability(2048)
                        .stacksTo(1)
                        .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES),
                        ModConfig.basicFlightPenalty
                )
        );

        ITEMS.register();
    }


    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(FlightRings.id(name), item);
    }

}
