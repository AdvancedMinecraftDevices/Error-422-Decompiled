/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.RenderBuffers;
/*    */ import nl.melonstudios.error422.idk.ErrRenderTypes;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({RenderBuffers.class})
/*    */ public class RenderBuffersMixin {
/*    */   @Inject(method = {"<init>"}, at = {@At("CTOR_HEAD")})
/*    */   public void setStatic(int bufferCount, CallbackInfo ci) {
/* 14 */     ErrRenderTypes.RENDER_BUFFERS = (RenderBuffers)this;
/*    */   }
/*    */   
/*    */   @Inject(method = {"<init>"}, at = {@At("RETURN")})
/*    */   public void removeStatic(int bufferCount, CallbackInfo ci) {
/* 19 */     ErrRenderTypes.RENDER_BUFFERS = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\RenderBuffersMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */