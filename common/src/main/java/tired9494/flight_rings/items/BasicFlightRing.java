package tired9494.flight_rings.items;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;

public class BasicFlightRing extends AbstractFlightRing{
    public BasicFlightRing(Properties properties, ModConfig.FlightPenaltyType flightPenaltyType) {
        super(properties, flightPenaltyType);

    }

    public int getEnchantmentValue(){
        return 3;
    }
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof ServerPlayer serverPlayer && serverPlayer.tickCount % 20 == 0) {
            if (flightEffect == null || flightEffect.value() == null) {
                regetFlightEffectHolder();
                return;
            }

            if (penaltyEffect.canApply(serverPlayer) || serverPlayer.isCreative()) {
                serverPlayer.addEffect(new MobEffectInstance(flightEffect, 200));
                if (serverPlayer.getAbilities().flying && !serverPlayer.isSpectator() && !serverPlayer.isCreative()) {
                    penaltyEffect.applyPenaltyTick(serverPlayer);
                    stack.hurtAndBreak(1, serverPlayer, serverPlayer.getEquipmentSlotForItem(stack));
                }
            }

        }
    }
}
