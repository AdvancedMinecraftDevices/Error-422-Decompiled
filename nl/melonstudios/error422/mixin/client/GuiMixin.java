/*     */ package nl.melonstudios.error422.mixin.client;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import net.minecraft.client.DeltaTracker;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.food.FoodData;
/*     */ import nl.melonstudios.error422.Err422Client;
/*     */ import nl.melonstudios.error422.Err422PhaseManager;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Overwrite;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ 
/*     */ @Mixin({Gui.class})
/*     */ public abstract class GuiMixin {
/*     */   @Unique
/*  23 */   private final RandomSource random = RandomSource.createThreadSafe();
/*     */   
/*     */   @Shadow
/*     */   @Final
/*     */   private static ResourceLocation FOOD_EMPTY_HUNGER_SPRITE;
/*     */   
/*     */   @Shadow
/*     */   @Final
/*     */   private static ResourceLocation FOOD_HALF_HUNGER_SPRITE;
/*     */   
/*     */   @Shadow
/*     */   @Final
/*     */   private static ResourceLocation FOOD_FULL_HUNGER_SPRITE;
/*     */   @Shadow
/*     */   @Final
/*     */   private static ResourceLocation FOOD_EMPTY_SPRITE;
/*     */   @Shadow
/*     */   @Final
/*     */   private static ResourceLocation FOOD_HALF_SPRITE;
/*     */   
/*     */   @Overwrite
/*     */   public void renderHeart(GuiGraphics guiGraphics, Gui.HeartType heartType, int x, int y, boolean hardcore, boolean halfHeart, boolean blinking) {
/*  45 */     int w = guiGraphics.guiWidth() / 2;
/*  46 */     int mx = w - 91;
/*  47 */     int tx = w + 91;
/*  48 */     RenderSystem.enableBlend();
/*  49 */     guiGraphics.blitSprite(heartType.getSprite(hardcore, blinking, halfHeart), this.random.nextInt(mx, tx), y, 9, 9);
/*  50 */     RenderSystem.disableBlend(); } @Shadow @Final private static ResourceLocation FOOD_FULL_SPRITE; @Shadow
/*     */   private int tickCount; @Shadow
/*     */   @Final
/*     */   private Minecraft minecraft; @Shadow
/*     */   public abstract Font getFont(); @Shadow
/*     */   protected abstract boolean isExperienceBarVisible(); @Overwrite
/*  56 */   public void renderFood(GuiGraphics guiGraphics, Player player, int y, int x) { FoodData fooddata = player.getFoodData();
/*  57 */     int i = fooddata.getFoodLevel();
/*  58 */     RenderSystem.enableBlend();
/*  59 */     int w = guiGraphics.guiWidth() / 2;
/*  60 */     int mx = w - 91;
/*  61 */     int tx = w + 91;
/*     */     
/*  63 */     for (int j = 0; j < 10; j++) {
/*  64 */       ResourceLocation resourcelocation, resourcelocation1, resourcelocation2; int k = y;
/*     */ 
/*     */ 
/*     */       
/*  68 */       if (player.hasEffect(MobEffects.HUNGER)) {
/*  69 */         resourcelocation = FOOD_EMPTY_HUNGER_SPRITE;
/*  70 */         resourcelocation1 = FOOD_HALF_HUNGER_SPRITE;
/*  71 */         resourcelocation2 = FOOD_FULL_HUNGER_SPRITE;
/*     */       } else {
/*  73 */         resourcelocation = FOOD_EMPTY_SPRITE;
/*  74 */         resourcelocation1 = FOOD_HALF_SPRITE;
/*  75 */         resourcelocation2 = FOOD_FULL_SPRITE;
/*     */       } 
/*     */       
/*  78 */       if (player.getFoodData().getSaturationLevel() <= 0.0F && this.tickCount % (i * 3 + 1) == 0) {
/*  79 */         k = y + this.random.nextInt(3) - 1;
/*     */       }
/*     */       
/*  82 */       int l = x - j * 8 - 9;
/*  83 */       guiGraphics.blitSprite(resourcelocation, this.random.nextInt(mx, tx), k, 9, 9);
/*  84 */       if (j * 2 + 1 < i) {
/*  85 */         guiGraphics.blitSprite(resourcelocation2, this.random.nextInt(mx, tx), k, 9, 9);
/*     */       }
/*     */       
/*  88 */       if (j * 2 + 1 == i) {
/*  89 */         guiGraphics.blitSprite(resourcelocation1, this.random.nextInt(mx, tx), k, 9, 9);
/*     */       }
/*     */     } 
/*     */     
/*  93 */     RenderSystem.disableBlend(); }
/*     */ 
/*     */ 
/*     */   
/*     */   @Overwrite
/*     */   public void renderAirLevel(GuiGraphics guiGraphics) {}
/*     */   
/*     */   @Overwrite
/*     */   public void renderExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
/* 102 */     int i = this.minecraft.player.experienceLevel;
/* 103 */     if (isExperienceBarVisible() && i > 0) {
/* 104 */       this.minecraft.getProfiler().push("expLevel");
/* 105 */       String s = Integer.toBinaryString(i);
/* 106 */       int j = (guiGraphics.guiWidth() - getFont().width(s)) / 2;
/* 107 */       int k = guiGraphics.guiHeight() - 31 - 4;
/* 108 */       guiGraphics.drawString(getFont(), s, j + 1, k, 0, false);
/* 109 */       guiGraphics.drawString(getFont(), s, j - 1, k, 0, false);
/* 110 */       guiGraphics.drawString(getFont(), s, j, k + 1, 0, false);
/* 111 */       guiGraphics.drawString(getFont(), s, j, k - 1, 0, false);
/* 112 */       guiGraphics.drawString(getFont(), s, j, k, 8453920, false);
/* 113 */       this.minecraft.getProfiler().pop();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Inject(method = {"onDisconnected"}, at = {@At("RETURN")})
/*     */   public void onDisconnected() {
/* 119 */     if (Err422PhaseManager.bossFightInEffect)
/* 120 */       Err422Client.endgameCrash(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\GuiMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */