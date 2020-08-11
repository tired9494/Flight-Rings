package flight_rings.flight_rings.rings;

import flight_rings.flight_rings.FlightRings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
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
import java.util.function.Consumer;


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
        int hungerLevel = ModConfig.INSTANCE.basicHungerLevel;
        double damageChance = ModConfig.INSTANCE.damageChancePerTick/100;
        LivingEntity livingEntity = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) livingEntity;
        Item mainItem = player.getMainHandStack().getItem();
        Item offItem = player.getOffHandStack().getItem();
        if ((mainItem == FlightRings.BASIC_RING || offItem == FlightRings.BASIC_RING || mainItem == FlightRings.BASIC_RING_ALT || offItem == FlightRings.BASIC_RING_ALT) && !(mainItem == FlightRings.ADVANCED_RING || offItem == FlightRings.ADVANCED_RING)) {
            player.abilities.allowFlying = true;
            if (hungerLevel > 0 & !livingEntity.hasStatusEffect(StatusEffects.HUNGER) & player.abilities.flying) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 10, hungerLevel-1));
            }
            if (ModConfig.INSTANCE.basicRingHasDurability & player.abilities.flying & Math.random() < damageChance & (mainItem == FlightRings.BASIC_RING || mainItem == FlightRings.BASIC_RING_ALT)) {
                player.getMainHandStack().damage(1, livingEntity, (Consumer) ((p) -> {
                }));}
            else if (ModConfig.INSTANCE.basicRingHasDurability & player.abilities.flying & Math.random() < damageChance) {
                player.getOffHandStack().damage(1, livingEntity, (Consumer) ((p) -> {
                }));}
        }
        else if (!(mainItem == FlightRings.ADVANCED_RING || offItem == FlightRings.ADVANCED_RING) && !player.abilities.creativeMode) {
            player.abilities.allowFlying = false;
            player.abilities.flying = false;
        }
    }
}

