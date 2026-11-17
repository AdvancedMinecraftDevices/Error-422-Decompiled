/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import nl.melonstudios.error422.Config;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import nl.melonstudios.error422.idk.ErrRenderTypes;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Mutable;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({RenderType.class})
/*    */ public abstract class RenderTypeMixin {
/*    */   @Inject(method = {"solid"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private static void solid(CallbackInfoReturnable<RenderType> cir) {
/* 20 */     if (Config.advancedRender && Err422Client.errScreenDuration > 0) cir.setReturnValue(ErrRenderTypes.REPLACEMENT); 
/*    */   }
/*    */   @Inject(method = {"cutout"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private static void cutout(CallbackInfoReturnable<RenderType> cir) {
/* 24 */     if (Config.advancedRender && Err422Client.errScreenDuration > 0) cir.setReturnValue(ErrRenderTypes.REPLACEMENT); 
/*    */   }
/*    */   @Inject(method = {"cutoutMipped"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private static void cutoutMipped(CallbackInfoReturnable<RenderType> cir) {
/* 28 */     if (Config.advancedRender && Err422Client.errScreenDuration > 0) cir.setReturnValue(ErrRenderTypes.REPLACEMENT); 
/*    */   }
/*    */   
/*    */   @Inject(method = {"translucent"}, at = {@At("RETURN")}, cancellable = true)
/* 32 */   private static void translucent(CallbackInfoReturnable<RenderType> cir) { if (Config.advancedRender && Err422Client.errScreenDuration > 0) cir.setReturnValue(ErrRenderTypes.REPLACEMENT);  } @Shadow
/*    */   @Final
/*    */   @Mutable
/* 35 */   public static ImmutableList<RenderType> CHUNK_BUFFER_LAYERS = ImmutableList.of(
/* 36 */       RenderType.solid(), 
/* 37 */       RenderType.cutoutMipped(), 
/* 38 */       RenderType.cutout(), 
/* 39 */       RenderType.translucent(), 
/* 40 */       RenderType.tripwire(), ErrRenderTypes.REPLACEMENT);
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\RenderTypeMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */