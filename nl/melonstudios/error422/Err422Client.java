/*     */ package nl.melonstudios.error422;
/*     */ import com.mojang.blaze3d.platform.IconSet;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.multiplayer.ClientLevel;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*     */ import net.minecraft.client.resources.sounds.SimpleSoundInstance;
/*     */ import net.minecraft.client.resources.sounds.SoundInstance;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.packs.repository.PackRepository;
/*     */ import net.minecraft.sounds.SoundEvent;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.neoforged.api.distmarker.Dist;
/*     */ import net.neoforged.bus.api.SubscribeEvent;
/*     */ import net.neoforged.fml.common.EventBusSubscriber;
/*     */ import net.neoforged.fml.util.ObfuscationReflectionHelper;
/*     */ import net.neoforged.neoforge.client.event.ClientTickEvent;
/*     */ import net.neoforged.neoforge.client.event.RenderFrameEvent;
/*     */ import nl.melonstudios.error422.newsys.unsure.BossfightSideEffects;
/*     */ import nl.melonstudios.error422.screen.EndgameCrashScreen;
/*     */ 
/*     */ @EventBusSubscriber(modid = "error422", bus = EventBusSubscriber.Bus.GAME, value = {Dist.CLIENT})
/*     */ public class Err422Client {
/*     */   @SubscribeEvent
/*     */   public static void tick(ClientTickEvent.Post event) {
/*  31 */     if (errScreenDuration > 0) {
/*  32 */       errScreenDuration--;
/*  33 */       Minecraft.getInstance().getSoundManager().play(
/*  34 */           (SoundInstance)SimpleSoundInstance.forUI((SoundEvent)Err422SFX.g
/*  35 */             .get(), 1.0F, 1.0F));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  40 */     if (crashNoiseDuration)
/*  41 */       if (nextSoundCD == 0)
/*  42 */       { Minecraft.getInstance().getSoundManager().play(
/*  43 */             (SoundInstance)SimpleSoundInstance.forUI((SoundEvent)Err422SFX.g422
/*  44 */               .get(), 1.0F, 1.0F));
/*     */ 
/*     */ 
/*     */         
/*  48 */         nextSoundCD = 80; }
/*  49 */       else { nextSoundCD--; }
/*     */        
/*     */   }
/*  52 */   public static int errScreenDuration = 0;
/*     */   public static boolean crashNoiseDuration = false;
/*  54 */   public static int nextSoundCD = 80;
/*     */   
/*  56 */   public static int moveTitleButtonsDownFrames = 0;
/*     */   
/*     */   public static void clientPlayerTick(Player player) {
/*  59 */     if (crashNoiseDuration) player.sendSystemMessage((Component)Component.literal("MCreatorKindaSucksHonestly").withColor(-7864320).withStyle(ChatFormatting.OBFUSCATED)); 
/*     */   }
/*     */   
/*     */   public static void playCrashNoise() {
/*  63 */     crashNoiseDuration = true;
/*  64 */     Minecraft.getInstance().getSoundManager().play(
/*  65 */         (SoundInstance)SimpleSoundInstance.forUI((SoundEvent)Err422SFX.g422
/*  66 */           .get(), 1.0F, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void endgameCrash() {
/*  72 */     crashNoiseDuration = false;
/*  73 */     ClientLevel clientLevel = (Minecraft.getInstance()).level;
/*  74 */     if (clientLevel != null) clientLevel.disconnect(); 
/*  75 */     Minecraft.getInstance().disconnect((Screen)new EndgameCrashScreen());
/*  76 */     if (Err422PhaseManager.bossFightInEffect) {
/*  77 */       Error422.startWorker(BossfightSideEffects::onLose, "BREAKOUT");
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean disableKeyboard() {
/*  82 */     return (errScreenDuration > 0 || (Err422PhaseManager.crashOrKill && crashNoiseDuration));
/*     */   }
/*     */   public static boolean disableScrolling() {
/*  85 */     return (errScreenDuration > 0 || crashNoiseDuration);
/*     */   }
/*     */   public static boolean disableMouseClick() {
/*  88 */     return (errScreenDuration > 0 || crashNoiseDuration);
/*     */   }
/*     */   public static boolean disableMouseMovement() {
/*  91 */     return (errScreenDuration > 0 || (Err422PhaseManager.crashOrKill && crashNoiseDuration));
/*     */   }
/*     */   public static boolean glColorMaskEnabled() {
/*  94 */     return (crashNoiseDuration || (Err422PhaseManager.bossFightInEffect && !Err422PhaseManager.error422Defeat));
/*     */   }
/*  96 */   public static final Field LOCATION_BLOCKS_FIELD = ObfuscationReflectionHelper.findField(TextureAtlas.class, "LOCATION_BLOCKS");
/*  97 */   public static final ResourceLocation BROKEN = ResourceLocation.fromNamespaceAndPath("error422", "textures/gui/icon_48x48.png");
/*  98 */   public static final ResourceLocation FIXED = ResourceLocation.withDefaultNamespace("textures/atlas/blocks.png");
/*     */   static {
/* 100 */     LOCATION_BLOCKS_FIELD.setAccessible(true);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void preFrame(RenderFrameEvent.Pre event) {
/* 105 */     Random rnd = new Random(System.currentTimeMillis() / 500L);
/* 106 */     if (glColorMaskEnabled()) {
/* 107 */       boolean[] mask = maskConfigurations[rnd.nextInt(maskConfigurations.length)];
/* 108 */       RenderSystem.colorMask(mask[0], mask[1], mask[2], mask[3]);
/*     */     } else {
/* 110 */       RenderSystem.colorMask(true, true, true, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void postFrame(RenderFrameEvent.Post event) {}
/*     */   
/* 118 */   private static final boolean[][] maskConfigurations = new boolean[][] { { false, true, true, false }, { true, false, true, false }, { false, true, false, true }, { true, false, false, true } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void tryChangeIcon() {
/* 134 */     Minecraft mc = Minecraft.getInstance();
/*     */     try {
/* 136 */       PackRepository packs = mc.getResourcePackRepository();
/* 137 */       mc.getWindow().setIcon(packs.getPack("mod_resources").open(), IconSet.RELEASE);
/* 138 */     } catch (Exception e) {
/* 139 */       System.err.println("ERR422 icon exception: " + e.getClass().getCanonicalName() + ": " + e.getLocalizedMessage());
/*     */     } 
/*     */   }
/*     */   
/* 143 */   private static final RandomSource rnd = RandomSource.createThreadSafe();
/*     */   public static RandomSource random() {
/* 145 */     return rnd;
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422Client.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */