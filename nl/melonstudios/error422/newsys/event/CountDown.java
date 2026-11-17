/*    */ package nl.melonstudios.error422.newsys.event;
/*    */ 
/*    */ import nl.melonstudios.error422.newsys.RandIntProvider;
/*    */ 
/*    */ public class CountDown
/*    */ {
/*    */   public final EventInterval interval;
/*    */   
/*    */   public CountDown(EventInterval interval, RandIntProvider intProvider) {
/* 10 */     this.interval = interval;
/* 11 */     this.intProvider = intProvider;
/* 12 */     this.ticker = this.interval.getNextDelay(intProvider);
/*    */   }
/*    */   public final RandIntProvider intProvider; public int ticker;
/*    */   
/*    */   public void tick() {
/* 17 */     if (this.ticker > 0) this.ticker--; 
/*    */   }
/*    */   public boolean isReady() {
/* 20 */     return (this.ticker <= 0);
/*    */   }
/*    */   public void resetTicker() {
/* 23 */     this.ticker = this.interval.getNextDelay(this.intProvider);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\CountDown.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */