package flight_rings.flight_rings;

import flight_rings.flight_rings.config.ModConfig;
import flight_rings.flight_rings.rings.AdvancedRing;
import flight_rings.flight_rings.rings.BasicRing;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FlightRings implements ModInitializer {

    public static final BasicRing BASIC_RING = new BasicRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(1300));
    public static final BasicRing BASIC_RING_ALT = new BasicRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(1300));
    public static final AdvancedRing ADVANCED_RING = new AdvancedRing(new Item.Settings().group(ItemGroup.TRANSPORTATION).maxCount(1).maxDamage(3000));

    @Override
    public void onInitialize() {
        ModConfig.initialise();
        if (!ModConfig.INSTANCE.basicRecipeUsesEnderEye) {
            Registry.register(Registry.ITEM, new Identifier("flight_rings", "basic_ring"), BASIC_RING);
        }
        else {
            Registry.register(Registry.ITEM, new Identifier("flight_rings", "basic_ring_alt"), BASIC_RING_ALT);
        }
        Registry.register(Registry.ITEM, new Identifier("flight_rings", "advanced_ring"), ADVANCED_RING);
    }
}
