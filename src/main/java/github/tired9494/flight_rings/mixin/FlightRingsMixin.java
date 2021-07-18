package github.tired9494.flight_rings.mixin;

import github.tired9494.flight_rings.FlightRings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static github.tired9494.flight_rings.FlightRings.getConfig;

@Mixin(PlayerEntity.class)
public abstract class FlightRingsMixin extends LivingEntity {

    @Shadow @Final private PlayerAbilities abilities;

    @Shadow public abstract void sendAbilitiesUpdate();

    @Shadow public abstract boolean isSpectator();

    @Shadow public int experienceLevel;

    @Shadow public float experienceProgress;

    @Shadow public abstract void addExhaustion(float exhaustion);

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    public FlightRingsMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    boolean trinkets = getConfig().misc.trinkets;
    boolean clearFlight = false;
    boolean useXP = getConfig().pureRingOptions.xpEnabled;
    float XPToUse = getConfig().pureRingOptions.xpCost/2000;
    double damageChance = getConfig().misc.damageChance/100;
    boolean basicProtects = getConfig().basicRingOptions.protects;
    boolean advancedProtects = getConfig().pureRingOptions.protects;
    boolean onEquip = true;
    boolean basicDurability = getConfig().basicRingOptions.durability;
    boolean advancedDurability = getConfig().pureRingOptions.durability;
    boolean basicEnabled = getConfig().basicRingOptions.enabled;

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info) {
        if (!trinkets && !this.abilities.creativeMode && !this.isSpectator()) {
            ItemStack offhand = this.getOffHandStack();
            ItemStack mainhand = this.getMainHandStack();
            Item offhandItem = offhand.getItem();
            Item mainhandItem = mainhand.getItem();
            boolean flying = this.abilities.flying;

            if (offhandItem == FlightRings.ADVANCED_RING || mainhandItem == FlightRings.ADVANCED_RING) {
                if (onEquip && getConfig().misc.autofly) {
                    if (!this.onGround) {
                        this.abilities.flying = true;
                    }
                }
                if (useXP) {
                    // XP based flying,
                    if (this.experienceLevel > 0 || this.experienceProgress > XPToUse) {
                        this.abilities.allowFlying = true;
                        clearFlight = true;

                        //xp deduction
                        if (flying) {

                            if (this.isSprinting()) {
                                this.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*damageChance)); }
                            else {
                                this.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*0.67*damageChance)); }

                            //if player has enough of a fraction of a level to fly
                            if (this.experienceProgress > XPToUse) {
                                this.experienceProgress = this.experienceProgress - XPToUse;
                            }

                            //else check if player has an extra level which can be used instead
                            else if (this.experienceProgress < XPToUse && this.experienceLevel > 0) {
                                this.experienceLevel = this.experienceLevel - 1;
                                this.experienceProgress = 1F;
                            }
                        }
                    } else {
                        if (onEquip) {
                            this.sendMessage (new TranslatableText("flight_rings.xpError"), true);
                        }
                        if (advancedProtects && this.age % 3 == 0) {
                            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, true, false));
                        } else {
                            this.abilities.allowFlying = false;
                            this.abilities.flying = false;
                            clearFlight = false;
                        }
                    }
                } else if (offhandItem == FlightRings.ADVANCED_RING) {
                    if (offhand.getDamage() != offhandItem.getMaxDamage() - 1) {
                        this.abilities.allowFlying = true;
                        clearFlight = true;
                        if (flying && Math.random() < damageChance && advancedDurability) {
                            if (this.isSprinting()) {
                                this.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*1.5)); }
                            else {
                                this.addExhaustion(getConfig().pureRingOptions.exhaustion); }
                            offhand.damage(1, this, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                        }
                    } else {
                        if (advancedProtects && this.age % 3 == 0) {
                            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, true, false));
                        }
                        this.abilities.allowFlying = false;
                        this.abilities.flying = false;
                        clearFlight = false;
                    }
                } else {
                    if (mainhand.getDamage() != mainhandItem.getMaxDamage() - 1) {
                        this.abilities.allowFlying = true;
                        clearFlight = true;
                        if (flying && Math.random() < damageChance && advancedDurability) {
                            if (this.isSprinting()) {
                                this.addExhaustion((float) (getConfig().pureRingOptions.exhaustion*1.5)); }
                            else {
                                this.addExhaustion(getConfig().pureRingOptions.exhaustion); }
                            mainhand.damage(1, this, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                        }
                    } else {
                        if (advancedProtects && this.age % 3 == 0) {
                            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2, 0, true, false));
                        }
                        this.abilities.allowFlying = false;
                        this.abilities.flying = false;
                        clearFlight = false;
                    }
                }
                onEquip = false;
            } else if (basicEnabled && offhandItem == FlightRings.BASIC_RING) {
                if (onEquip) {
                    if (!this.onGround && getConfig().misc.autofly) {
                        this.abilities.flying = true;
                    }
                    onEquip = false;
                }
                this.abilities.allowFlying = true;
                clearFlight = true;
                if (flying && Math.random() < damageChance && basicDurability) {
                    if (this.isSprinting()) {
                        this.addExhaustion(getConfig().basicRingOptions.exhaustion*2); }
                    else {
                        this.addExhaustion(getConfig().basicRingOptions.exhaustion); }
                    if (basicProtects && offhand.getDamage() == offhandItem.getMaxDamage() - 1) {
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0));
                    }
                    offhand.damage(1, this, e -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND));
                }
            } else if (basicEnabled && mainhandItem == FlightRings.BASIC_RING) {
                if (onEquip) {
                    if (!this.onGround && getConfig().misc.autofly) {
                        this.abilities.flying = true;
                    }
                    onEquip = false;
                }
                this.abilities.allowFlying = true;
                clearFlight = true;
                if (flying && Math.random() < damageChance && basicDurability) {
                    if (this.isSprinting()) {
                        this.addExhaustion(getConfig().basicRingOptions.exhaustion*2); }
                    else {
                        this.addExhaustion(getConfig().basicRingOptions.exhaustion); }
                    if (basicProtects && mainhand.getDamage() == mainhandItem.getMaxDamage() - 1) {
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0));
                    }
                    mainhand.damage(1, this, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
                }
            }

            else {
                onEquip = true;
                if (clearFlight) {
                    this.abilities.allowFlying = false;
                    this.abilities.flying = false;
                    clearFlight = false;
                }
                this.sendAbilitiesUpdate();
            }
        }
    }
}
