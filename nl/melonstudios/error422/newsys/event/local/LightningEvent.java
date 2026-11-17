/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.Entity;
/*    */ import net.minecraft.world.entity.LightningBolt;
/*    */ import net.minecraft.world.level.Level;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class LightningEvent extends LocalEvent {
/*    */   public LightningEvent() {
/* 12 */     super(EventInterval.capture(Ticks.fromMinutes(10), Ticks.fromMinutes(20)), 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 17 */     int x = this.random.nextInt(-7, 8);
/* 18 */     int z = this.random.nextInt(-7, 8);
/* 19 */     LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, (Level)player.serverLevel());
/* 20 */     bolt.setPos(player.getX() + x, player.getY(), player.getZ() + z);
/* 21 */     bolt.setCause(null);
/* 22 */     player.level().addFreshEntity((Entity)bolt);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\LightningEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */