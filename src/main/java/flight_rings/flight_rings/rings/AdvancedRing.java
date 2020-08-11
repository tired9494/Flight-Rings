package flight_rings.flight_rings.rings;

import flight_rings.flight_rings.FlightRings;
import flight_rings.flight_rings.config.ModConfig;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;


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
        boolean useXP = ModConfig.INSTANCE.pureRingUsesXP;
        double damageChance = ModConfig.INSTANCE.damageChancePerTick/100;
        float XPToUse = ModConfig.INSTANCE.pureRingXPAmount;
        LivingEntity livingEntity = (LivingEntity) entity;
        PlayerEntity player = (PlayerEntity) livingEntity;
        Item mainItem = player.getMainHandStack().getItem();
        Item offItem = player.getOffHandStack().getItem();

        if (mainItem == FlightRings.ADVANCED_RING | offItem == FlightRings.ADVANCED_RING) {
            if (useXP) {
                // XP based flying,
                if (player.experienceProgress > XPToUse | player.experienceLevel > 0) {
                    player.abilities.allowFlying = true;
                    if (player.abilities.flying & player.experienceProgress > XPToUse) {
                        player.experienceProgress = player.experienceProgress - XPToUse;
                    }
                    else if (player.abilities.flying && player.experienceProgress < XPToUse && player.experienceLevel > 0) {
                        player.experienceLevel = player.experienceLevel - 1;
                        player.experienceProgress = 1F;
                }
            }
        }
        else if (mainItem == FlightRings.ADVANCED_RING) {
            // uses durability instead, with a chance per tick to damage. (As opposed to high durability and 1 damage per tick, to balance with mending)
            player.abilities.allowFlying = true;
            if (player.abilities.flying & Math.random() < damageChance) {
                player.getMainHandStack().damage(1, livingEntity, (Consumer) ((p) -> {
                }));}
            }
        else {
            // ditto but offhand
                player.abilities.allowFlying = true;
                if (player.abilities.flying & Math.random() < damageChance) {
                    player.getOffHandStack().damage(1, livingEntity, (Consumer) ((p) -> {
                    }));}
            }
        }
        else if (!(mainItem == FlightRings.BASIC_RING_ALT || offItem == FlightRings.BASIC_RING_ALT || mainItem == FlightRings.BASIC_RING || offItem == FlightRings.BASIC_RING) && !player.abilities.creativeMode) {
            player.abilities.allowFlying = false;
            player.abilities.flying = false;
        }
    }
}

