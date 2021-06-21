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
    public basicOptions basicOptions = new basicOptions();


    public static class basicOptions
    {
        @Comment("")
        public boolean enabled = true;

        @Comment("Recipe uses an ender eye instead of quartz, §c§ldeletes all existing rings. §rRequires restart")
        public boolean eye = false;

        @Comment("Basic ring has durability")
        public boolean durability = true;

        @Comment("")
        public float exhaustion = 0.15F;
    }



    /**
     * advanced ring
     */
    @Comment("")
    @ConfigEntry.Gui.CollapsibleObject
    public pureOptions pureOptions = new pureOptions();

    public static class pureOptions
    {
        @Comment("")
        public float exhaustion = 0.01F;
        @Comment("Pure ring uses XP instead of durability")
        public boolean xpEnabled = false;
        @Comment("How much XP the pure ring uses")
        public float xpCost = 0.001F;
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

        @Comment("Disables recipes, §c§ldeletes all existing rings. §rRequires restart")
        public boolean treasure = false;
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
