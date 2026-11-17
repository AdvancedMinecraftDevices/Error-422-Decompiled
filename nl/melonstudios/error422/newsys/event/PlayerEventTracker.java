/*    */ package nl.melonstudios.error422.newsys.event;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Random;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public class PlayerEventTracker {
/* 11 */   private final Random random = new Random();
/*    */   public final ServerPlayer player;
/*    */   public final Map<LocalEvent, CountDown> trackers;
/*    */   
/*    */   public PlayerEventTracker(ServerPlayer player) {
/* 16 */     this.trackers = new HashMap<>();
/*    */     this.player = player; } public void init() {
/* 18 */     for (UnmodifiableIterator<LocalEvent> unmodifiableIterator = EventManager.getAllLocalEvents().iterator(); unmodifiableIterator.hasNext(); ) { LocalEvent event = unmodifiableIterator.next();
/* 19 */       Objects.requireNonNull(this.random); this.trackers.put(event, new CountDown(event.getInterval(), this.random::nextInt)); }
/*    */   
/*    */   }
/*    */   public void tick() {
/* 23 */     for (Map.Entry<LocalEvent, CountDown> entry : this.trackers.entrySet()) {
/* 24 */       CountDown count = entry.getValue();
/* 25 */       count.tick();
/* 26 */       if (count.isReady()) {
/* 27 */         count.resetTicker();
/* 28 */         if (((LocalEvent)entry.getKey()).requiredPhase <= Err422PhaseManager.phase)
/* 29 */           ((LocalEvent)entry.getKey()).occur(this.player); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\PlayerEventTracker.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */