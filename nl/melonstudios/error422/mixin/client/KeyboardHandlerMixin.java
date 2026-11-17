/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.KeyboardHandler;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({KeyboardHandler.class})
/*    */ public class KeyboardHandlerMixin
/*    */ {
/*    */   @Inject(method = {"keyPress"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void keyPress(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
/* 15 */     if (Err422Client.disableKeyboard()) ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\KeyboardHandlerMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */