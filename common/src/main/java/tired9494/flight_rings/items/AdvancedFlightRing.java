package tired9494.flight_rings.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;

public class AdvancedFlightRing extends AbstractFlightRing{
    public AdvancedFlightRing(Properties properties, ModConfig.FlightPenaltyType flightPenaltyType) {
        super(properties, flightPenaltyType);
        switch(flightPenaltyType) {
            case XP -> penaltyEffect = new AdvancedExperiencePenaltyEffect();
            case HUNGER -> penaltyEffect = new AdvancedHungerPenaltyEffect();
            default -> penaltyEffect = new NoPenaltyEffect();
        }
    }

    public int getEnchantmentValue(){
        return 11;
    }
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof ServerPlayer serverPlayer && serverPlayer.tickCount % 10 == 0) {
            if (flightEffect == null || flightEffect.value() == null) {
                regetFlightEffectHolder();
                return;
            }

            if ((penaltyEffect.canApply(serverPlayer) || serverPlayer.isCreative()) && (stack.getDamageValue() < stack.getMaxDamage() - 1 || stack.getComponents().has(DataComponents.UNBREAKABLE))) {
                serverPlayer.addEffect(new MobEffectInstance(flightEffect, 200));
                if (serverPlayer.getAbilities().flying && serverPlayer.getKnownMovement().length()!=0 && !serverPlayer.isSpectator() && !serverPlayer.isCreative()) {
                    penaltyEffect.applyPenaltyTick(serverPlayer);
                    stack.hurtAndBreak(1, serverPlayer, serverPlayer.getEquipmentSlotForItem(stack));
                    if (stack.getDamageValue() == stack.getMaxDamage() - 1) {
                        serverPlayer.playSound(stack.getBreakingSound());
                    }
                }
            }

        }
    }

    private static class AdvancedHungerPenaltyEffect extends PenaltyEffect {
        public boolean canApply(ServerPlayer serverPlayer) {
            return serverPlayer.getFoodData().getFoodLevel() > 0;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer) {
            serverPlayer.causeFoodExhaustion((float) ModConfig.advancedHungerPenalty);
        }
    }
    private static class AdvancedExperiencePenaltyEffect extends PenaltyEffect {
        private double cumulativeXp;
        public boolean canApply(ServerPlayer serverPlayer) {
            FlightRings.LOGGER.info("Player has %d experience, %g needed!".formatted(serverPlayer.totalExperience, ModConfig.advancedExperiencePenalty));
            return serverPlayer.totalExperience >= ModConfig.advancedExperiencePenalty;
        }
        public void applyPenaltyTick(ServerPlayer serverPlayer) {
            cumulativeXp += ModConfig.advancedExperiencePenalty;
            if (cumulativeXp >= 1) {
                int xpCost = (int)cumulativeXp;
                cumulativeXp -= xpCost;
                serverPlayer.giveExperiencePoints(-xpCost);
            }
        }
    }
}
