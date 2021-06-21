package github.tired9494.flight_rings;

import github.tired9494.flight_rings.config.FlightRingsConfig;
import github.tired9494.flight_rings.rings.AdvancedRing;
import github.tired9494.flight_rings.rings.BasicRing;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FlightRings implements ModInitializer {

    public static final BasicRing BASIC_RING = new BasicRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(180));
    public static final AdvancedRing ADVANCED_RING = new AdvancedRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(9000));
    private static final Identifier DUNGEON_CHEST_ID = LootTables.SIMPLE_DUNGEON_CHEST;
    private static final Identifier MINESHAFT_CHEST_ID = LootTables.ABANDONED_MINESHAFT_CHEST;
    private static final Identifier DESERT_CHEST_ID = LootTables.DESERT_PYRAMID_CHEST;
    private static final Identifier END_CHEST_ID = LootTables.END_CITY_TREASURE_CHEST;
    private static final Identifier BASTION_CHEST_ID = LootTables.BASTION_OTHER_CHEST;

    public static final ConfigHolder<FlightRingsConfig> CONFIG = FlightRingsConfig.init();

    @Override
    public void onInitialize() {
        if (!getConfig().misc.treasure) {
            if (!getConfig().basicOptions.eye & getConfig().basicOptions.enabled) {
                Registry.register(Registry.ITEM, new Identifier("flight_rings", "basic_ring"), BASIC_RING);
            } else if (getConfig().basicOptions.enabled) {
                Registry.register(Registry.ITEM, new Identifier("flight_rings", "basic_ring_alt"), BASIC_RING);
            }
            Registry.register(Registry.ITEM, new Identifier("flight_rings", "advanced_ring"), ADVANCED_RING);
        }
        else {
            Registry.register(Registry.ITEM, new Identifier("flight_rings", "advanced_ring_treasure"), ADVANCED_RING);
            Registry.register(Registry.ITEM, new Identifier("flight_rings", "basic_ring_treasure"), BASIC_RING);
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (DUNGEON_CHEST_ID.equals(id)) {
                    LootPool pool = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(pool);
                }

                else if (MINESHAFT_CHEST_ID.equals(id)) {
                    LootPool poolBuilder = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(poolBuilder);
                }
                else if (DESERT_CHEST_ID.equals(id)) {
                    LootPool poolBuilder = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(poolBuilder);
                }
                else if (END_CHEST_ID.equals(id)) {
                    LootPool poolBuilder = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(FlightRings.ADVANCED_RING).build())
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(poolBuilder);
                }
                else if (BASTION_CHEST_ID.equals(id)) {
                    LootPool poolBuilder = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(FlightRings.ADVANCED_RING).build())
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(FlightRings.BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(poolBuilder);
                }
            });
        }
    }
    public static FlightRingsConfig getConfig()
    {
        return CONFIG.getConfig();
    }
}
