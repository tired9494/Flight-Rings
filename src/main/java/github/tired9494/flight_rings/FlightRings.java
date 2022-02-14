package github.tired9494.flight_rings;

import github.tired9494.flight_rings.compat.TrinketsCompat;
import github.tired9494.flight_rings.config.FlightRingsConfig;
import github.tired9494.flight_rings.rings.AdvancedRing;
import github.tired9494.flight_rings.rings.BasicRing;
import me.shedaniel.autoconfig.ConfigHolder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FlightRings implements ModInitializer {

    public static final ConfigHolder<FlightRingsConfig> CONFIG = FlightRingsConfig.init();
    public static FlightRingsConfig getConfig()
    {
        return CONFIG.getConfig();
    }
    public static final BasicRing BASIC_RING = new BasicRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(getConfig().basicRingOptions.durabilityValue));
    public static final AdvancedRing ADVANCED_RING = new AdvancedRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(getConfig().pureRingOptions.durabilityValue));
    private static final Identifier DUNGEON_CHEST_ID = LootTables.SIMPLE_DUNGEON_CHEST;
    private static final Identifier MINESHAFT_CHEST_ID = LootTables.ABANDONED_MINESHAFT_CHEST;
    private static final Identifier DESERT_CHEST_ID = LootTables.DESERT_PYRAMID_CHEST;
    private static final Identifier END_CHEST_ID = LootTables.END_CITY_TREASURE_CHEST;
    private static final Identifier BASTION_CHEST_ID = LootTables.BASTION_OTHER_CHEST;
    private static final Identifier BONUS_CHEST_ID = LootTables.SPAWN_BONUS_CHEST;



    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("trinkets") && getConfig().misc.trinkets) {
            TrinketsCompat.init();
        }
        String basicIdentifier = getConfig().misc.trinkets ? "t_basic_ring" : "basic_ring";
        String advancedIdentifier = getConfig().misc.trinkets ? "t_advanced_ring" : "advanced_ring";
        if (getConfig().misc.treasure) {
            basicIdentifier += "_treasure";
            advancedIdentifier += "_treasure";
        }
        else if (getConfig().basicRingOptions.eye) {
            basicIdentifier += "_alt";
        }

        if (getConfig().misc.chestLoot) {
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if (DUNGEON_CHEST_ID.equals(id)) {
                    LootPool pool = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
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
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
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
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
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
                            .withEntry(ItemEntry.builder(ADVANCED_RING).build())
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
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
                            .withEntry(ItemEntry.builder(ADVANCED_RING).build())
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
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
                else if (BONUS_CHEST_ID.equals(id)) {
                    LootPool poolBuilder = FabricLootPoolBuilder.builder()
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
                            .withEntry(ItemEntry.builder(BASIC_RING).build())
                            .withEntry(ItemEntry.builder(Items.AIR).build())
                            .build();
                    supplier.withPool(poolBuilder);
                }
            });
        }
        Registry.register(Registry.ITEM, new Identifier("flight_rings", basicIdentifier), BASIC_RING);
        Registry.register(Registry.ITEM, new Identifier("flight_rings", advancedIdentifier), ADVANCED_RING);
    }
}
