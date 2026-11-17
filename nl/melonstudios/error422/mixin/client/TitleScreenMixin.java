/*     */ package nl.melonstudios.error422.mixin.client;
/*     */ 
/*     */ import com.mojang.realmsclient.gui.screens.RealmsNotificationsScreen;
/*     */ import java.net.SocketException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.SharedConstants;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.components.Button;
/*     */ import net.minecraft.client.gui.components.Renderable;
/*     */ import net.minecraft.client.gui.components.SplashRenderer;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.gui.screens.TitleScreen;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import nl.melonstudios.error422.Config;
/*     */ import nl.melonstudios.error422.Err422Client;
/*     */ import nl.melonstudios.error422.Error422;
/*     */ import nl.melonstudios.error422.newsys.BreakoutCheck;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Overwrite;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.Unique;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({TitleScreen.class})
/*     */ public abstract class TitleScreenMixin
/*     */ {
/*     */   @Shadow
/*     */   private boolean fading;
/*     */   
/*     */   @Inject(method = {"init"}, at = {@At("RETURN")})
/*     */   public void init(CallbackInfo ci) {
/*  40 */     this.realmsNotificationsScreen = null;
/*     */     
/*  42 */     TitleScreen screen = (TitleScreen)this;
/*     */     
/*  44 */     for (Renderable renderable : screen.renderables) {
/*  45 */       if (renderable instanceof Button) { Button button = (Button)renderable;
/*  46 */         this.original_button_positions.put(button, Integer.valueOf(button.getY()));
/*  47 */         if (button.getMessage().getString().equals(Component.translatable("menu.quit").getString())) {
/*  48 */           button.active = false;
/*     */         }
/*  50 */         if (button.getMessage().getString().equals(Component.translatable("menu.multiplayer").getString()) && 
/*  51 */           !Config.enableMultiplayer) {
/*  52 */           button.active = false;
/*  53 */           button.setMessage((Component)Component.literal(SocketException.class.getCanonicalName()));
/*     */         } 
/*     */         
/*  56 */         if (button.getMessage().getString().equals(Component.translatable("menu.online").getString())) {
/*  57 */           button.active = false;
/*  58 */           button.setMessage((Component)Component.literal(NullPointerException.class.getCanonicalName()));
/*     */         } 
/*  60 */         if (button.getMessage().getString().equals(Component.translatable("options.accessibility").getString())) {
/*  61 */           button.active = false;
/*  62 */           button.visible = false;
/*     */         } 
/*  64 */         if (button.getMessage().getString().equals(Component.translatable("options.language").getString())) {
/*  65 */           button.active = false;
/*  66 */           button.visible = false;
/*     */         }  }
/*     */     
/*     */     } 
/*     */     
/*  71 */     this.splash = null;
/*     */   }
/*     */   @Shadow
/*     */   @Nullable
/*     */   private SplashRenderer splash; @Shadow
/*     */   private RealmsNotificationsScreen realmsNotificationsScreen;
/*     */   
/*     */   @Overwrite
/*     */   protected void renderPanorama(GuiGraphics guiGraphics, float partialTick) {
/*  80 */     Error422.renderDefaultBG(guiGraphics, (Screen)this);
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("RETURN")})
/*     */   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
/*  86 */     Minecraft mc = Minecraft.getInstance();
/*  87 */     TitleScreen screen = (TitleScreen)this;
/*  88 */     String s = "Minecraft " + SharedConstants.getCurrentVersion().getName();
/*  89 */     if (mc.isDemo()) {
/*  90 */       s = s + " Demo";
/*     */     } else {
/*  92 */       s = s + s;
/*     */     } 
/*     */     
/*  95 */     if (Minecraft.checkModStatus().shouldReportAsModified()) {
/*  96 */       s = s + s;
/*     */     }
/*     */     
/*  99 */     Objects.requireNonNull(mc.font); guiGraphics.drawString(mc.font, "§m" + s + "§r §4ERR422", 2, screen.height - 10 + 9 + 1, -1);
/* 100 */     if (BreakoutCheck.displaysMessage()) {
/* 101 */       String msg = BreakoutCheck.getError();
/* 102 */       guiGraphics.drawString(mc.font, msg, 3, 2, 0, false);
/* 103 */       guiGraphics.drawString(mc.font, msg, 1, 2, 0, false);
/* 104 */       guiGraphics.drawString(mc.font, msg, 2, 3, 0, false);
/* 105 */       guiGraphics.drawString(mc.font, msg, 2, 1, 0, false);
/* 106 */       int red = error422$calculateRedness();
/* 107 */       int col = 0xFFFF0000 | red | red << 8;
/* 108 */       guiGraphics.drawString(mc.font, msg, 2, 2, col, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Unique
/*     */   private static int error422$calculateRedness() {
/* 114 */     long tiems = System.currentTimeMillis() % 2000L;
/* 115 */     boolean decrease = (tiems < 1000L);
/* 116 */     int strippedTime = (int)tiems % 1000;
/* 117 */     int to0xFF = (int)(strippedTime / 1000.0F * 255.0F) & 0xFF;
/* 118 */     return decrease ? (255 - to0xFF) : to0xFF;
/*     */   }
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("HEAD")})
/*     */   public void renderFirst(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
/* 123 */     TitleScreen screen = (TitleScreen)this;
/* 124 */     for (Renderable renderable : screen.renderables) {
/* 125 */       if (renderable instanceof Button) { Button button = (Button)renderable; if (!(renderable instanceof net.minecraft.client.gui.components.PlainTextButton)) {
/* 126 */           button.setY(((Integer)this.original_button_positions.get(button)).intValue() + ((Err422Client.moveTitleButtonsDownFrames > 0) ? 100 : 0));
/*     */         } }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   @Unique
/* 133 */   private final Map<Button, Integer> original_button_positions = new HashMap<>();
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\TitleScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */