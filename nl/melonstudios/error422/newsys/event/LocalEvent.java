/*    */ package nl.melonstudios.error422.newsys.event;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ 
/*    */ public abstract class LocalEvent
/*    */   implements Event {
/*  9 */   protected final Random random = new Random();
/*    */   
/*    */   public final int requiredPhase;
/*    */   public final EventInterval interval;
/*    */   public ResourceLocation registeredId;
/*    */   
/*    */   protected LocalEvent(EventInterval interval, int requiredPhase) {
/* 16 */     this.registeredId = ResourceLocation.parse("minecraft:empty");
/*    */     this.interval = interval;
/* 18 */     this.requiredPhase = requiredPhase; } public final void setResource(ResourceLocation id) { this.registeredId = id; }
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean isLocal() {
/* 23 */     return true;
/*    */   }
/*    */   
/*    */   public final EventInterval getInterval() {
/* 27 */     return this.interval;
/*    */   }
/*    */   
/*    */   public abstract void occur(ServerPlayer paramServerPlayer);
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\LocalEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */