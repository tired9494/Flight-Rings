package github.tired9494.flight_rings.rings;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

import java.util.List;


public class AdvancedRing extends Item {

    public AdvancedRing(Settings settings) { super(settings);}

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public int getEnchantability() {
        return 11;
    }

    @Override

    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("tooltip.flight_rings.advanced"));
    }
}

