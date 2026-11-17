/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Screen.class})
/*    */ public class ScreenMixin
/*    */ {
/*    */   @Overwrite
/*    */   protected void renderPanorama(GuiGraphics guiGraphics, float partialTicks) {
/* 22 */     Error422.renderDefaultBG(guiGraphics, (Screen)this);
/*    */   }
/*    */   
/*    */   @Inject(method = {"renderBlurredBackground"}, at = {@At("HEAD")}, cancellable = true)
/*    */   protected void renderBlurredBackground(float partialTick, CallbackInfo ci) {
/* 27 */     ci.cancel();
/*    */   }
/*    */   
/*    */   @Inject(method = {"isPauseScreen"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void isPauseScreen(CallbackInfoReturnable<Boolean> cir) {
/* 32 */     if (Err422PhaseManager.bossFightInEffect) {
/* 33 */       cir.cancel();
/* 34 */       cir.setReturnValue(Boolean.valueOf(false));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\ScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */