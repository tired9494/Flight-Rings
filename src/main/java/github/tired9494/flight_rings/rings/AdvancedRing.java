package github.tired9494.flight_rings.rings;

import github.tired9494.flight_rings.FlightRings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

import static github.tired9494.flight_rings.FlightRings.getConfig;


public class AdvancedRing extends Item {

    public AdvancedRing(Settings settings) { super(settings);}

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public int getEnchantability() {
        return 1;
    }

    @Override

    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("tooltip.flight_rings.advanced"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        boolean useXP = getConfig().pureOptions.xpEnabled;
        double damageChance = getConfig().misc.damageChance/100;
        float XPToUse = getConfig().pureOptions.xpCost;
        LivingEntity livingEntity = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) livingEntity;
        Item mainItem = player.getMainHandStack().getItem();
        Item offItem = player.getOffHandStack().getItem();
        Item basic = FlightRings.BASIC_RING;
        Item advanced = FlightRings.ADVANCED_RING;
        boolean flying = player.getAbilities().flying;

        if (offItem == advanced || mainItem == advanced) {
            player.addExhaustion(getConfig().pureOptions.exhaustion/6);
            if (useXP) {
                // XP based flying,
                if (player.experienceLevel > 0 || player.experienceProgress > XPToUse) {
                    player.getAbilities().allowFlying = false;
                    if (flying && player.experienceProgress > XPToUse) {
                        player.experienceProgress = player.experienceProgress - XPToUse;
                    }
                    else if (flying && player.experienceProgress < XPToUse && player.experienceLevel > 0) {
                        player.experienceLevel = player.experienceLevel - 1;
                        player.experienceProgress = 1F;
                    }
                }
            }
            else if (mainItem == advanced) {
                // uses durability instead, with a chance per tick to damage. (As opposed to high durability and 1 damage per tick, to balance with mending)
                if (player.getMainHandStack().getDamage() != 8999) {
                    player.getAbilities().allowFlying = true;
                    if (flying && Math.random() < damageChance) {
                        player.addExhaustion(getConfig().pureOptions.exhaustion);
                        player.getMainHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));}
                }
                else {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 0));
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                }
            }
            else {
                // ditto but offhand
                if (player.getOffHandStack().getDamage() != 8999) {
                    player.getAbilities().allowFlying = true;
                    if (flying && Math.random() < damageChance) {
                        player.addExhaustion(getConfig().pureOptions.exhaustion);
                        player.getOffHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));}
                }
                else {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 10, 0));
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                }
            }
        }
        else if (!(offItem == basic || mainItem == basic) && !(player.getAbilities().creativeMode || player.isSpectator())) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
        }
    }
}

