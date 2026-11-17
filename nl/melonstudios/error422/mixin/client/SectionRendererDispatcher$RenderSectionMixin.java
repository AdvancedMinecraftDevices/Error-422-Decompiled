/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({SectionRenderDispatcher.RenderSection.class})
/*    */ public class SectionRendererDispatcher$RenderSectionMixin {
/*    */   @Inject(method = {"getOrigin"}, at = {@At("HEAD")})
/*    */   public void getOrigin(CallbackInfoReturnable<BlockPos> cir) {
/* 17 */     if (Err422Client.errScreenDuration > 0) {
/* 18 */       RandomSource rnd = RandomSource.create();
/* 19 */       RenderSystem.setShaderColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat() * 0.5F + 0.5F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\SectionRendererDispatcher$RenderSectionMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */