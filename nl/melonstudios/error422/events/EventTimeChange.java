/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public class EventTimeChange
/*    */   extends SpookyEvent {
/*    */   public EventTimeChange() {
/* 12 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 17 */     return (Err422PhaseManager.phase > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 22 */     Level level = Objects.<Level>requireNonNull(player.level());
/* 23 */     if (level instanceof ServerLevel) { ServerLevel sl = (ServerLevel)level;
/* 24 */       sl.setDayTime(sl.getDayTime() + (sl.random.nextBoolean() ? 12000L : -12000L)); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventTimeChange.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */