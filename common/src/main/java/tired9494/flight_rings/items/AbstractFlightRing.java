package tired9494.flight_rings.items;


import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;

//the "template" for other flight rings
public abstract class AbstractFlightRing extends Item {
    public PenaltyEffect penaltyEffect;
    public Holder<MobEffect> flightEffect;
    public AbstractFlightRing(Properties properties, ModConfig.FlightPenaltyType flightPenaltyType) {
        super(properties);
        flightEffect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(BuiltInRegistries.MOB_EFFECT.get(FlightRings.id("flight")));
    }
    public abstract int getEnchantmentValue();

    public void regetFlightEffectHolder() {
        flightEffect = BuiltInRegistries.MOB_EFFECT.wrapAsHolder(BuiltInRegistries.MOB_EFFECT.get(FlightRings.id("flight")));
    }

    public abstract static class PenaltyEffect
    {
        public abstract boolean canApply(ServerPlayer serverPlayer);
        public abstract void applyPenaltyTick(ServerPlayer serverPlayer);
    }

    public static class NoPenaltyEffect extends PenaltyEffect {
        public boolean canApply(ServerPlayer serverPlayer) {
            return true;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer) {

        }
    }
}
