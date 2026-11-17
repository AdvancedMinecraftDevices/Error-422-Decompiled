/*     */ package nl.melonstudios.error422.entity;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.commands.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.core.NonNullList;
/*     */ import net.minecraft.nbt.CompoundTag;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.syncher.EntityDataAccessor;
/*     */ import net.minecraft.network.syncher.EntityDataSerializers;
/*     */ import net.minecraft.network.syncher.SynchedEntityData;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.EquipmentSlot;
/*     */ import net.minecraft.world.entity.HumanoidArm;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.item.ItemStack;
/*     */ import net.minecraft.world.level.Level;
/*     */ import nl.melonstudios.error422.Err422PhaseManager;
/*     */ 
/*     */ public class TheEntityCrash extends LivingEntity {
/*  25 */   public static final EntityDataAccessor<Optional<UUID>> UUID_DATA = SynchedEntityData.defineId(TheEntityCrash.class, EntityDataSerializers.OPTIONAL_UUID);
/*     */   public TheEntityCrash(EntityType<? extends LivingEntity> entityType, Level level) {
/*  27 */     super(entityType, level);
/*     */   }
/*     */   
/*     */   private Player player;
/*     */   
/*     */   public TheEntityCrash(Level level, Player player) {
/*  33 */     this((EntityType<? extends LivingEntity>)Err422Entities.THE_ENTITY_CRASH.get(), level);
/*  34 */     this.player = player;
/*  35 */     RandomSource rnd = RandomSource.create();
/*  36 */     int dist = rnd.nextInt(5, 8);
/*  37 */     int angle = rnd.nextInt(360);
/*  38 */     double rad = Math.toRadians(angle);
/*  39 */     double sin = Math.sin(rad) * dist;
/*  40 */     double cos = Math.cos(rad) * dist;
/*  41 */     setPos(player.position().add(sin, 0.0D, cos));
/*  42 */     lookAt(EntityAnchorArgument.Anchor.EYES, player.getEyePosition());
/*  43 */     player.lookAt(EntityAnchorArgument.Anchor.EYES, getEyePosition());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void defineSynchedData(SynchedEntityData.Builder builder) {
/*  48 */     super.defineSynchedData(builder);
/*  49 */     builder.define(UUID_DATA, Optional.empty());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditionalSaveData(CompoundTag compound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<ItemStack> getArmorSlots() {
/*  59 */     return (Iterable<ItemStack>)NonNullList.withSize(4, ItemStack.EMPTY);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemBySlot(EquipmentSlot slot) {
/*  64 */     return ItemStack.EMPTY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemSlot(EquipmentSlot slot, ItemStack stack) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAdditionalSaveData(CompoundTag compound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  79 */     this.animStepO = this.animStep;
/*  80 */     this.yBodyRotO = this.yBodyRot;
/*  81 */     this.yHeadRotO = this.yHeadRot;
/*  82 */     this.yRotO = getYRot();
/*  83 */     this.xRotO = getXRot();
/*  84 */     if (this.player != null && !this.player.isRemoved()) {
/*  85 */       lookAt(EntityAnchorArgument.Anchor.EYES, this.player.getEyePosition());
/*     */     }
/*  87 */     if ((!level().isClientSide() && Err422PhaseManager.getCrashDelay() == 0) || !Err422PhaseManager.isBegunFinale()) remove(Entity.RemovalReason.DISCARDED);
/*     */   
/*     */   }
/*     */   
/*     */   public HumanoidArm getMainArm() {
/*  92 */     return HumanoidArm.RIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldShowName() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCustomNameVisible() {
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomName() {
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Component getTypeName() {
/* 112 */     return (Component)Component.literal("ERROR422").withColor(8912896).withStyle(ChatFormatting.OBFUSCATED);
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\entity\TheEntityCrash.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */