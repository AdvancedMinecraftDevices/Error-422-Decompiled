/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ 
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import nl.melonstudios.error422.newsys.ErrMessageProvider;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class LocalErrorDumpEvent extends LocalEvent {
/*    */   public LocalErrorDumpEvent() {
/* 12 */     super(EventInterval.capture(Ticks.fromMinutes(5), Ticks.fromMinutes(20)), 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 17 */     Component error = ErrMessageProvider.provideClient();
/* 18 */     player.sendSystemMessage(error);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\LocalErrorDumpEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */