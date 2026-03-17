package tired9494.flight_rings.registry;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import tired9494.flight_rings.FlightRings;
import tired9494.flight_rings.ModConfig;
import tired9494.flight_rings.effects.Flight;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(FlightRings.MOD_ID, Registries.MOB_EFFECT);

    public static Supplier<MobEffect> FLIGHT;

    public static void initEffects(){
        FLIGHT = EFFECTS.register(FlightRings.id("flight"), () ->
                new Flight());

        EFFECTS.register();
    }
}
