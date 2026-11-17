/*     */ package nl.melonstudios.error422.mixin.client;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.blaze3d.platform.GlUtil;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.ChatFormatting;
/*     */ import net.minecraft.SharedConstants;
/*     */ import net.minecraft.client.ClientBrandRetriever;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ClientPacketListener;
/*     */ import net.minecraft.client.renderer.PostChain;
/*     */ import net.minecraft.client.server.IntegratedServer;
/*     */ import net.minecraft.core.BlockPos;
/*     */ import net.minecraft.core.Direction;
/*     */ import net.minecraft.core.Holder;
/*     */ import net.minecraft.core.registries.BuiltInRegistries;
/*     */ import net.minecraft.network.Connection;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceKey;
/*     */ import net.minecraft.server.ServerTickRateManager;
/*     */ import net.minecraft.server.level.ServerChunkCache;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.tags.TagKey;
/*     */ import net.minecraft.util.Mth;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.TickRateManager;
/*     */ import net.minecraft.world.entity.Entity;
/*     */ import net.minecraft.world.entity.MobCategory;
/*     */ import net.minecraft.world.level.ChunkPos;
/*     */ import net.minecraft.world.level.Level;
/*     */ import net.minecraft.world.level.LightLayer;
/*     */ import net.minecraft.world.level.NaturalSpawner;
/*     */ import net.minecraft.world.level.biome.Biome;
/*     */ import net.minecraft.world.level.biome.BiomeSource;
/*     */ import net.minecraft.world.level.biome.Climate;
/*     */ import net.minecraft.world.level.block.state.BlockState;
/*     */ import net.minecraft.world.level.block.state.properties.Property;
/*     */ import net.minecraft.world.level.chunk.ChunkGenerator;
/*     */ import net.minecraft.world.level.chunk.LevelChunk;
/*     */ import net.minecraft.world.level.levelgen.Heightmap;
/*     */ import net.minecraft.world.level.levelgen.RandomState;
/*     */ import net.minecraft.world.level.material.FluidState;
/*     */ import net.minecraft.world.phys.BlockHitResult;
/*     */ import net.minecraft.world.phys.HitResult;
/*     */ import org.spongepowered.asm.mixin.Final;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.Shadow;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*     */ 
/*     */ @Mixin({DebugScreenOverlay.class})
/*     */ public abstract class DebugScreenOverlayMixin {
/*     */   @Shadow
/*     */   @Final
/*     */   private Minecraft minecraft;
/*     */   
/*     */   @Shadow
/*     */   private static String printBiome(Holder<Biome> biomeHolder) {
/*  68 */     return (String)biomeHolder.unwrap().map(p_205377_ -> p_205377_.location().toString(), p_339278_ -> "[unregistered " + String.valueOf(p_339278_) + "]");
/*     */   }
/*     */ 
/*     */   
/*     */   @Shadow
/*     */   @Final
/*     */   private static Map<Heightmap.Types, String> HEIGHTMAP_NAMES;
/*     */   
/*     */   @Shadow
/*     */   @Nullable
/*     */   private ChunkPos lastPos;
/*     */   @Shadow
/*     */   private HitResult block;
/*     */   @Shadow
/*     */   private HitResult liquid;
/*     */   
/*     */   @Shadow
/*     */   private static long bytesToMegabytes(long bytes) {
/*  86 */     return bytes / 1024L / 1024L;
/*     */   }
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
/*     */   @Inject(method = {"getGameInformation"}, at = {@At("HEAD")}, cancellable = true)
/*     */   protected void getGameInformation(CallbackInfoReturnable<List<String>> cir) {
/*     */     String str1, s;
/* 101 */     cir.cancel();
/* 102 */     IntegratedServer integratedserver = this.minecraft.getSingleplayerServer();
/* 103 */     ClientPacketListener clientpacketlistener = this.minecraft.getConnection();
/* 104 */     Connection connection = clientpacketlistener.getConnection();
/* 105 */     float f = connection.getAverageSentPackets();
/* 106 */     float f1 = connection.getAverageReceivedPackets();
/* 107 */     TickRateManager tickratemanager = getLevel().tickRateManager();
/*     */     
/* 109 */     if (tickratemanager.isSteppingForward()) {
/* 110 */       str1 = " (frozen - stepping)";
/* 111 */     } else if (tickratemanager.isFrozen()) {
/* 112 */       str1 = " (frozen)";
/*     */     } else {
/* 114 */       str1 = "";
/*     */     } 
/*     */ 
/*     */     
/* 118 */     if (integratedserver != null) {
/* 119 */       ServerTickRateManager servertickratemanager = integratedserver.tickRateManager();
/* 120 */       boolean flag = servertickratemanager.isSprinting();
/* 121 */       if (flag) {
/* 122 */         str1 = " (sprinting)";
/*     */       }
/*     */       
/* 125 */       String s2 = flag ? "-" : String.format(Locale.ROOT, "%.1f", new Object[] { Float.valueOf(tickratemanager.millisecondsPerTick()) });
/* 126 */       s = String.format(Locale.ROOT, "Integrated server @ %.1f/%s ms%s, %.0f tx, %.0f rx", new Object[] { Float.valueOf(integratedserver.getCurrentSmoothedTickTime()), s2, str1, Float.valueOf(f), Float.valueOf(f1) });
/*     */     } else {
/* 128 */       s = String.format(Locale.ROOT, "\"%s\" server%s, %.0f tx, %.0f rx", new Object[] { clientpacketlistener.serverBrand(), str1, Float.valueOf(f), Float.valueOf(f1) });
/*     */     } 
/*     */     
/* 131 */     BlockPos blockpos = this.minecraft.getCameraEntity().blockPosition();
/* 132 */     if (this.minecraft.showOnlyReducedInfo()) {
/* 133 */       ArrayList<String> ret = Lists.newArrayList((Object[])new String[] {
/*     */             
/* 135 */             "§mMinecraft " + SharedConstants.getCurrentVersion().getName() + " (" + this.minecraft
/*     */             
/* 137 */             .getLaunchedVersion() + "/" + 
/*     */             
/* 139 */             ClientBrandRetriever.getClientModName() + ")§r §4ERR422", this.minecraft.fpsString, s, this.minecraft.levelRenderer
/*     */ 
/*     */ 
/*     */             
/* 143 */             .getSectionStatistics(), this.minecraft.levelRenderer
/* 144 */             .getEntityStatistics(), "P: " + this.minecraft.particleEngine
/* 145 */             .countParticles() + ". T: " + this.minecraft.level.getEntityCount(), this.minecraft.level
/* 146 */             .gatherChunkSourceStats(), "", 
/*     */             
/* 148 */             String.format(Locale.ROOT, "§kChunk-relative: %d %d %d", new Object[] { Integer.valueOf(blockpos.getX() & 0xF), Integer.valueOf(blockpos.getY() & 0xF), Integer.valueOf(blockpos.getZ() & 0xF) })
/*     */           });
/* 150 */       cir.setReturnValue(ret);
/*     */       return;
/*     */     } 
/* 153 */     Entity entity = this.minecraft.getCameraEntity();
/* 154 */     Direction direction = entity.getDirection();
/*     */     
/* 156 */     switch (direction) { case NORTH: 
/*     */       case SOUTH: 
/*     */       case WEST: 
/*     */       case EAST: 
/*     */       default:
/* 161 */         break; }  String $$21 = "Invalid";
/*     */     
/* 163 */     ChunkPos chunkpos = new ChunkPos(blockpos);
/* 164 */     if (!Objects.equals(this.lastPos, chunkpos)) {
/* 165 */       this.lastPos = chunkpos;
/* 166 */       clearChunkCache();
/*     */     } 
/*     */     
/* 169 */     Level level = getLevel();
/* 170 */     LongSet longset = (level instanceof ServerLevel) ? ((ServerLevel)level).getForcedChunks() : (LongSet)LongSets.EMPTY_SET;
/* 171 */     List<String> list = Lists.newArrayList((Object[])new String[] {
/*     */           
/* 173 */           "§mMinecraft " + SharedConstants.getCurrentVersion().getName() + " (" + this.minecraft
/*     */           
/* 175 */           .getLaunchedVersion() + "/" + 
/*     */           
/* 177 */           ClientBrandRetriever.getClientModName() + (
/* 178 */           "release".equalsIgnoreCase(this.minecraft.getVersionType()) ? "" : ("/" + this.minecraft.getVersionType())) + ")§r §4ERR422", this.minecraft.fpsString, s, this.minecraft.levelRenderer
/*     */ 
/*     */ 
/*     */           
/* 182 */           .getSectionStatistics(), this.minecraft.levelRenderer
/* 183 */           .getEntityStatistics(), "P: " + this.minecraft.particleEngine
/* 184 */           .countParticles() + ". T: " + this.minecraft.level.getEntityCount(), this.minecraft.level
/* 185 */           .gatherChunkSourceStats()
/*     */         });
/* 187 */     String s4 = getServerChunkStats();
/* 188 */     if (s4 != null) {
/* 189 */       list.add(s4);
/*     */     }
/*     */     
/* 192 */     list.add(String.valueOf(this.minecraft.level.dimension().location()) + " FC: " + String.valueOf(this.minecraft.level.dimension().location()));
/* 193 */     list.add("");
/* 194 */     list.add(
/* 195 */         String.format(Locale.ROOT, "§kXYZ: %.3f / %.5f / %.3f", new Object[] {
/*     */ 
/*     */             
/* 198 */             Double.valueOf(this.minecraft.getCameraEntity().getX()), 
/* 199 */             Double.valueOf(this.minecraft.getCameraEntity().getY()), 
/* 200 */             Double.valueOf(this.minecraft.getCameraEntity().getZ())
/*     */           }));
/*     */     
/* 203 */     list.add(
/* 204 */         String.format(Locale.ROOT, "§kBlock: %d %d %d [%d %d %d]", new Object[] {
/*     */ 
/*     */             
/* 207 */             Integer.valueOf(blockpos.getX()), 
/* 208 */             Integer.valueOf(blockpos.getY()), 
/* 209 */             Integer.valueOf(blockpos.getZ()), 
/* 210 */             Integer.valueOf(blockpos.getX() & 0xF), 
/* 211 */             Integer.valueOf(blockpos.getY() & 0xF), 
/* 212 */             Integer.valueOf(blockpos.getZ() & 0xF)
/*     */           }));
/*     */     
/* 215 */     list.add(
/* 216 */         String.format(Locale.ROOT, "§kChunk: %d %d %d [%d %d in r.%d.%d.mca]", new Object[] {
/*     */ 
/*     */             
/* 219 */             Integer.valueOf(chunkpos.x), 
/* 220 */             Integer.valueOf(SectionPos.blockToSectionCoord(blockpos.getY())), 
/* 221 */             Integer.valueOf(chunkpos.z), 
/* 222 */             Integer.valueOf(chunkpos.getRegionLocalX()), 
/* 223 */             Integer.valueOf(chunkpos.getRegionLocalZ()), 
/* 224 */             Integer.valueOf(chunkpos.getRegionX()), 
/* 225 */             Integer.valueOf(chunkpos.getRegionZ())
/*     */           }));
/*     */     
/* 228 */     list.add(
/* 229 */         String.format(Locale.ROOT, "§kFacing: %s (%s) (%.1f / %.1f)", new Object[] {
/* 230 */             direction, $$21, Float.valueOf(Mth.wrapDegrees(entity.getYRot())), Float.valueOf(Mth.wrapDegrees(entity.getXRot()))
/*     */           }));
/*     */     
/* 233 */     LevelChunk levelchunk = getClientChunk();
/* 234 */     if (levelchunk.isEmpty()) {
/* 235 */       list.add("Waiting for chunk...");
/*     */     } else {
/* 237 */       int i = this.minecraft.level.getChunkSource().getLightEngine().getRawBrightness(blockpos, 0);
/* 238 */       int j = this.minecraft.level.getBrightness(LightLayer.SKY, blockpos);
/* 239 */       int k = this.minecraft.level.getBrightness(LightLayer.BLOCK, blockpos);
/* 240 */       list.add("§kClient Light: " + i + " (" + j + " sky, " + k + " block)");
/* 241 */       LevelChunk levelchunk1 = getServerChunk();
/* 242 */       StringBuilder stringbuilder = new StringBuilder("§kCH");
/*     */       
/* 244 */       for (Heightmap.Types heightmap$types : Heightmap.Types.values()) {
/* 245 */         if (heightmap$types.sendToClient()) {
/* 246 */           stringbuilder.append(" ")
/* 247 */             .append(HEIGHTMAP_NAMES.get(heightmap$types))
/* 248 */             .append(": ")
/* 249 */             .append(levelchunk.getHeight(heightmap$types, blockpos.getX(), blockpos.getZ()));
/*     */         }
/*     */       } 
/*     */       
/* 253 */       list.add(stringbuilder.toString());
/* 254 */       stringbuilder.setLength(0);
/* 255 */       stringbuilder.append("SH");
/*     */       
/* 257 */       for (Heightmap.Types heightmap$types1 : Heightmap.Types.values()) {
/* 258 */         if (heightmap$types1.keepAfterWorldgen()) {
/* 259 */           stringbuilder.append(" ").append(HEIGHTMAP_NAMES.get(heightmap$types1)).append(": ");
/* 260 */           if (levelchunk1 != null) {
/* 261 */             stringbuilder.append(levelchunk1.getHeight(heightmap$types1, blockpos.getX(), blockpos.getZ()));
/*     */           } else {
/* 263 */             stringbuilder.append("??");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 268 */       list.add(stringbuilder.toString());
/* 269 */       if (blockpos.getY() >= this.minecraft.level.getMinBuildHeight() && blockpos.getY() < this.minecraft.level.getMaxBuildHeight()) {
/* 270 */         list.add("§kBiome: " + printBiome(this.minecraft.level.getBiome(blockpos)));
/* 271 */         if (levelchunk1 != null) {
/* 272 */           float f2 = level.getMoonBrightness();
/* 273 */           long l = levelchunk1.getInhabitedTime();
/* 274 */           DifficultyInstance difficultyinstance = new DifficultyInstance(level.getDifficulty(), level.getDayTime(), l, f2);
/* 275 */           list.add(
/* 276 */               String.format(Locale.ROOT, "§kLocal Difficulty: %.2f // %.2f (Day %d)", new Object[] {
/*     */ 
/*     */                   
/* 279 */                   Float.valueOf(difficultyinstance.getEffectiveDifficulty()), 
/* 280 */                   Float.valueOf(difficultyinstance.getSpecialMultiplier()), 
/* 281 */                   Long.valueOf(this.minecraft.level.getDayTime() / 24000L)
/*     */                 }));
/*     */         } else {
/*     */           
/* 285 */           list.add("§kLocal Difficulty: ??");
/*     */         } 
/*     */       } 
/*     */       
/* 289 */       if (levelchunk1 != null && levelchunk1.isOldNoiseGeneration()) {
/* 290 */         list.add("Blending: Old");
/*     */       }
/*     */     } 
/*     */     
/* 294 */     ServerLevel serverlevel = getServerLevel();
/* 295 */     if (serverlevel != null) {
/* 296 */       ServerChunkCache serverchunkcache = serverlevel.getChunkSource();
/* 297 */       ChunkGenerator chunkgenerator = serverchunkcache.getGenerator();
/* 298 */       RandomState randomstate = serverchunkcache.randomState();
/* 299 */       chunkgenerator.addDebugScreenInfo(list, randomstate, blockpos);
/* 300 */       Climate.Sampler climate$sampler = randomstate.sampler();
/* 301 */       BiomeSource biomesource = chunkgenerator.getBiomeSource();
/* 302 */       biomesource.addDebugInfo(list, blockpos, climate$sampler);
/* 303 */       NaturalSpawner.SpawnState naturalspawner$spawnstate = serverchunkcache.getLastSpawnState();
/* 304 */       if (naturalspawner$spawnstate != null) {
/* 305 */         Object2IntMap<MobCategory> object2intmap = naturalspawner$spawnstate.getMobCategoryCounts();
/* 306 */         int i1 = naturalspawner$spawnstate.getSpawnableChunkCount();
/* 307 */         list.add("§kSC: " + i1 + ", " + 
/*     */ 
/*     */ 
/*     */             
/* 311 */             (String)Stream.<MobCategory>of(MobCategory.values())
/* 312 */             .map(p_94068_ -> "" + Character.toUpperCase(p_94068_.getName().charAt(0)) + ": " + Character.toUpperCase(p_94068_.getName().charAt(0)))
/* 313 */             .collect(Collectors.joining(", ")));
/*     */       } else {
/*     */         
/* 316 */         list.add("§kSC: N/A");
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     PostChain postchain = this.minecraft.gameRenderer.currentEffect();
/* 321 */     if (postchain != null) {
/* 322 */       list.add("Shader: " + postchain.getName());
/*     */     }
/*     */     
/* 325 */     list.add("§k" + this.minecraft
/* 326 */         .getSoundManager().getDebugString() + 
/* 327 */         String.format(Locale.ROOT, " (Mood %d%%)", new Object[] { Integer.valueOf(Math.round(this.minecraft.player.getCurrentMood() * 100.0F)) }));
/*     */     
/* 329 */     cir.setReturnValue(list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"getSystemInformation"}, at = {@At("HEAD")}, cancellable = true)
/*     */   protected void getSystemInformation(CallbackInfoReturnable<List<String>> cir) {
/* 336 */     cir.cancel();
/* 337 */     long i = Runtime.getRuntime().maxMemory();
/* 338 */     long j = Runtime.getRuntime().totalMemory();
/* 339 */     long k = Runtime.getRuntime().freeMemory();
/* 340 */     long l = j - k;
/* 341 */     List<String> list = Lists.newArrayList((Object[])new String[] {
/* 342 */           String.format(Locale.ROOT, "Java: %s", new Object[] { System.getProperty("java.version")
/* 343 */             }), String.format(Locale.ROOT, "§kMem: %2d%% %03d/%03dMB", new Object[] { Long.valueOf(l * 100L / i), Long.valueOf(bytesToMegabytes(l)), Long.valueOf(bytesToMegabytes(i))
/* 344 */             }), String.format(Locale.ROOT, "§kAllocation rate: %03dMB/s", new Object[] { Integer.valueOf(422)
/* 345 */             }), String.format(Locale.ROOT, "§kAllocated: %2d%% %03dMB", new Object[] { Long.valueOf(j * 100L / i), Long.valueOf(bytesToMegabytes(j)) }), "", 
/*     */           
/* 347 */           String.format(Locale.ROOT, "§kCPU: %s", new Object[] { GlUtil.getCpuInfo() }), "", 
/*     */           
/* 349 */           String.format(Locale.ROOT, "Display: %dx%d (%s)", new Object[] {
/*     */ 
/*     */               
/* 352 */               Integer.valueOf(Minecraft.getInstance().getWindow().getWidth()), 
/* 353 */               Integer.valueOf(Minecraft.getInstance().getWindow().getHeight()), 
/* 354 */               GlUtil.getVendor()
/*     */             
/* 356 */             }), GlUtil.getRenderer(), 
/* 357 */           GlUtil.getOpenGLVersion()
/*     */         });
/* 359 */     if (this.minecraft.isDemo()) {
/* 360 */       if (this.minecraft.level.getGameTime() >= 120500L) {
/* 361 */         list.add(0, Component.translatable("demo.demoExpired").getString());
/*     */       } else {
/* 363 */         list.add(0, Component.translatable("demo.remainingTime", new Object[] { StringUtil.formatTickDuration((int)(120500L - this.minecraft.level.getGameTime()), this.minecraft.level.tickRateManager().tickrate()) }).getString());
/*     */       } 
/*     */     }
/* 366 */     if (this.minecraft.showOnlyReducedInfo()) {
/* 367 */       cir.setReturnValue(list);
/*     */       return;
/*     */     } 
/* 370 */     if (this.block.getType() == HitResult.Type.BLOCK) {
/* 371 */       BlockPos blockpos = ((BlockHitResult)this.block).getBlockPos();
/* 372 */       BlockState blockstate = this.minecraft.level.getBlockState(blockpos);
/* 373 */       list.add("");
/* 374 */       list.add(String.valueOf(ChatFormatting.UNDERLINE) + "Targeted Block: " + String.valueOf(ChatFormatting.UNDERLINE) + ", " + blockpos.getX() + ", " + blockpos.getY());
/* 375 */       list.add(String.valueOf(BuiltInRegistries.BLOCK.getKey(blockstate.getBlock())));
/*     */       
/* 377 */       for (Map.Entry<Property<?>, Comparable<?>> entry : (Iterable<Map.Entry<Property<?>, Comparable<?>>>)blockstate.getValues().entrySet()) {
/* 378 */         list.add(getPropertyValueString(entry));
/*     */       }
/*     */       
/* 381 */       Objects.requireNonNull(list); blockstate.getTags().map(p_339277_ -> "§k#" + String.valueOf(p_339277_.location())).forEach(list::add);
/*     */     } 
/*     */     
/* 384 */     if (this.liquid.getType() == HitResult.Type.BLOCK) {
/* 385 */       BlockPos blockpos1 = ((BlockHitResult)this.liquid).getBlockPos();
/* 386 */       FluidState fluidstate = this.minecraft.level.getFluidState(blockpos1);
/* 387 */       list.add("");
/* 388 */       list.add(String.valueOf(ChatFormatting.UNDERLINE) + "Targeted Fluid: " + String.valueOf(ChatFormatting.UNDERLINE) + ", " + blockpos1.getX() + ", " + blockpos1.getY());
/* 389 */       list.add(String.valueOf(BuiltInRegistries.FLUID.getKey(fluidstate.getType())));
/*     */       
/* 391 */       for (Map.Entry<Property<?>, Comparable<?>> entry1 : (Iterable<Map.Entry<Property<?>, Comparable<?>>>)fluidstate.getValues().entrySet()) {
/* 392 */         list.add(getPropertyValueString(entry1));
/*     */       }
/*     */       
/* 395 */       Objects.requireNonNull(list); fluidstate.getTags().map(p_339279_ -> "§k#" + String.valueOf(p_339279_.location())).forEach(list::add);
/*     */     } 
/*     */     
/* 398 */     Entity entity = this.minecraft.crosshairPickEntity;
/* 399 */     if (entity != null) {
/* 400 */       list.add("");
/* 401 */       list.add(String.valueOf(ChatFormatting.UNDERLINE) + "Targeted Entity");
/* 402 */       list.add(String.valueOf(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType())));
/* 403 */       entity.getType().builtInRegistryHolder().tags().forEach(t -> list.add("§k#" + String.valueOf(t.location())));
/*     */     } 
/*     */     
/* 406 */     cir.setReturnValue(list);
/*     */   }
/*     */   
/*     */   @Shadow
/*     */   @Nullable
/*     */   protected abstract ServerLevel getServerLevel();
/*     */   
/*     */   @Shadow
/*     */   @Nullable
/*     */   protected abstract LevelChunk getServerChunk();
/*     */   
/*     */   @Shadow
/*     */   protected abstract LevelChunk getClientChunk();
/*     */   
/*     */   @Shadow
/*     */   @Nullable
/*     */   protected abstract String getServerChunkStats();
/*     */   
/*     */   @Shadow
/*     */   protected abstract Level getLevel();
/*     */   
/*     */   @Shadow
/*     */   public abstract void clearChunkCache();
/*     */   
/*     */   @Shadow
/*     */   protected abstract String getPropertyValueString(Map.Entry<Property<?>, Comparable<?>> paramEntry);
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\DebugScreenOverlayMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */