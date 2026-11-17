/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.Window;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import com.mojang.blaze3d.vertex.VertexSorting;
/*    */ import java.util.Objects;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.DeltaTracker;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Font;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.renderer.GameRenderer;
/*    */ import net.minecraft.client.renderer.RenderBuffers;
/*    */ import net.neoforged.neoforge.client.ClientHooks;
/*    */ import nl.melonstudios.error422.Config;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import org.joml.Matrix4f;
/*    */ import org.joml.Matrix4fStack;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ 
/*    */ @Mixin({GameRenderer.class})
/*    */ public class GameRendererMixin {
/*    */   @Shadow
/*    */   @Final
/*    */   Minecraft minecraft;
/*    */   
/*    */   @Inject(method = {"render"}, at = {@At("RETURN")})
/* 33 */   public void render(DeltaTracker deltaTracker, boolean renderLevel, CallbackInfo ci) { RenderSystem.viewport(0, 0, this.minecraft.getWindow().getWidth(), this.minecraft.getWindow().getHeight());
/*    */     
/* 35 */     Window window = this.minecraft.getWindow();
/* 36 */     RenderSystem.clear(256, Minecraft.ON_OSX);
/*    */     
/* 38 */     Matrix4f matrix4f = (new Matrix4f()).setOrtho(0.0F, 
/*    */         
/* 40 */         (float)(window.getWidth() / window.getGuiScale()), 
/* 41 */         (float)(window.getHeight() / window.getGuiScale()), 0.0F, 1000.0F, 
/*    */ 
/*    */         
/* 44 */         ClientHooks.getGuiFarPlane());
/*    */     
/* 46 */     RenderSystem.setProjectionMatrix(matrix4f, VertexSorting.ORTHOGRAPHIC_Z);
/* 47 */     Matrix4fStack matrix4fstack = RenderSystem.getModelViewStack();
/* 48 */     matrix4fstack.pushMatrix();
/* 49 */     matrix4fstack.translation(0.0F, 0.0F, 10000.0F - ClientHooks.getGuiFarPlane());
/* 50 */     RenderSystem.applyModelViewMatrix();
/*    */     
/* 52 */     GuiGraphics guigraphics = new GuiGraphics(this.minecraft, this.renderBuffers.bufferSource());
/*    */     
/* 54 */     if (Err422Client.errScreenDuration > 0) {
/* 55 */       Font font = this.minecraft.font;
/* 56 */       Random rnd = new Random();
/* 57 */       int c = rnd.nextInt(20, 40);
/* 58 */       int ww = (int)(window.getWidth() / window.getGuiScale());
/* 59 */       int wh = (int)(window.getHeight() / window.getGuiScale());
/* 60 */       for (int i = 0; i < c; i++) {
/* 61 */         String txt = this.error422$errors[rnd.nextInt(this.error422$errors.length)];
/* 62 */         int w = font.width(txt) / 2;
/* 63 */         Objects.requireNonNull(font); int h = 9 / 2;
/* 64 */         int x = rnd.nextInt(ww) - w;
/* 65 */         int y = rnd.nextInt(wh) - h;
/* 66 */         guigraphics.drawString(font, txt, x, y, -7864320, false);
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     if (Config.enableVHSOverlay) {
/* 71 */       int ww = (int)(window.getWidth() / window.getGuiScale());
/* 72 */       int wh = (int)(window.getHeight() / window.getGuiScale());
/* 73 */       for (int y = 0; y < wh; y += 2) {
/* 74 */         guigraphics.fill(0, y, ww, y + 1, 1157627903);
/*    */       }
/*    */     } 
/*    */     
/* 78 */     guigraphics.flush();
/* 79 */     matrix4fstack.popMatrix();
/* 80 */     RenderSystem.applyModelViewMatrix(); } @Shadow
/*    */   @Final
/*    */   private RenderBuffers renderBuffers; @Unique
/* 83 */   private final String[] error422$errors = new String[] {
/* 84 */       toString(), NullPointerException.class
/* 85 */       .getCanonicalName(), 
/* 86 */       Minecraft.getInstance().toString(), "null", "§kErrScreenEvent"
/*    */     };
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\GameRendererMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */