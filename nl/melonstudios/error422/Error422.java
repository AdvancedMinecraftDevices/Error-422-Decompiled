/*     */ package nl.melonstudios.error422;
/*     */ import com.mojang.blaze3d.systems.RenderSystem;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Font;
/*     */ import net.minecraft.client.gui.GuiGraphics;
/*     */ import net.minecraft.client.gui.screens.Screen;
/*     */ import net.minecraft.client.renderer.GameRenderer;
/*     */ import net.minecraft.commands.CommandSourceStack;
/*     */ import net.minecraft.commands.Commands;
/*     */ import net.minecraft.commands.SharedSuggestionProvider;
/*     */ import net.minecraft.commands.arguments.ResourceLocationArgument;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.sounds.Music;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.neoforged.api.distmarker.Dist;
/*     */ import net.neoforged.api.distmarker.OnlyIn;
/*     */ import net.neoforged.bus.api.IEventBus;
/*     */ import net.neoforged.bus.api.SubscribeEvent;
/*     */ import net.neoforged.fml.ModContainer;
/*     */ import net.neoforged.fml.common.EventBusSubscriber;
/*     */ import net.neoforged.fml.config.IConfigSpec;
/*     */ import net.neoforged.fml.config.ModConfig;
/*     */ import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
/*     */ import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
/*     */ import net.neoforged.neoforge.common.NeoForge;
/*     */ import net.neoforged.neoforge.event.server.ServerStartingEvent;
/*     */ import nl.melonstudios.error422.events.Err422Events;
/*     */ import nl.melonstudios.error422.newsys.event.EventManager;
/*     */ import nl.melonstudios.error422.newsys.event.GlobalEvent;
/*     */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*     */ import nl.melonstudios.error422.newsys.event.global.TimeChangeEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.ErrScreenEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.FireEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.GlassBreakEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.LightningEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.RandomEffectEvent;
/*     */ import nl.melonstudios.error422.newsys.event.local.ReplaceWaterEvent;
/*     */ import nl.melonstudios.error422.screen.EndgameCrashScreen;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ @Mod("error422")
/*     */ public class Error422 extends Error {
/*  57 */   public static final Logger LOGGER = LoggerFactory.getLogger("ERR422"); public static final String MODID = "error422"; public static Dist dist; private static final SuggestionProvider<CommandSourceStack> SUGGEST_EVENTS_GLOBAL; private static final SuggestionProvider<CommandSourceStack> SUGGEST_EVENTS_LOCAL; public static final int GREEN = 4521796;
/*     */   public static final int ORANGE = 16753920;
/*     */   public static final int RED = 8978312;
/*     */   
/*     */   public Error422(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
/*  62 */     modEventBus.addListener(this::commonSetup);
/*     */     
/*  64 */     NeoForge.EVENT_BUS.register(this);
/*     */     
/*  66 */     Error422.dist = dist;
/*     */     
/*  68 */     Err422SFX.register(modEventBus);
/*  69 */     Err422Events.register(modEventBus);
/*  70 */     Err422Entities.register(modEventBus);
/*     */     
/*  72 */     registerBaseEvents();
/*     */     
/*  74 */     modContainer.registerConfig(ModConfig.Type.COMMON, (IConfigSpec)Config.SPEC);
/*     */   }
/*     */   
/*     */   private void commonSetup(FMLCommonSetupEvent event) {
/*  78 */     EventManager.optimize();
/*     */   }
/*     */   private void registerBaseEvents() {
/*  81 */     EventManager.registerGlobalEvent("error422:time_change", (GlobalEvent)new TimeChangeEvent());
/*  82 */     EventManager.registerGlobalEvent("error422:error_dump", (GlobalEvent)new GlobalErrorDumpEvent());
/*     */     
/*  84 */     EventManager.registerLocalEvent("error422:err_screen", (LocalEvent)new ErrScreenEvent());
/*  85 */     EventManager.registerLocalEvent("error422:error_dump", (LocalEvent)new LocalErrorDumpEvent());
/*  86 */     EventManager.registerLocalEvent("error422:lightning", (LocalEvent)new LightningEvent());
/*  87 */     EventManager.registerLocalEvent("error422:fire", (LocalEvent)new FireEvent());
/*  88 */     EventManager.registerLocalEvent("error422:random_effect", (LocalEvent)new RandomEffectEvent());
/*  89 */     EventManager.registerLocalEvent("error422:glass_break", (LocalEvent)new GlassBreakEvent());
/*  90 */     EventManager.registerLocalEvent("error422:push", (LocalEvent)new PushEvent());
/*  91 */     EventManager.registerLocalEvent("error422:lava", (LocalEvent)new ReplaceWaterEvent());
/*     */   }
/*     */   static {
/*  94 */     SUGGEST_EVENTS_GLOBAL = ((i, j) -> SharedSuggestionProvider.suggestResource(EventManager.GLOBAL_EVENT_REGISTRY.keySet(), j));
/*     */     
/*  96 */     SUGGEST_EVENTS_LOCAL = ((i, j) -> SharedSuggestionProvider.suggestResource(EventManager.LOCAL_EVENT_REGISTRY.keySet(), j));
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onServerStarting(ServerStartingEvent event) {
/* 102 */     LOGGER.info("Boss fight: {}", Boolean.valueOf(Config.enableError422BossFight));
/*     */     
/* 104 */     if (Config.disableAnticheat) {
/* 105 */       error422Command(event.getServer().getCommands().getDispatcher());
/*     */     }
/*     */   }
/*     */   
/*     */   private static void error422Command(CommandDispatcher<CommandSourceStack> dispatcher) {
/* 110 */     dispatcher.register(
/* 111 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("error422").then((
/* 112 */           (LiteralArgumentBuilder)Commands.literal("force_event").then(
/* 113 */             Commands.literal("global").then(
/* 114 */               Commands.argument("id", (ArgumentType)ResourceLocationArgument.id())
/* 115 */               .suggests(SUGGEST_EVENTS_GLOBAL)
/* 116 */               .executes(Error422::forceGlobalEvent))))
/*     */           
/* 118 */           .then(
/* 119 */             Commands.literal("local").then(
/* 120 */               Commands.argument("id", (ArgumentType)ResourceLocationArgument.id())
/* 121 */               .suggests(SUGGEST_EVENTS_LOCAL)
/* 122 */               .executes(Error422::forceLocalEvent)))))
/*     */ 
/*     */         
/* 125 */         .then((
/* 126 */           (LiteralArgumentBuilder)Commands.literal("random_event").then(
/* 127 */             Commands.literal("global").executes(Error422::randomGlobalEvent)))
/*     */ 
/*     */           
/* 130 */           .then(
/* 131 */             Commands.literal("local").executes(Error422::randomLocalEvent))))
/*     */ 
/*     */ 
/*     */         
/* 135 */         .then((
/* 136 */           (LiteralArgumentBuilder)Commands.literal("phase").executes(Error422::showPhase))
/*     */           
/* 138 */           .then(
/* 139 */             Commands.literal("skip").executes(Error422::skipPhase))))
/*     */ 
/*     */ 
/*     */         
/* 143 */         .then(
/* 144 */           (ArgumentBuilder)debugCommandSection()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int forceGlobalEvent(CommandContext<CommandSourceStack> context) {
/* 150 */     ResourceLocation rsl = (ResourceLocation)context.getArgument("id", ResourceLocation.class);
/* 151 */     if (!EventManager.GLOBAL_EVENT_REGISTRY.containsKey(rsl)) {
/* 152 */       ((CommandSourceStack)context.getSource()).sendFailure((Component)Component.literal("Invalid global event key: " + String.valueOf(rsl)));
/* 153 */       return 1;
/*     */     } 
/* 155 */     GlobalEvent event = (GlobalEvent)EventManager.GLOBAL_EVENT_REGISTRY.get(rsl);
/* 156 */     event.occur(((CommandSourceStack)context.getSource()).getLevel());
/* 157 */     return 1;
/*     */   }
/*     */   private static int forceLocalEvent(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 160 */     ResourceLocation rsl = (ResourceLocation)context.getArgument("id", ResourceLocation.class);
/* 161 */     if (!EventManager.LOCAL_EVENT_REGISTRY.containsKey(rsl)) {
/* 162 */       ((CommandSourceStack)context.getSource()).sendFailure((Component)Component.literal("Invalid local event key: " + String.valueOf(rsl)));
/* 163 */       return 1;
/*     */     } 
/* 165 */     LocalEvent event = (LocalEvent)EventManager.LOCAL_EVENT_REGISTRY.get(rsl);
/* 166 */     event.occur(((CommandSourceStack)context.getSource()).getPlayerOrException());
/* 167 */     return 1;
/*     */   }
/*     */   
/*     */   private static int randomGlobalEvent(CommandContext<CommandSourceStack> context) {
/* 171 */     RandomSource rnd = RandomSource.create();
/* 172 */     GlobalEvent event = (GlobalEvent)EventManager.getAllGlobalEvents().get(rnd.nextInt(EventManager.getAllGlobalEvents().size()));
/* 173 */     if (event.requiredPhase <= Err422PhaseManager.phase) {
/* 174 */       event.occur(((CommandSourceStack)context.getSource()).getLevel());
/*     */     }
/* 176 */     ((CommandSourceStack)context.getSource()).sendSuccess(() -> Component.literal("Random global event"), true);
/* 177 */     return 1;
/*     */   }
/*     */   private static int randomLocalEvent(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
/* 180 */     RandomSource rnd = RandomSource.create();
/* 181 */     LocalEvent event = (LocalEvent)EventManager.getAllLocalEvents().get(rnd.nextInt(EventManager.getAllLocalEvents().size()));
/* 182 */     if (event.requiredPhase <= Err422PhaseManager.phase) {
/* 183 */       event.occur(((CommandSourceStack)context.getSource()).getPlayerOrException());
/*     */     }
/* 185 */     ((CommandSourceStack)context.getSource()).sendSuccess(() -> Component.literal("Random local event"), true);
/* 186 */     return 1;
/*     */   }
/*     */   
/*     */   private static int showPhase(CommandContext<CommandSourceStack> context) {
/* 190 */     ((CommandSourceStack)context.getSource()).sendSuccess(() -> Component.literal(String.format("Current phase: %s; cooldown: %s", new Object[] { Integer.valueOf(Err422PhaseManager.phase), Integer.valueOf(Err422PhaseManager.phaseCooldown) })), false);
/*     */     
/* 192 */     return 1;
/*     */   }
/*     */   private static int skipPhase(CommandContext<CommandSourceStack> context) {
/* 195 */     if (Err422PhaseManager.phase == 3) {
/* 196 */       ((CommandSourceStack)context.getSource()).sendFailure((Component)Component.literal("Max phase already reached"));
/* 197 */       return 1;
/*     */     } 
/* 199 */     Err422PhaseManager.nextPhase();
/* 200 */     ((CommandSourceStack)context.getSource()).sendSuccess(() -> Component.literal("Skipped to phase " + Err422PhaseManager.phase), true);
/* 201 */     return 1;
/*     */   }
/*     */   
/*     */   private static LiteralArgumentBuilder<CommandSourceStack> debugCommandSection() {
/* 205 */     return (LiteralArgumentBuilder<CommandSourceStack>)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("debug").then((
/* 206 */         (LiteralArgumentBuilder)Commands.literal("applicable_events").then(
/* 207 */           Commands.literal("global").executes(context -> {
/*     */               List<GlobalEvent> events = EventManager.getAvailableGlobalEvents(Err422PhaseManager.phase);
/*     */ 
/*     */               
/*     */               if (events.isEmpty()) {
/*     */                 ((CommandSourceStack)context.getSource()).sendSuccess((), false);
/*     */               } else {
/*     */                 ((CommandSourceStack)context.getSource()).sendSuccess((), false);
/*     */                 
/*     */                 events.forEach(());
/*     */               } 
/*     */               
/*     */               return 1;
/* 220 */             }))).then(
/* 221 */           Commands.literal("local").executes(context -> {
/*     */               List<LocalEvent> events = EventManager.getAvailableLocalEvents(Err422PhaseManager.phase);
/*     */ 
/*     */               
/*     */               if (events.isEmpty()) {
/*     */                 ((CommandSourceStack)context.getSource()).sendSuccess((), false);
/*     */               } else {
/*     */                 ((CommandSourceStack)context.getSource()).sendSuccess((), false);
/*     */ 
/*     */                 
/*     */                 events.forEach(());
/*     */               } 
/*     */               
/*     */               return 1;
/* 235 */             })))).then(
/* 236 */         Commands.literal("my_event_trackers").executes(context -> {
/*     */             ServerPlayer player = ((CommandSourceStack)context.getSource()).getPlayerOrException();
/*     */             
/*     */             EventManager.manager().getDebugForPlayer(player).forEach(());
/*     */             
/*     */             return 1;
/* 242 */           }))).then(
/* 243 */         Commands.literal("global_event_trackers").executes(context -> {
/*     */             ServerLevel level = ((CommandSourceStack)context.getSource()).getLevel();
/*     */             EventManager.manager().getDebugForLevel(level).forEach(());
/*     */             return 1;
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "error422", bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
/*     */   public static class ClientModEvents
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void onClientSetup(FMLClientSetupEvent event) {
/* 260 */       if (Error422.dist.isClient()) Err422Client.tryChangeIcon(); 
/* 261 */       BreakoutCheck.checkForExe();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void startWorker(Runnable runnable, String thread) {
/* 266 */     Thread t = new Thread(runnable);
/* 267 */     t.setName(thread);
/* 268 */     t.start();
/*     */   }
/*     */   
/* 271 */   public static final Music MENU_MUSIC_REPLACEMENT = new Music((Holder)Err422SFX.th, 0, 0, true);
/* 272 */   public static final Music DEFAULT_MUSIC_REPLACEMENT = new Music((Holder)Err422SFX.n, 0, 0, true);
/* 273 */   public static final Music SILENCE = new Music((Holder)Err422SFX.s, 100, 100, true);
/* 274 */   public static final Music BOSSFIGHT_OST = new Music((Holder)Err422SFX.te, 0, 0, true);
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void renderDefaultBG(GuiGraphics guiGraphics, Screen screen) {
/* 278 */     Random random = new Random(System.currentTimeMillis() / 500L);
/* 279 */     RenderSystem.enableBlend();
/* 280 */     RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
/* 281 */     guiGraphics.setColor(0.25F, 0.25F, 0.25F, 1.0F);
/* 282 */     guiGraphics.blit(ResourceLocation.withDefaultNamespace("textures/block/bedrock.png"), 0, 0, 0.0F, 0.0F, screen.width, screen.height, 16, 16);
/*     */     
/* 284 */     Font font = (Minecraft.getInstance()).font;
/* 285 */     RenderSystem.disableDepthTest();
/* 286 */     RenderSystem.depthMask(false);
/* 287 */     guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 288 */     for (int x = 0; x < screen.width; x += 7) {
/* 289 */       for (int y = 0; y < screen.height; y += 9) {
/* 290 */         guiGraphics.drawString(font, get_random_char(random), x, y, random.nextInt(16777215) | 0xFF000000, false);
/*     */       }
/*     */     } 
/* 293 */     RenderSystem.depthMask(true);
/* 294 */     RenderSystem.disableBlend();
/* 295 */     RenderSystem.disableDepthTest();
/*     */   }
/*     */   private static String get_random_char(Random random) {
/* 298 */     if (random.nextBoolean()) {
/* 299 */       return String.valueOf((char)random.nextInt(65, 91));
/*     */     }
/* 301 */     return String.valueOf((char)random.nextInt(97, 123));
/*     */   }
/*     */   
/* 304 */   private static int maxStackSize = RandomSource.create().nextInt(1, 100);
/*     */   public static int getMaxStackSize() {
/* 306 */     return maxStackSize;
/*     */   }
/*     */   public static void setMaxStackSize(int size) {
/* 309 */     maxStackSize = size;
/*     */   }
/*     */   
/*     */   public static String hexName(String str) {
/* 313 */     if (str.isEmpty()) return "0"; 
/* 314 */     StringBuilder builder = new StringBuilder();
/* 315 */     for (char c : str.toCharArray()) {
/* 316 */       builder.append(Integer.toHexString(c));
/*     */     }
/* 318 */     return builder.toString();
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void crashWithError422_client() {
/* 323 */     Minecraft.getInstance().disconnect((Screen)new EndgameCrashScreen());
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Error422.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */