/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.MouseHandler;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({MouseHandler.class})
/*    */ public class MouseHandlerMixin {
/*    */   @Inject(method = {"onPress"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onPress(long windowPointer, int button, int action, int modifiers, CallbackInfo ci) {
/* 14 */     if (Err422Client.disableMouseClick()) ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"onScroll"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onScroll(long windowPointer, double xOffset, double yOffset, CallbackInfo ci) {
/* 19 */     if (Err422Client.disableScrolling()) ci.cancel(); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"onMove"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onMove(long windowPointer, double xpos, double ypos, CallbackInfo ci) {
/* 24 */     if (Err422Client.disableMouseMovement()) ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\MouseHandlerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */