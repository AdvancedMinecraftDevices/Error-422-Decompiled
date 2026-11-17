/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({AccessibilityOnboardingScreen.class})
/*    */ public class AccessibilityOnboardingScreenMixin
/*    */ {
/*    */   @Overwrite
/*    */   protected void renderPanorama(GuiGraphics guiGraphics, float partialTicks) {
/* 18 */     Error422.renderDefaultBG(guiGraphics, (Screen)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\AccessibilityOnboardingScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */