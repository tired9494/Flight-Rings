package flight_rings.flight_rings.rings;

import flight_rings.flight_rings.FlightRings;
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
import flight_rings.flight_rings.config.ModConfig;

import java.util.List;


public class BasicRing extends Item {

    public BasicRing(Settings settings) { super(settings);}

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("tooltip.flight_rings.basic"));
        tooltip.add(new TranslatableText("tooltip.flight_rings.basic2"));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        double damageChance = ModConfig.INSTANCE.damageChancePerTick/100;
        LivingEntity livingEntity = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) livingEntity;
        Item mainItem = player.getMainHandStack().getItem();
        Item offItem = player.getOffHandStack().getItem();
        Item basic = FlightRings.BASIC_RING;
        Item basicAlt = FlightRings.BASIC_RING_ALT;
        Item advanced = FlightRings.ADVANCED_RING;
        boolean flying = player.abilities.flying;
        if (!player.abilities.creativeMode) {
            if ((mainItem == basic | offItem == basic | mainItem == basicAlt | offItem == basicAlt) & !(mainItem == advanced | offItem == advanced)) {
                player.abilities.allowFlying = true;
                player.addExhaustion(ModConfig.INSTANCE.basicExhaustion/4);
                if (flying) {
                    player.addExhaustion(ModConfig.INSTANCE.basicExhaustion);
                }
                if (flying & ModConfig.INSTANCE.basicRingHasDurability & Math.random() < damageChance) {
                    if (mainItem == basic | mainItem == basicAlt) {
                        if (player.getMainHandStack().getDamage() == 1) {
                            player.abilities.allowFlying = false;
                            player.abilities.flying = false;
                        }
                        player.getMainHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }
                    else {
                        if (player.getOffHandStack().getDamage() == 1) {
                            player.abilities.allowFlying = false;
                            player.abilities.flying = false;
                        }
                        player.getOffHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                    }
                }
            }
            else if (!(mainItem == advanced | offItem == advanced)) {
                player.abilities.allowFlying = false;
                player.abilities.flying = false;
            }
        }
    }
}

