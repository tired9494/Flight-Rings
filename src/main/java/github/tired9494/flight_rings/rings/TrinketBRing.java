package github.tired9494.flight_rings.rings;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;


import static github.tired9494.flight_rings.FlightRings.getConfig;


public class TrinketBRing implements Trinket {

    double damageChance = getConfig().misc.damageChance/100;
    boolean basicProtects = getConfig().basicRingOptions.protects;

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        player.getAbilities().allowFlying = true;
        if (!player.isOnGround() && getConfig().misc.autofly) {
            player.getAbilities().flying = true;
        }
        player.sendAbilitiesUpdate();
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        if (!player.getAbilities().creativeMode && !player.isSpectator()) {
            player.getAbilities().allowFlying = true;
            if (player.getAbilities().flying && Math.random() < damageChance) {
                if (player.isSprinting()) {
                    player.addExhaustion(getConfig().basicRingOptions.exhaustion*2);
                }
                else {
                    player.addExhaustion(getConfig().basicRingOptions.exhaustion);
                }
                if (stack.getDamage() == stack.getMaxDamage() - 1) {
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                    player.playSound(SoundEvents.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
                    if (basicProtects) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0));
                    }
                }
                stack.damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        if (!player.getAbilities().creativeMode && !player.isSpectator()) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            player.sendAbilitiesUpdate();
        }
    }
}

