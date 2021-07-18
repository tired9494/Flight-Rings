package github.tired9494.flight_rings.rings;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;

import static github.tired9494.flight_rings.FlightRings.getConfig;


public class TrinketARing implements Trinket {

    double damageChance = getConfig().misc.damageChance/100;
    float XPToUse = getConfig().pureRingOptions.xpCost/2000;
    boolean useXP = getConfig().pureRingOptions.xpEnabled;
    boolean advancedProtects = getConfig().pureRingOptions.protects;

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        player.getAbilities().allowFlying = true;
        if (useXP && !(player.experienceLevel > 0 || player.experienceProgress > XPToUse)) {
            player.sendMessage (new TranslatableText("flight_rings.xpError"), true);
        }
        if (!player.isOnGround() && getConfig().misc.autofly) {
            player.getAbilities().flying = true;
        }
        player.sendAbilitiesUpdate();
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity) entity;
        if (!player.getAbilities().creativeMode && !player.isSpectator()) {
            if (useXP) {
                // XP based flying,
                if (player.experienceLevel > 0 || player.experienceProgress > XPToUse) {
                    player.getAbilities().allowFlying = true;

                    if (player.getAbilities().flying) {

                        if (player.isSprinting()) {
                            player.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*damageChance)); }
                        else {
                            player.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*0.67*damageChance)); }

                        if (player.experienceProgress > XPToUse) {
                            player.experienceProgress = player.experienceProgress - XPToUse;

                        } else if (player.experienceProgress < XPToUse && player.experienceLevel > 0) {
                            player.experienceLevel = player.experienceLevel - 1;
                            player.experienceProgress = 1F;
                        }
                    }
                }
                else {
                    if (advancedProtects && player.age % 3 == 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, true, false));
                    }
                    else {
                        player.getAbilities().allowFlying = false;
                        player.getAbilities().flying = false;
                        player.sendAbilitiesUpdate();
                    }
                }
            }
            else {
                // uses durability instead, with a chance per tick to damage. (As opposed to high durability and 1 damage per tick, to balance with mending)
                if (stack.getDamage() != stack.getMaxDamage() - 1) {
                    player.getAbilities().allowFlying = true;
                    if (player.getAbilities().flying && Math.random() < damageChance) {
                        if (player.isSprinting()) {
                            player.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*1.5));
                        }
                        else {
                            player.addExhaustion(getConfig().pureRingOptions.exhaustion);
                        }
                        stack.damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.LEGS));
                    }
                } else {
                    if (advancedProtects && player.age % 3 == 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, true, false));
                    }
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                }
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

