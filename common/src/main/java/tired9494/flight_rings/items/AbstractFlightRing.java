package tired9494.flight_rings.items;


import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import tired9494.flight_rings.ModConfig;

//the "template" for other flight rings
public abstract class AbstractFlightRing extends Item {
    private PenaltyEffect penaltyEffect;
    public AbstractFlightRing(Properties properties, ModConfig.FlightPenaltyType flightPenaltyType) {
        super(properties);
        switch(flightPenaltyType) {
            case XP -> penaltyEffect = new ExperiencePenaltyEffect();
            case HUNGER -> penaltyEffect = new HungerPenaltyEffect();
            default -> penaltyEffect = new NoPenaltyEffect();
        }
    }
    public abstract int getEnchantmentValue();

    private abstract static class PenaltyEffect
    {
        public abstract boolean canApply(ServerPlayer serverPlayer, int modifier);
        public abstract void applyPenaltyTick(ServerPlayer serverPlayer, int modifier);
    }
    private static class HungerPenaltyEffect extends PenaltyEffect {
        public boolean canApply(ServerPlayer serverPlayer, int modifier) {
            return true;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer, int modifier) {

        }
    }
    private static class ExperiencePenaltyEffect extends PenaltyEffect {
        public boolean canApply(ServerPlayer serverPlayer, int modifier) {
            return true;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer, int modifier) {

        }
    }
    private static class NoPenaltyEffect extends PenaltyEffect {
        public boolean canApply(ServerPlayer serverPlayer, int modifier) {
            return true;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer, int modifier) {

        }
    }
}
