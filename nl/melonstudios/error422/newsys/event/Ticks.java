/*   */ package nl.melonstudios.error422.newsys.event;
/*   */ 
/*   */ public interface Ticks {
/*   */   static int fromSeconds(int s) {
/* 5 */     return s * 20;
/*   */   }
/*   */   static int fromMinutes(int m) {
/* 8 */     return m * 1200;
/*   */   }
/*   */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\Ticks.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */