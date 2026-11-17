/*    */ package nl.melonstudios.error422.newsys.event;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Random;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public class LevelEventTracker {
/* 11 */   private final Random random = new Random();
/*    */   public final ServerLevel level;
/*    */   public final Map<GlobalEvent, CountDown> trackers;
/*    */   
/*    */   public LevelEventTracker(ServerLevel level) {
/* 16 */     this.trackers = new HashMap<>();
/*    */     this.level = level; } public void init() {
/* 18 */     for (UnmodifiableIterator<GlobalEvent> unmodifiableIterator = EventManager.getAllGlobalEvents().iterator(); unmodifiableIterator.hasNext(); ) { GlobalEvent event = unmodifiableIterator.next();
/* 19 */       Objects.requireNonNull(this.random); this.trackers.put(event, new CountDown(event.getInterval(), this.random::nextInt)); }
/*    */   
/*    */   }
/*    */   public void tick() {
/* 23 */     for (Map.Entry<GlobalEvent, CountDown> entry : this.trackers.entrySet()) {
/* 24 */       CountDown count = entry.getValue();
/* 25 */       count.tick();
/* 26 */       if (count.isReady()) {
/* 27 */         count.resetTicker();
/* 28 */         if (((GlobalEvent)entry.getKey()).requiredPhase <= Err422PhaseManager.phase)
/* 29 */           ((GlobalEvent)entry.getKey()).occur(this.level); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\LevelEventTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */