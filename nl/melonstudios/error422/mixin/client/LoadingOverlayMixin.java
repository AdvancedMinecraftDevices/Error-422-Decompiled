/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ import net.minecraft.client.gui.screens.LoadingOverlay;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ @Mixin({LoadingOverlay.class})
/*    */ public class LoadingOverlayMixin {
/*    */   @Shadow
/*    */   @Final
/*    */   @Mutable
/* 13 */   static ResourceLocation MOJANG_STUDIOS_LOGO_LOCATION = ResourceLocation.fromNamespaceAndPath("error422", "textures/gui/mojangstudios.png");
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\LoadingOverlayMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */