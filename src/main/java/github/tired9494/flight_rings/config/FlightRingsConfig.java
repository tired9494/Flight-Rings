package github.tired9494.flight_rings.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

@Config(name = "Flight Rings")
public class FlightRingsConfig implements ConfigData
{

    /**
     * basic ring
     */
    @Comment("")
    @ConfigEntry.Gui.CollapsibleObject
    public basicRingOptions basicRingOptions = new basicRingOptions();


    public static class basicRingOptions
    {
        @Comment("")
        public boolean enabled = true;
        @Comment("")
        public boolean durability = true;
        @Comment("Durability of basic ring")
        public int durabilityValue = 180;
        @Comment("Recipe uses an ender eye instead of quartz. §c§lDeletes existing basic rings")
        public boolean eye = false;
        @Comment("")
        public float exhaustion = 0.15F;
        @Comment("Basic ring gives slow falling after breaking")
        public boolean protects = false;
    }



    /**
     * advanced ring
     */
    @Comment("")
    @ConfigEntry.Gui.CollapsibleObject
    public pureRingOptions pureRingOptions = new pureRingOptions();

    public static class pureRingOptions
    {
        @Comment("")
        public boolean durability = true;
        @Comment("Durability of pure ring")
        public int durabilityValue = 9000;
        @Comment("")
        public float exhaustion = 0.03F;
        @Comment("Pure ring uses XP instead of durability")
        public boolean xpEnabled = false;
        @Comment("How much percentage of the XP bar is used per second")
        public float xpCost = 1.0F;
        @Comment("Pure ring gives you slow falling when out of durability")
        public boolean protects = true;
    }

    /**
     * misc
     */
    @Comment("")
    @ConfigEntry.Gui.CollapsibleObject
    public misc misc = new misc();


    public static class misc
    {
        @Comment("Percentage chance per tick (20/s) for a ring to lose durability")
        public double damageChance = 10;

        @Comment("Recipes disabled. §c§lDeletes all existing rings")
        public boolean treasure = false;

        @Comment("Enables rings to spawn in chest loot")
        public boolean chestLoot = true;

        @Comment("Adds Trinkets support. §c§lDeletes all existing rings")
        public boolean trinkets = false;

        @Comment("Rings force flight when equipping or loading world midair")
        public boolean autofly = true;
    }


    /**
     * Registers and prepares a new configuration instance.
     *
     * @return registered config holder
     * @see AutoConfig#register
     */
    public static ConfigHolder<FlightRingsConfig> init()
    {
        // Register the config
        ConfigHolder<FlightRingsConfig> holder = AutoConfig.register(FlightRingsConfig.class, JanksonConfigSerializer::new);

        // Listen for when the server is reloading (i.e. /reload), and reload the config
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((s, m) ->
                AutoConfig.getConfigHolder(FlightRingsConfig.class).load());

        return holder;
    }

    /**
     * Mod Menu integration.
     */
    @Environment(EnvType.CLIENT)
    public static class ModMenuIntegration implements ModMenuApi
    {
        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory()
        {
            return screen -> AutoConfig.getConfigScreen(FlightRingsConfig.class, screen).get();
        }
    }
}
