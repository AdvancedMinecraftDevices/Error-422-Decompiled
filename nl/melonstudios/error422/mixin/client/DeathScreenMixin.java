/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.screens.DeathScreen;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({DeathScreen.class})
/*    */ public class DeathScreenMixin {
/*    */   @Unique
/* 21 */   private int err422$cd = -1; @Shadow
/*    */   @Final
/*    */   private List<Button> exitButtons; @Overwrite
/*    */   public void setButtonsActive(boolean active) {
/* 25 */     for (Button button : this.exitButtons) {
/* 26 */       button.active = (active && !Err422Client.crashNoiseDuration);
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"init"}, at = {@At("RETURN")})
/*    */   public void init(CallbackInfo ci) {
/* 32 */     if (Err422PhaseManager.bossFightInEffect) {
/* 33 */       this.err422$cd = 10;
/*    */     }
/*    */   }
/*    */   
/*    */   @Inject(method = {"tick"}, at = {@At("RETURN")})
/*    */   public void tick(CallbackInfo ci) {
/* 39 */     if (this.err422$cd > 0) this.err422$cd--; 
/*    */   }
/*    */   
/*    */   @Inject(method = {"render"}, at = {@At("HEAD")})
/*    */   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
/* 44 */     if (this.err422$cd == 0) {
/* 45 */       this.err422$cd = -1;
/* 46 */       Err422Client.endgameCrash();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\DeathScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */