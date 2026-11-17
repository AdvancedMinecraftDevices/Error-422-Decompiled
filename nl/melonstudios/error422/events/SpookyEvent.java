/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public abstract class SpookyEvent
/*    */ {
/*    */   public static final int DEFAULT_WEIGHT = 64;
/*    */   
/*    */   public final int weight() {
/* 10 */     return this.weight;
/*    */   } private final int weight;
/*    */   public abstract boolean canOccur(Player paramPlayer);
/*    */   public abstract void occur(Player paramPlayer);
/*    */   protected SpookyEvent(int weight) {
/* 15 */     this.weight = weight;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\SpookyEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */