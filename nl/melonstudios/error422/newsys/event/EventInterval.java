/*    */ package nl.melonstudios.error422.newsys.event;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import nl.melonstudios.error422.newsys.RandIntProvider;
/*    */ 
/*    */ public class EventInterval {
/*    */   public final int minTickDelay;
/*    */   
/*    */   private EventInterval(int min, int max) {
/* 11 */     this.minTickDelay = min;
/* 12 */     this.maxTickDelay = max;
/*    */   }
/*    */   public final int maxTickDelay;
/*    */   public static EventInterval capture(int minTicks, int maxTicks) {
/* 16 */     return new EventInterval(minTicks, maxTicks);
/*    */   }
/*    */   
/*    */   public int getNextDelay(Random random) {
/* 20 */     return random.nextInt(this.minTickDelay, this.maxTickDelay);
/*    */   }
/*    */   public int getNextDelay(RandomSource random) {
/* 23 */     return random.nextInt(this.minTickDelay, this.maxTickDelay);
/*    */   }
/*    */   public int getNextDelay(RandIntProvider random) {
/* 26 */     return random.getInt(this.minTickDelay, this.maxTickDelay);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\EventInterval.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */