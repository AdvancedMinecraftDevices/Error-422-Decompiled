/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import nl.melonstudios.error422.events.EventErrScreen;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class ErrScreenEvent extends LocalEvent {
/*    */   public ErrScreenEvent() {
/* 11 */     super(EventInterval.capture(Ticks.fromMinutes(5), Ticks.fromMinutes(25)), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 16 */     EventErrScreen.enact((Player)player, 200);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\ErrScreenEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */