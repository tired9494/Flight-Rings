package tired9494.flight_rings.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Unbreakable;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;
import tired9494.flight_rings.items.AdvancedFlightRing;
import tired9494.flight_rings.items.BasicFlightRing;

import java.util.function.Supplier;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(FlightRings.MOD_ID, Registries.ITEM);

    public static Supplier<Item> BASIC_FLIGHT_RING;
    public static Supplier<Item> ADVANCED_FLIGHT_RING;

    public static void initItems() {
        Item.Properties basicFlightRingProperties = new Item.Properties()
                .stacksTo(1)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .rarity(Rarity.UNCOMMON);
        if (ModConfig.basicRingDurability == 0) {
            basicFlightRingProperties.component(DataComponents.UNBREAKABLE, new Unbreakable(true));
        }
        else {
            basicFlightRingProperties.durability(ModConfig.basicRingDurability);
        }
        BASIC_FLIGHT_RING = registerItem("basic_ring", () ->
                new BasicFlightRing(basicFlightRingProperties, ModConfig.basicFlightPenalty)
        );

        Item.Properties advancedFlightRingProperties = new Item.Properties()
                .stacksTo(1)
                .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .rarity(Rarity.RARE);
        if (ModConfig.advancedRingDurability == 0) {
            advancedFlightRingProperties.component(DataComponents.UNBREAKABLE, new Unbreakable(true));
        }
        else {
            advancedFlightRingProperties.durability(ModConfig.advancedRingDurability);
        }
        ADVANCED_FLIGHT_RING = registerItem("advanced_ring", () ->
                new AdvancedFlightRing(advancedFlightRingProperties, ModConfig.advancedFlightPenalty)
        );

        ITEMS.register();
    }


    public static RegistrySupplier<Item> registerItem(String name, Supplier<Item> item) {
        return ITEMS.register(FlightRings.id(name), item);
    }

}
