/*    */ package nl.melonstudios.error422;
/*    */ 
/*    */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public class Err422Comps {
/*  8 */   public static final ResourceLocation BLOCKS_ATLAS_RSL = TextureAtlas.LOCATION_BLOCKS;
/*    */   
/*    */   public static boolean undefined(String text) {
/* 11 */     if (text.equals(telemetryData())) return true; 
/* 12 */     if (text.equals(creditsAttribution())) return true; 
/* 13 */     if (text.equals(chatSettings())) return true; 
/* 14 */     if (text.equals(accessibilitySettings())) return true; 
/* 15 */     if (text.startsWith(hideLightningFlashes())) return true; 
/* 16 */     if (text.startsWith(backgroundBlurriness())) return true; 
/* 17 */     if (text.startsWith(panoramaSpeed())) return true; 
/* 18 */     if (text.startsWith(screenEffect())) return true; 
/* 19 */     if (text.startsWith(damageTilt())) return true; 
/* 20 */     if (text.startsWith(guiScale())) return true; 
/* 21 */     if (text.startsWith(chunkBuilder())) return true; 
/* 22 */     if (text.startsWith(smoothLight())) return true; 
/* 23 */     if (text.startsWith(vsync())) return true; 
/* 24 */     return false;
/*    */   }
/*    */   
/* 27 */   public static final Component CHUNK_BUILDER = (Component)Component.translatable("options.prioritizeChunkUpdates");
/* 28 */   public static final Component SMOOTH_LIGHT = (Component)Component.translatable("options.ao");
/* 29 */   public static final Component VSYNC = (Component)Component.translatable("options.vsync");
/*    */   
/*    */   public static String chunkBuilder() {
/* 32 */     return CHUNK_BUILDER.getString();
/*    */   }
/*    */   public static String smoothLight() {
/* 35 */     return SMOOTH_LIGHT.getString();
/*    */   }
/*    */   public static String vsync() {
/* 38 */     return VSYNC.getString();
/*    */   }
/*    */   
/* 41 */   public static final Component TELEMETRY_DATA = (Component)Component.translatable("options.telemetry");
/* 42 */   public static final Component CREDITS_ATTRIBUTION = (Component)Component.translatable("options.credits_and_attribution");
/* 43 */   public static final Component CHAT_SETTINGS = (Component)Component.translatable("options.chat");
/* 44 */   public static final Component ACCESSIBILITY_SETTINGS = (Component)Component.translatable("options.accessibility");
/*    */   
/*    */   public static String telemetryData() {
/* 47 */     return TELEMETRY_DATA.getString();
/*    */   }
/*    */   public static String creditsAttribution() {
/* 50 */     return CREDITS_ATTRIBUTION.getString();
/*    */   }
/*    */   public static String chatSettings() {
/* 53 */     return CHAT_SETTINGS.getString();
/*    */   }
/*    */   public static String accessibilitySettings() {
/* 56 */     return ACCESSIBILITY_SETTINGS.getString();
/*    */   }
/*    */   
/* 59 */   public static final Component HIDE_LIGHTNING_FLASHES = (Component)Component.translatable("options.hideLightningFlashes");
/* 60 */   public static final Component BACKGROUND_BLURRINESS = (Component)Component.translatable("options.accessibility.menu_background_blurriness");
/* 61 */   public static final Component PANORAMA_SPEED = (Component)Component.translatable("options.accessibility.panorama_speed");
/* 62 */   public static final Component SCREEN_EFFECT = (Component)Component.translatable("options.screenEffectScale");
/* 63 */   public static final Component DAMAGE_TILT = (Component)Component.translatable("options.damageTiltStrength");
/* 64 */   public static final Component GUI_SCALE = (Component)Component.translatable("options.guiScale");
/*    */   
/*    */   public static String hideLightningFlashes() {
/* 67 */     return HIDE_LIGHTNING_FLASHES.getString();
/*    */   }
/*    */   public static String backgroundBlurriness() {
/* 70 */     return BACKGROUND_BLURRINESS.getString();
/*    */   }
/*    */   public static String panoramaSpeed() {
/* 73 */     return PANORAMA_SPEED.getString();
/*    */   }
/*    */   public static String screenEffect() {
/* 76 */     return SCREEN_EFFECT.getString();
/*    */   }
/*    */   public static String damageTilt() {
/* 79 */     return DAMAGE_TILT.getString();
/*    */   }
/*    */   public static String guiScale() {
/* 82 */     return GUI_SCALE.getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422Comps.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */