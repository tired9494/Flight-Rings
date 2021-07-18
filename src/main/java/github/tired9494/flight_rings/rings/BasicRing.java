package github.tired9494.flight_rings.rings;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;

import static github.tired9494.flight_rings.FlightRings.getConfig;


public class BasicRing extends Item {

    public BasicRing(Settings settings) { super(settings);}

    public int getEnchantability() {
        return 3;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (getConfig().basicRingOptions.enabled) {
            tooltip.add(new TranslatableText("tooltip.flight_rings.basic"));
            tooltip.add(new TranslatableText("tooltip.flight_rings.basic2"));
        }
        else {
            tooltip.add(new TranslatableText("tooltip.flight_rings.disabled"));
        }
    }
}

