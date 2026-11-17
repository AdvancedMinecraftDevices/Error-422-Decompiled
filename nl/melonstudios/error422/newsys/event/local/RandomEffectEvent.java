/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.effect.MobEffects;
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.targeting.TargetingConditions;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class RandomEffectEvent extends LocalEvent {
/*    */   public RandomEffectEvent() {
/* 20 */     super(EventInterval.capture(Ticks.fromMinutes(5), Ticks.fromMinutes(20)), 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 25 */     player.addEffect(new MobEffectInstance(
/* 26 */           findBestMobEffect(player), 
/* 27 */           Ticks.fromSeconds(15 + this.random.nextInt(15)), this.random
/* 28 */           .nextInt(3)));
/*    */   }
/*    */ 
/*    */   
/*    */   private Holder<MobEffect> findBestMobEffect(ServerPlayer player) {
/* 33 */     boolean tryPickSituational = (this.random.nextInt(3) == 0);
/* 34 */     if (tryPickSituational && player.getY() > 128.0D) return MobEffects.MOVEMENT_SPEED; 
/* 35 */     if (tryPickSituational && player.getY() < 16.0D) return MobEffects.DIG_SPEED; 
/* 36 */     if (tryPickSituational && player.level().getNearestEntity(Mob.class, 
/* 37 */         TargetingConditions.forCombat(), (LivingEntity)player, player.getX(), player.getY(), player.getZ(), player
/* 38 */         .getBoundingBox().inflate(16.0D)) != null) {
/* 39 */       return this.random.nextBoolean() ? MobEffects.WEAKNESS : MobEffects.DAMAGE_BOOST;
/*    */     }
/* 41 */     return justPickRandom();
/*    */   }
/*    */   private Holder<MobEffect> justPickRandom() {
/* 44 */     Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.getRandom(RandomSource.create());
/* 45 */     if (effect.isPresent()) {
/* 46 */       return (Holder<MobEffect>)effect.get();
/*    */     }
/* 48 */     return MobEffects.CONDUIT_POWER;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\RandomEffectEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */