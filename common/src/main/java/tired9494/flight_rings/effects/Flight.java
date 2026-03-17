package tired9494.flight_rings.effects;

import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FastColor;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import tired9494.flight_rings.ModConfig;

public class Flight extends MobEffect {
    private static final int color = 0x9DE3CE;
    private int duration;

    public Flight(ModConfig.FlightPenaltyType flightPenaltyType) {
        super(MobEffectCategory.BENEFICIAL, color, ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, FastColor.ARGB32.color(0, color)));
    }

    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide && livingEntity instanceof  ServerPlayer serverPlayer
                && !serverPlayer.isCreative() && !serverPlayer.isSpectator()) {
            //server player is in survival...
            serverPlayer.getAbilities().mayfly = true;

            if (this.duration <= 1) {
                serverPlayer.getAbilities().mayfly = false;
                serverPlayer.getAbilities().flying = false;
            }
            serverPlayer.onUpdateAbilities();
        }
        return true;
    }

    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        this.duration = duration;
        return duration >= 1;
    }

}
