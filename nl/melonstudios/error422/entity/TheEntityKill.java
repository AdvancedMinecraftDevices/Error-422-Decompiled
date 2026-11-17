/*     */ package nl.melonstudios.error422.entity;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.Mob;
/*     */ import net.minecraft.world.entity.PathfinderMob;
/*     */ import net.minecraft.world.entity.ai.goal.Goal;
/*     */ import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
/*     */ import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ 
/*     */ public class TheEntityKill extends PathfinderMob {
/*     */   public TheEntityKill(EntityType<? extends PathfinderMob> entityType, Level level) {
/*  17 */     super(entityType, level);
/*     */   }
/*     */   
/*     */   private Player player;
/*     */   
/*     */   public TheEntityKill(Player player) {
/*  23 */     this((EntityType<? extends PathfinderMob>)Err422Entities.THE_ENTITY_KILL.get(), player.level());
/*  24 */     this.player = player;
/*  25 */     RandomSource rnd = RandomSource.create();
/*  26 */     int dist = rnd.nextInt(5, 8);
/*  27 */     int angle = rnd.nextInt(360);
/*  28 */     double rad = Math.toRadians(angle);
/*  29 */     double sin = Math.sin(rad) * dist;
/*  30 */     double cos = Math.cos(rad) * dist;
/*  31 */     setPos(player.position().add(sin, 0.0D, cos));
/*  32 */     lookAt(EntityAnchorArgument.Anchor.EYES, player.getEyePosition());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  37 */     super.tick();
/*  38 */     if (!level().isClientSide()) {
/*  39 */       if (this.player != null && this.player.isDeadOrDying()) { remove(Entity.RemovalReason.DISCARDED); }
/*     */       else
/*  41 */       { if (this.player != null && (distanceToSqr((Entity)this.player) > 256.0D || this.random.nextInt(20) == 0)) {
/*  42 */           teleportTo(this.player.getX(), this.player.getY(), this.player.getZ());
/*     */         }
/*  44 */         setTarget((LivingEntity)this.player); }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  51 */     this.goalSelector.addGoal(0, (Goal)new FloatGoal((Mob)this));
/*  52 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.5D, true));
/*  53 */     this.targetSelector.addGoal(1, (Goal)new TargetPlayerGoal((Mob)this, this.player));
/*  54 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomStrollGoal(this, 1.0D));
/*  55 */     this.goalSelector.addGoal(4, (Goal)new LookPlayerGoal((Mob)this, this.player));
/*  56 */     this.goalSelector.addGoal(5, (Goal)new RandomLookAroundGoal((Mob)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldShowName() {
/*  61 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCustomNameVisible() {
/*  65 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasCustomName() {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canBeLeashed() {
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public static class TargetPlayerGoal extends TargetGoal { private final Player player;
/*     */     
/*     */     public TargetPlayerGoal(Mob mob, Player player) {
/*  79 */       super(mob, false);
/*  80 */       this.targetMob = (LivingEntity)player;
/*  81 */       this.player = player;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canUse() {
/*  86 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canContinueToUse() {
/*  91 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void stop() {
/*  96 */       super.stop();
/*  97 */       this.targetMob = (LivingEntity)this.player;
/*     */     } }
/*     */   
/*     */   public static class LookPlayerGoal extends LookAtPlayerGoal { private final Player player;
/*     */     
/*     */     public LookPlayerGoal(Mob mob, Player player) {
/* 103 */       super(mob, Player.class, 1024.0F);
/* 104 */       this.player = player;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canUse() {
/* 109 */       return (this.lookAt != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canContinueToUse() {
/* 114 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void tick() {
/* 119 */       this.lookAt = (Entity)this.player;
/* 120 */       super.tick();
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Component getTypeName() {
/* 126 */     return (Component)Component.literal("ERROR422").withColor(8912896).withStyle(ChatFormatting.OBFUSCATED);
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\entity\TheEntityKill.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */