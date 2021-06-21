package github.tired9494.flight_rings.rings;

import github.tired9494.flight_rings.FlightRings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

import static github.tired9494.flight_rings.FlightRings.getConfig;


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
        double damageChance = getConfig().misc.damageChance/100;
        LivingEntity livingEntity = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) livingEntity;
        Item mainItem = player.getMainHandStack().getItem();
        Item offItem = player.getOffHandStack().getItem();
        Item basic = FlightRings.BASIC_RING;
        Item advanced = FlightRings.ADVANCED_RING;
        boolean flying = player.getAbilities().flying;
        if (!(player.getAbilities().creativeMode || player.isSpectator())) {
            if ((offItem == basic || mainItem == basic) && !(mainItem == advanced || offItem == advanced)) {
                player.getAbilities().allowFlying = true;
                player.addExhaustion(getConfig().basicOptions.exhaustion/4);
                if (flying) {
                    player.addExhaustion(getConfig().basicOptions.exhaustion);
                }
                if (flying && getConfig().basicOptions.durability && Math.random() < damageChance) {
                    if (mainItem == basic) {
                        if (player.getMainHandStack().getDamage() == 1) {
                            player.getAbilities().allowFlying = false;
                            player.getAbilities().flying = false;
                        }
                        player.getMainHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                    }
                    else {
                        if (player.getOffHandStack().getDamage() == 1) {
                            player.getAbilities().allowFlying = false;
                            player.getAbilities().flying = false;
                        }
                        player.getOffHandStack().damage(1, player, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                    }
                }
            }
            else if (!(mainItem == advanced || offItem == advanced)) {
                player.getAbilities().allowFlying = false;
                player.getAbilities().flying = false;
            }
        }
    }
}

