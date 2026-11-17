/*    */ package nl.melonstudios.error422.newsys.event.global;
/*    */ 
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.GlobalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class TimeChangeEvent extends GlobalEvent {
/*    */   public TimeChangeEvent() {
/* 10 */     super(EventInterval.capture(Ticks.fromMinutes(10), Ticks.fromMinutes(30)), 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerLevel level) {
/* 15 */     long time = level.getDayTime();
/* 16 */     level.setDayTime(Math.max(time + this.random.nextInt(24000) - 12000L, 0L));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\global\TimeChangeEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */