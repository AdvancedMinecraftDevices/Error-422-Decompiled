/*    */ package nl.melonstudios.error422.newsys.event.global;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import nl.melonstudios.error422.newsys.ErrMessageProvider;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.GlobalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class GlobalErrorDumpEvent extends GlobalEvent {
/*    */   public GlobalErrorDumpEvent() {
/* 12 */     super(EventInterval.capture(Ticks.fromMinutes(5), Ticks.fromMinutes(20)), 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerLevel level) {
/* 17 */     Component error = ErrMessageProvider.provideServer();
/* 18 */     level.players().forEach(p -> p.sendSystemMessage(error));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\global\GlobalErrorDumpEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */