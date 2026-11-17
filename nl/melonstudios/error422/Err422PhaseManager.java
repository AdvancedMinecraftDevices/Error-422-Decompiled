/*     */ package nl.melonstudios.error422;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.util.RandomSource;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.LivingEntity;
/*     */ import net.minecraft.world.entity.player.Player;
/*     */ import net.minecraft.world.level.GameType;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.block.Blocks;
/*     */ import net.minecraft.world.level.portal.DimensionTransition;
/*     */ import net.minecraft.world.phys.Vec3;
/*     */ import net.neoforged.neoforge.network.PacketDistributor;
/*     */ import nl.melonstudios.error422.entity.TheEntityCrash;
/*     */ import nl.melonstudios.error422.entity.TheEntityFight;
/*     */ import nl.melonstudios.error422.entity.TheEntityKill;
/*     */ import nl.melonstudios.error422.events.EventErrScreen;
/*     */ import nl.melonstudios.error422.net.PacketCrashGame;
/*     */ import nl.melonstudios.error422.net.PacketSyncCrashOrKill;
/*     */ import nl.melonstudios.error422.net.PacketUpdatePhaseState;
/*     */ import nl.melonstudios.error422.newsys.ErrMessageProvider;
/*     */ 
/*     */ public class Err422PhaseManager {
/*  30 */   public static final Random random = new Random(System.nanoTime());
/*  31 */   public static boolean crashOrKill = random.nextBoolean();
/*     */   public static boolean error422Defeat = false;
/*     */   public static boolean bossFightInEffect = false;
/*  34 */   public static int phase = 0;
/*  35 */   public static int phaseCooldown = findNextCooldown();
/*  36 */   private static int bossFightDelay = -1;
/*     */   
/*     */   private static int findNextCooldown() {
/*  39 */     return Mth.ceil((24000 + random.nextInt(12000)) * Config.eventTimeScale);
/*     */   }
/*     */   
/*  42 */   private static int crashDelay = 80;
/*     */   private static boolean begunFinale = false;
/*     */   
/*     */   public static int getCrashDelay() {
/*  46 */     return crashDelay;
/*     */   }
/*     */   public static boolean isBegunFinale() {
/*  49 */     return begunFinale;
/*     */   }
/*     */   
/*     */   public static void reset() {
/*  53 */     phase = 0;
/*  54 */     phaseCooldown = findNextCooldown();
/*     */   }
/*     */   public static void nextPhase() {
/*  57 */     phase++;
/*  58 */     phaseCooldown = findNextCooldown();
/*     */   }
/*     */   
/*     */   private static ServerPlayer resetPlayerDimension(ServerPlayer player, ServerLevel overworld, Vec3 spawnVec) {
/*  62 */     return (ServerPlayer)player.changeDimension(new DimensionTransition(overworld, spawnVec
/*     */ 
/*     */           
/*  65 */           .add((random.nextFloat() * 2.0F - 1.0F), 0.0D, (random.nextFloat() * 2.0F - 1.0F)), Vec3.ZERO, random
/*     */           
/*  67 */           .nextFloat(360.0F), 0.0F, false, Err422PhaseManager::h));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void h(Entity e) {
/*  75 */     e.extinguishFire();
/*  76 */     if (e instanceof LivingEntity) { LivingEntity entity = (LivingEntity)e; entity.setHealth(entity.getMaxHealth()); }
/*     */   
/*     */   }
/*     */   public static void tick(MinecraftServer server) {
/*  80 */     if (error422Defeat)
/*  81 */       return;  if (bossFightDelay > 0) {
/*  82 */       bossFightDelay--;
/*  83 */       if (bossFightDelay == 0) {
/*  84 */         ServerLevel overworld = server.overworld();
/*  85 */         BlockPos spawn = overworld.getSharedSpawnPos();
/*  86 */         Vec3 spawnVec = spawn.getBottomCenter();
/*  87 */         for (ServerPlayer player : server.getPlayerList().getPlayers()) {
/*  88 */           player = resetPlayerDimension(player, overworld, spawnVec);
/*  89 */           if (player != null) {
/*  90 */             player.setGameMode(GameType.SURVIVAL);
/*  91 */             EventErrScreen.enact((Player)player, 50);
/*     */           } 
/*     */         } 
/*  94 */         PacketDistributor.sendToAllPlayers((CustomPacketPayload)new PacketUpdatePhaseState(error422Defeat, bossFightInEffect), new CustomPacketPayload[0]);
/*  95 */         TheEntityFight tef = new TheEntityFight(overworld);
/*  96 */         overworld.addFreshEntity((Entity)tef);
/*     */       } 
/*     */     } 
/*  99 */     if (bossFightInEffect) {
/* 100 */       bossFightTick(server);
/*     */     } else {
/* 102 */       if (phase < 3) {
/* 103 */         phaseCooldown--;
/* 104 */         if (phaseCooldown <= 0) {
/* 105 */           nextPhase();
/*     */         }
/*     */       }
/* 108 */       else if (Config.enableError422BossFight) {
/* 109 */         bossFightInEffect = true;
/* 110 */         bossFightDelay = 1200;
/* 111 */         server.getPlayerList().getPlayers().forEach(p -> p.sendSystemMessage(ErrMessageProvider.provide("UNPROCESSABLE ENTITY ERROR")));
/*     */       } else {
/*     */         
/* 114 */         if (!begunFinale) {
/* 115 */           begunFinale = true;
/* 116 */           PacketDistributor.sendToAllPlayers((CustomPacketPayload)new PacketSyncCrashOrKill(crashOrKill), new CustomPacketPayload[0]);
/* 117 */           PacketDistributor.sendToAllPlayers((CustomPacketPayload)new PacketCrashGame(true), new CustomPacketPayload[0]);
/* 118 */           for (ServerPlayer player : server.getPlayerList().getPlayers()) {
/* 119 */             Level level = Objects.<Level>requireNonNull(player.level());
/* 120 */             level.addFreshEntity(crashOrKill ? (Entity)new TheEntityCrash(level, (Player)player) : (Entity)new TheEntityKill((Player)player));
/*     */           } 
/*     */         } 
/* 123 */         if (crashDelay > 0) { crashDelay--; }
/*     */         else
/* 125 */         { finale(server); }
/*     */       
/*     */       } 
/*     */ 
/*     */       
/* 130 */       if (!Config.disableAnticheat) {
/* 131 */         for (ServerPlayer player : server.getPlayerList().getPlayers()) {
/* 132 */           if (server.getPlayerList().isOp(player.getGameProfile())) {
/* 133 */             PacketDistributor.sendToPlayer(player, (CustomPacketPayload)new PacketCrashGame(false), new CustomPacketPayload[0]);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void bossFightTick(MinecraftServer server) {
/* 141 */     if (bossFightDelay == 0) {
/* 142 */       for (ServerPlayer player : server.getPlayerList().getPlayers()) {
/* 143 */         if (player.level() != server.overworld()) {
/* 144 */           resetPlayerDimension(player, server.overworld(), server.overworld().getSharedSpawnPos().getBottomCenter());
/* 145 */           EventErrScreen.enact((Player)player, 100);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static void flamePlayer(ServerPlayer player) {
/* 152 */     RandomSource rnd = RandomSource.create();
/* 153 */     Level level = Objects.<Level>requireNonNull(player.level());
/* 154 */     BlockPos origin = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
/* 155 */     for (int x = -8; x <= 8; x++) {
/* 156 */       for (int y = -8; y <= 8; y++) {
/* 157 */         for (int z = -8; z <= 8; z++) {
/* 158 */           if (rnd.nextInt(5) == 0 && 
/* 159 */             x * x + y * y + z * z <= 64) {
/* 160 */             BlockPos pos = origin.offset(x, y, z);
/* 161 */             if (level.getBlockState(pos).canBeReplaced()) {
/* 162 */               level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finale(MinecraftServer server) {
/* 172 */     if (crashOrKill)
/* 173 */       PacketDistributor.sendToAllPlayers((CustomPacketPayload)new PacketCrashGame(false), new CustomPacketPayload[0]); 
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422PhaseManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */