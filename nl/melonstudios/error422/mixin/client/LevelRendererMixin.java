/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.LevelRenderer;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.joml.Matrix4f;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({LevelRenderer.class})
/*    */ public class LevelRendererMixin {
/*    */   @Inject(method = {"renderSectionLayer"}, at = {@At("HEAD")})
/*    */   public void renderSectionLayer(RenderType renderType, double x, double y, double z, Matrix4f frustrumMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
/* 16 */     if (Err422Client.errScreenDuration > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\LevelRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */