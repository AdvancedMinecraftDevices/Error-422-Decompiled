/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiGraphics;
/*    */ import net.minecraft.client.gui.components.AbstractButton;
/*    */ import net.minecraft.client.gui.components.AbstractWidget;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import nl.melonstudios.error422.Config;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ 
/*    */ @Mixin({AbstractButton.class})
/*    */ public abstract class AbstractButtonMixin {
/*    */   @Unique
/* 19 */   private static final Component UNDEFINED = (Component)Component.translatable("undefined"); @Unique
/* 20 */   private static final Random shaky_random = new Random();
/*    */   
/*    */   @Shadow
/*    */   @Final
/*    */   protected static WidgetSprites SPRITES;
/*    */ 
/*    */   
/*    */   @Shadow
/*    */   public abstract void renderString(GuiGraphics paramGuiGraphics, Font paramFont, int paramInt);
/*    */   
/*    */   @Overwrite
/*    */   protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
/* 32 */     AbstractButton button = (AbstractButton)this;
/* 33 */     if (Config.undefinedOptions && Err422Comps.undefined(button.getMessage().getString())) {
/* 34 */       button.active = false;
/* 35 */       button.setMessage(UNDEFINED);
/*    */     } 
/* 37 */     Random random = new Random(hashCode());
/* 38 */     boolean isQuitButton = button.getMessage().getString().equals(Component.translatable("menu.quit").getString());
/* 39 */     boolean isShaky = (button.isHoveredOrFocused() && !isQuitButton);
/* 40 */     boolean textShaky = (isShaky && button.active);
/* 41 */     Minecraft minecraft = Minecraft.getInstance();
/* 42 */     if (isQuitButton) { guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F); }
/*    */     else
/* 44 */     { Random rnd = textShaky ? shaky_random : random;
/* 45 */       guiGraphics.setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1.0F); }
/*    */     
/* 47 */     RenderSystem.enableBlend();
/* 48 */     RenderSystem.enableDepthTest();
/* 49 */     int x = isShaky ? (button.getX() + shaky_random.nextInt(6) - 3) : button.getX();
/* 50 */     int y = isShaky ? (button.getY() + shaky_random.nextInt(6) - 3) : button.getY();
/* 51 */     guiGraphics.blitSprite(SPRITES.get(button.active, button.isHoveredOrFocused()), x, y, button.getWidth(), button.getHeight());
/* 52 */     guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 53 */     int i = isQuitButton ? 16711680 : (textShaky ? (0xFF000000 | shaky_random.nextInt(16777215)) : button.getFGColor());
/*    */     
/* 55 */     int k = button.getX() + 2 + (isShaky ? (shaky_random.nextInt(3) - 1) : 0);
/* 56 */     int j = k + button.getWidth() - 2;
/* 57 */     int h = button.getY() + (isShaky ? (shaky_random.nextInt(3) - 1) : 0);
/* 58 */     AbstractWidget.renderScrollingString(guiGraphics, minecraft.font, button.getMessage(), k, h, j, h + button
/* 59 */         .getHeight(), i);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\AbstractButtonMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */