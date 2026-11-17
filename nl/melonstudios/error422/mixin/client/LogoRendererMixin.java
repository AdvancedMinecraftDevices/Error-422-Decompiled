/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.LogoRenderer;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ 
/*    */ @Mixin({LogoRenderer.class})
/*    */ public class LogoRendererMixin {
/*    */   @Unique
/* 16 */   private static final Random error422$random = new Random();
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   public static ResourceLocation MINECRAFT_LOGO;
/*    */   
/*    */   @Overwrite
/*    */   public void renderLogo(GuiGraphics guiGraphics, int screenWidth, float transparency, int height) {
/* 24 */     boolean down = (Err422Client.moveTitleButtonsDownFrames > 0);
/* 25 */     guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 26 */     RenderSystem.enableBlend();
/* 27 */     int x = (error422$random.nextFloat() < 0.01F) ? 5 : 0;
/* 28 */     int y = (down || error422$random.nextFloat() < 0.005F) ? 100 : 0;
/* 29 */     int i = screenWidth / 2 - 160;
/* 30 */     guiGraphics.blit(MINECRAFT_LOGO, i + x, height + y - 10, 0.0F, 0.0F, 320, 80, 320, 80);
/* 31 */     guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 32 */     RenderSystem.disableBlend();
/* 33 */     if (down) { Err422Client.moveTitleButtonsDownFrames--; }
/* 34 */     else if (y != 0) { Err422Client.moveTitleButtonsDownFrames = 3; }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\LogoRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */