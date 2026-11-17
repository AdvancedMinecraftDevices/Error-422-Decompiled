/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.effect.MobEffect;
/*    */ import net.minecraft.world.effect.MobEffectInstance;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public class EventRandomMobEffect
/*    */   extends SpookyEvent {
/*    */   public EventRandomMobEffect() {
/* 14 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 24 */     RandomSource rnd = RandomSource.create();
/* 25 */     Optional<Holder.Reference<MobEffect>> effectReference = BuiltInRegistries.MOB_EFFECT.getRandom(rnd);
/* 26 */     if (effectReference.isPresent()) {
/* 27 */       Holder<MobEffect> effect = (Holder<MobEffect>)effectReference.get();
/* 28 */       player.addEffect(new MobEffectInstance(effect, 100 + rnd
/* 29 */             .nextInt(400), rnd.nextInt(3)));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventRandomMobEffect.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */