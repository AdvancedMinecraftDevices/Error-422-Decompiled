/*    */ package nl.melonstudios.error422.entity;
/*    */ 
/*    */ import net.minecraft.world.entity.LivingEntity;
/*    */ import net.minecraft.world.entity.Mob;
/*    */ import net.minecraft.world.entity.ai.goal.target.TargetGoal;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TargetPlayerGoal
/*    */   extends TargetGoal
/*    */ {
/*    */   private final Player player;
/*    */   
/*    */   public TargetPlayerGoal(Mob mob, Player player) {
/* 79 */     super(mob, false);
/* 80 */     this.targetMob = (LivingEntity)player;
/* 81 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canUse() {
/* 86 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canContinueToUse() {
/* 91 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stop() {
/* 96 */     super.stop();
/* 97 */     this.targetMob = (LivingEntity)this.player;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\entity\TheEntityKill$TargetPlayerGoal.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */