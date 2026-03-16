package tired9494.flight_rings.items;


import net.minecraft.world.item.Item;

//the "template" for other flight rings
public abstract class AbstractFlightRing extends Item {
    public AbstractFlightRing(Properties properties) {
        super(properties);
    }
    public abstract int getEnchantmentValue();
}
