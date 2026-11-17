/*    */ package nl.melonstudios.error422.events;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.LightningBolt;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ 
/*    */ public final class EventLightning extends SpookyEvent {
/* 10 */   private final RandomSource random = RandomSource.create();
/*    */   
/*    */   public EventLightning() {
/* 13 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 23 */     Level level = player.level();
/*    */     
/* 25 */     LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
/* 26 */     bolt.setPos(player.position().add(((this.random
/* 27 */           .nextInt(20) + 10) * (this.random.nextBoolean() ? -1 : 1)), (this.random
/* 28 */           .nextInt(5) - 2), ((this.random
/* 29 */           .nextInt(20) + 10) * (this.random.nextBoolean() ? -1 : 1))));
/*    */     
/* 31 */     level.addFreshEntity((Entity)bolt);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventLightning.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */