/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import java.net.SocketException;
/*    */ import net.minecraft.client.gui.components.Button;
/*    */ import net.minecraft.client.gui.components.Renderable;
/*    */ import net.minecraft.client.gui.screens.PauseScreen;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import nl.melonstudios.error422.Config;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({PauseScreen.class})
/*    */ public class PauseScreenMixin
/*    */ {
/*    */   @Inject(method = {"createPauseMenu"}, at = {@At("RETURN")})
/*    */   private void createPauseMenu(CallbackInfo ci) {
/* 21 */     if (!Config.enableMultiplayer) {
/* 22 */       Screen screen = (Screen)this;
/* 23 */       for (Renderable renderable : screen.renderables) {
/* 24 */         if (renderable instanceof Button) { Button button = (Button)renderable;
/* 25 */           if (button.getMessage().getString().equals(Component.translatable("menu.shareToLan").getString())) {
/* 26 */             button.active = false;
/* 27 */             button.setMessage((Component)Component.literal(SocketException.class.getCanonicalName()));
/*    */           }  }
/*    */       
/*    */       } 
/*    */     } 
/* 32 */     if (Err422PhaseManager.bossFightInEffect && !Err422PhaseManager.error422Defeat) {
/* 33 */       Screen screen = (Screen)this;
/* 34 */       for (Renderable renderable : screen.renderables) {
/* 35 */         if (renderable instanceof Button) { Button button = (Button)renderable;
/* 36 */           if (button.getMessage().getString().equals(Component.translatable("menu.disconnect").getString())) {
/* 37 */             button.active = false;
/*    */           }
/* 39 */           if (button.getMessage().getString().equals(Component.translatable("menu.returnToMenu").getString()))
/* 40 */             button.active = false;  }
/*    */       
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\PauseScreenMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */