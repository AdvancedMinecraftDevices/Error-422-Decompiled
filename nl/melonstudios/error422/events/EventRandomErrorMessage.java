/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import nl.melonstudios.error422.newsys.ErrMessageProvider;
/*    */ 
/*    */ public class EventRandomErrorMessage
/*    */   extends SpookyEvent {
/*    */   private final List<String> messages;
/* 11 */   private final Random random = new Random();
/*    */   protected EventRandomErrorMessage(List<String> messages) {
/* 13 */     super(64);
/*    */     
/* 15 */     this.messages = messages;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 20 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 25 */     player.sendSystemMessage(ErrMessageProvider.provideWithContext(player));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventRandomErrorMessage.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */