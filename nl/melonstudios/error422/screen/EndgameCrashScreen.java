/*    */ package nl.melonstudios.error422.screen;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.screens.Screen;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.Music;
/*    */ import net.neoforged.api.distmarker.Dist;
/*    */ import net.neoforged.api.distmarker.OnlyIn;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class EndgameCrashScreen extends Screen {
/*    */   public EndgameCrashScreen() {
/* 18 */     super((Component)Component.literal("ERR422"));
/*    */   }
/*    */   
/* 21 */   private static final ResourceLocation CRASH_LOGO_TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/title/crash_logo.png");
/* 22 */   private static final String[] text = new String[] { "@@@@@ @@@@  @@@@   @@@  @@@@    @ @  @@   @@ ", "@     @   @ @   @ @   @ @   @  @  @ @  @ @  @", "@@@@  @@@@  @@@@  @   @ @@@@  @@@@@   @    @ ", "@     @   @ @   @ @   @ @   @     @  @    @  ", "@@@@@ @   @ @   @  @@@  @   @     @ @@@@ @@@@" };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void init() {
/* 32 */     super.init();
/* 33 */     Err422Client.crashNoiseDuration = false;
/* 34 */     Err422PhaseManager.bossFightInEffect = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
/* 39 */     super.render(guiGraphics, mouseX, mouseY, partialTick);
/* 40 */     int w = this.width;
/* 41 */     int h = this.height;
/* 42 */     guiGraphics.fill(0, 0, w, h, -16777216);
/*    */     
/* 44 */     int yOffset = h / 2 - text.length * 4;
/* 45 */     for (int y = 0; y < text.length; y++) {
/* 46 */       String txt = text[y];
/* 47 */       int i = w / 2 - txt.length() * 4;
/* 48 */       for (int x = 0; x < txt.length(); x++) {
/* 49 */         guiGraphics.drawString(this.font, txt.substring(x, x + 1), x * 8 + i, y * 8 + yOffset, -7864320, false);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 54 */     int xOffset = w / 2 - 160;
/* 55 */     guiGraphics.blit(CRASH_LOGO_TEXTURE, xOffset, 0, 0.0F, 0.0F, 320, 64, 320, 64);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Music getBackgroundMusic() {
/* 62 */     return Error422.SILENCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCloseOnEsc() {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removed() {
/* 72 */     System.exit(422);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\screen\EndgameCrashScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */