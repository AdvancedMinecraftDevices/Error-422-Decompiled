/*     */ package nl.melonstudios.error422;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererProvider;
/*     */ import net.minecraft.world.entity.EntityType;
/*     */ import net.minecraft.world.entity.ai.attributes.Attributes;
/*     */ import net.neoforged.api.distmarker.Dist;
/*     */ import net.neoforged.bus.api.SubscribeEvent;
/*     */ import net.neoforged.fml.common.EventBusSubscriber;
/*     */ import net.neoforged.neoforge.client.event.EntityRenderersEvent;
/*     */ import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
/*     */ import net.neoforged.neoforge.network.registration.PayloadRegistrar;
/*     */ import net.neoforged.neoforge.registries.NewRegistryEvent;
/*     */ import nl.melonstudios.error422.entity.Err422Entities;
/*     */ import nl.melonstudios.error422.idk.ErrRenderTypes;
/*     */ import nl.melonstudios.error422.net.PacketCrashGame;
/*     */ import nl.melonstudios.error422.net.PacketEnforceCaveNoise;
/*     */ import nl.melonstudios.error422.net.PacketNotifyErrScreen;
/*     */ import nl.melonstudios.error422.net.PacketPlaySFX1;
/*     */ import nl.melonstudios.error422.net.PacketPush;
/*     */ import nl.melonstudios.error422.net.PacketSyncCrashOrKill;
/*     */ import nl.melonstudios.error422.net.PacketSyncStackSize;
/*     */ import nl.melonstudios.error422.net.PacketUpdatePhaseState;
/*     */ 
/*     */ @EventBusSubscriber(modid = "error422", bus = EventBusSubscriber.Bus.MOD)
/*     */ public class Err422ModEventBus {
/*     */   @SubscribeEvent
/*     */   public static void registerRegistries(NewRegistryEvent event) {
/*  27 */     event.register(Err422Registries.EVENTS_REGISTRY);
/*     */   }
/*     */   @SubscribeEvent
/*     */   public static void registerPackets(RegisterPayloadHandlersEvent event) {
/*  31 */     PayloadRegistrar registrar = event.registrar("1");
/*  32 */     registrar.playToClient(PacketCrashGame.TYPE, PacketCrashGame.STREAM_CODEC, PacketCrashGame::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     registrar.playToClient(PacketEnforceCaveNoise.TYPE, PacketEnforceCaveNoise.STREAM_CODEC, PacketEnforceCaveNoise::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     registrar.playToClient(PacketNotifyErrScreen.TYPE, PacketNotifyErrScreen.STREAM_CODEC, PacketNotifyErrScreen::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     registrar.playToClient(PacketPlaySFX1.TYPE, PacketPlaySFX1.STREAM_CODEC, PacketPlaySFX1::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     registrar.playToClient(PacketPush.TYPE, PacketPush.STREAM_CODEC, PacketPush::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     registrar.playToClient(PacketSyncCrashOrKill.TYPE, PacketSyncCrashOrKill.STREAM_CODEC, PacketSyncCrashOrKill::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     registrar.playToClient(PacketSyncStackSize.TYPE, PacketSyncStackSize.STREAM_CODEC, PacketSyncStackSize::handle);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     registrar.playToClient(PacketUpdatePhaseState.TYPE, PacketUpdatePhaseState.STREAM_CODEC, PacketUpdatePhaseState::handle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
/*  77 */     event.registerEntityRenderer((EntityType)Err422Entities.THE_ENTITY_CRASH.get(), nl.melonstudios.error422.entity.TheEntityCrashRenderer::new);
/*  78 */     event.registerEntityRenderer((EntityType)Err422Entities.THE_ENTITY_KILL.get(), nl.melonstudios.error422.entity.TheEntityKillRenderer::new);
/*  79 */     event.registerEntityRenderer((EntityType)Err422Entities.THE_ENTITY_FIGHT.get(), nl.melonstudios.error422.entity.TheEntityFightRenderer::new);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void createEntityAttributes(EntityAttributeCreationEvent event) {
/*  84 */     event.put((EntityType)Err422Entities.THE_ENTITY_CRASH.get(), LivingEntity.createLivingAttributes()
/*  85 */         .add(Attributes.ATTACK_DAMAGE, 1.0D)
/*  86 */         .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612D)
/*  87 */         .add(Attributes.ATTACK_SPEED)
/*  88 */         .build());
/*  89 */     event.put((EntityType)Err422Entities.THE_ENTITY_KILL.get(), PathfinderMob.createMobAttributes()
/*  90 */         .add(Attributes.ATTACK_DAMAGE, 10.0D)
/*  91 */         .add(Attributes.MOVEMENT_SPEED, 1.0D)
/*  92 */         .add(Attributes.ATTACK_SPEED, 2.5D)
/*  93 */         .add(Attributes.MAX_HEALTH, 422.0D)
/*  94 */         .add(Attributes.FOLLOW_RANGE, 1024.0D)
/*  95 */         .build());
/*  96 */     event.put((EntityType)Err422Entities.THE_ENTITY_FIGHT.get(), PathfinderMob.createMobAttributes()
/*  97 */         .add(Attributes.ATTACK_DAMAGE, 2.5D)
/*  98 */         .add(Attributes.MOVEMENT_SPEED, 0.30000001192092896D)
/*  99 */         .add(Attributes.ATTACK_SPEED, 2.5D)
/* 100 */         .add(Attributes.MAX_HEALTH, 422.0D)
/* 101 */         .add(Attributes.FOLLOW_RANGE, 1024.0D)
/* 102 */         .build());
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public static void renderBufferSilliness(RegisterRenderBuffersEvent event) {
/* 108 */     event.registerRenderBuffer(ErrRenderTypes.REPLACEMENT, ErrRenderTypes.RENDER_BUFFERS.fixedBufferPack().buffer(ErrRenderTypes.REPLACEMENT));
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422ModEventBus.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */