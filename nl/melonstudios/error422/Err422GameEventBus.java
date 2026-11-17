/*     */ package nl.melonstudios.error422;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.neoforged.bus.api.SubscribeEvent;
/*     */ import net.neoforged.fml.common.EventBusSubscriber;
/*     */ import net.neoforged.neoforge.event.entity.player.PlayerEvent;
/*     */ import net.neoforged.neoforge.event.server.ServerStartingEvent;
/*     */ import net.neoforged.neoforge.event.tick.PlayerTickEvent;
/*     */ import net.neoforged.neoforge.event.tick.ServerTickEvent;
/*     */ import net.neoforged.neoforge.network.PacketDistributor;
/*     */ import nl.melonstudios.error422.events.EventErrScreen;
/*     */ import nl.melonstudios.error422.events.SpookyEvent;
/*     */ import nl.melonstudios.error422.net.PacketCrashGame;
/*     */ import nl.melonstudios.error422.net.PacketSyncStackSize;
/*     */ import nl.melonstudios.error422.net.PacketUpdatePhaseState;
/*     */ import nl.melonstudios.error422.newsys.event.EventManager;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "error422", bus = EventBusSubscriber.Bus.GAME)
/*     */ public class Err422GameEventBus
/*     */ {
/*     */   public static final boolean destroyOldEventSys = true;
/*  34 */   private static final RandomSource eventRandom = RandomSource.create();
/*     */   @SubscribeEvent
/*     */   public static void playerTickEvent(PlayerTickEvent.Post event) {
/*  37 */     Player player = event.getEntity();
/*     */     
/*  39 */     Level level = Objects.<Level>requireNonNull(player.level());
/*     */     
/*  41 */     if (!level.isClientSide()) {
/*     */       
/*  43 */       if (!Config.disableAnticheat)
/*  44 */       { boolean pass = playerAntiCheat(player); }
/*  45 */       else { boolean bool = true;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */          }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  66 */       Err422Client.clientPlayerTick(player);
/*     */     } 
/*     */   }
/*     */   private static int getEventCooldown() {
/*  70 */     return Mth.ceil((Config.averageEventDelay + eventRandom.nextInt(Config.averageEventDelay * 2)) * Config.eventTimeScale);
/*     */   }
/*     */   
/*     */   @Nonnull
/*     */   private static SpookyEvent selectEvent(int selector, @Nonnull Set<ResourceLocation> locations) {
/*  75 */     int c = 0;
/*  76 */     for (ResourceLocation rsl : locations) {
/*  77 */       SpookyEvent spookyEvent = Objects.<SpookyEvent>requireNonNull((SpookyEvent)Err422Registries.EVENTS_REGISTRY.get(rsl));
/*  78 */       c += spookyEvent.weight();
/*  79 */       if (selector < c) return spookyEvent; 
/*     */     } 
/*  81 */     throw new NullPointerException("???");
/*     */   }
/*     */   
/*     */   private static boolean playerAntiCheat(Player player) {
/*  85 */     if (player.isCreative() || player.isSpectator()) {
/*  86 */       PacketDistributor.sendToPlayer((ServerPlayer)player, (CustomPacketPayload)new PacketCrashGame(false), new CustomPacketPayload[0]);
/*  87 */       return false;
/*     */     } 
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void playerJoinedServer(PlayerEvent.PlayerLoggedInEvent event) {
/*  94 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/*  95 */       if (Err422PhaseManager.bossFightInEffect) {
/*  96 */         serverPlayer.connection.disconnect((Component)Component.literal("ERROR422 IS BREAKING OUT")
/*  97 */             .withColor(8912896)
/*  98 */             .withStyle(ChatFormatting.OBFUSCATED)
/*  99 */             .withStyle(ChatFormatting.BOLD));
/*     */       } else {
/*     */         
/* 102 */         PacketDistributor.sendToPlayer(serverPlayer, (CustomPacketPayload)new PacketSyncStackSize(
/* 103 */               Error422.getMaxStackSize()), new CustomPacketPayload[0]);
/* 104 */         PacketDistributor.sendToPlayer(serverPlayer, (CustomPacketPayload)new PacketUpdatePhaseState(Err422PhaseManager.error422Defeat, Err422PhaseManager.bossFightInEffect), new CustomPacketPayload[0]);
/*     */       } 
/*     */       
/* 107 */       EventManager.manager().addPlayer(serverPlayer); }
/*     */   
/*     */   }
/*     */   public static void playerLeftServer(PlayerEvent.PlayerLoggedOutEvent event) {
/* 111 */     Player player = event.getEntity(); if (player instanceof ServerPlayer) { ServerPlayer serverPlayer = (ServerPlayer)player;
/* 112 */       EventManager.manager().removePlayer(serverPlayer); }
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void serverTick(ServerTickEvent.Post event) {
/* 118 */     Err422PhaseManager.tick(event.getServer());
/* 119 */     EventManager.manager().tick();
/* 120 */     EventErrScreen.tick();
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void serverStarting(ServerStartingEvent event) {
/* 125 */     EventManager manager = EventManager.create(event.getServer());
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422GameEventBus.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */