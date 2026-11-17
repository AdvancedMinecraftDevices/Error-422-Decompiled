/*     */ package nl.melonstudios.error422.newsys.event;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.network.chat.Component;
/*     */ import net.minecraft.resources.ResourceLocation;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.level.ServerLevel;
/*     */ import net.minecraft.server.level.ServerPlayer;
/*     */ 
/*     */ public class EventManager {
/*     */   private static void destroyNull(Object... o) {
/*  16 */     for (Object obj : o) assert obj != null; 
/*     */   }
/*     */   public static EventManager manager() {
/*  19 */     return instance;
/*     */   }
/*  21 */   private static EventManager instance = null;
/*     */   public static EventManager create(MinecraftServer server) {
/*  23 */     return instance = new EventManager(server);
/*     */   }
/*     */   
/*  26 */   public static final HashMap<ResourceLocation, LocalEvent> LOCAL_EVENT_REGISTRY = new HashMap<>();
/*  27 */   public static final HashMap<ResourceLocation, GlobalEvent> GLOBAL_EVENT_REGISTRY = new HashMap<>();
/*     */   private static ImmutableList<GlobalEvent> ALL_GLOBAL_EVENTS;
/*     */   private static ImmutableList<LocalEvent> ALL_LOCAL_EVENTS;
/*     */   
/*     */   public static ImmutableList<GlobalEvent> getAllGlobalEvents() {
/*  32 */     return Objects.<ImmutableList<GlobalEvent>>requireNonNull(ALL_GLOBAL_EVENTS);
/*     */   }
/*     */   public static ImmutableList<LocalEvent> getAllLocalEvents() {
/*  35 */     return Objects.<ImmutableList<LocalEvent>>requireNonNull(ALL_LOCAL_EVENTS);
/*     */   }
/*     */   
/*     */   public static void registerLocalEvent(String id, LocalEvent event) {
/*  39 */     destroyNull(new Object[] { id, event });
/*  40 */     ResourceLocation rsl = ResourceLocation.parse(id);
/*  41 */     event.setResource(rsl);
/*  42 */     if (LOCAL_EVENT_REGISTRY.containsKey(rsl)) {
/*  43 */       throw new IllegalStateException("Duplicate local event key: " + String.valueOf(rsl));
/*     */     }
/*  45 */     LOCAL_EVENT_REGISTRY.put(rsl, event);
/*     */   }
/*     */   public static void registerGlobalEvent(String id, GlobalEvent event) {
/*  48 */     destroyNull(new Object[] { id, event });
/*  49 */     ResourceLocation rsl = ResourceLocation.parse(id);
/*  50 */     event.setResource(rsl);
/*  51 */     if (GLOBAL_EVENT_REGISTRY.containsKey(rsl)) {
/*  52 */       throw new IllegalStateException("Duplicate global event key: " + String.valueOf(rsl));
/*     */     }
/*  54 */     GLOBAL_EVENT_REGISTRY.put(rsl, event);
/*     */   }
/*     */   
/*     */   public static void optimize() {
/*  58 */     ALL_LOCAL_EVENTS = ImmutableList.copyOf(LOCAL_EVENT_REGISTRY.values());
/*  59 */     ALL_GLOBAL_EVENTS = ImmutableList.copyOf(GLOBAL_EVENT_REGISTRY.values());
/*     */     UnmodifiableIterator<GlobalEvent> unmodifiableIterator;
/*  61 */     for (unmodifiableIterator = ALL_GLOBAL_EVENTS.iterator(); unmodifiableIterator.hasNext(); ) { GlobalEvent event = unmodifiableIterator.next();
/*  62 */       switch (event.requiredPhase) { case 0:
/*  63 */           globalEvents0.add(event);
/*  64 */         case 1: globalEvents1.add(event);
/*  65 */         case 2: globalEvents2.add(event); }
/*     */        }
/*     */     
/*  68 */     for (unmodifiableIterator = ALL_LOCAL_EVENTS.iterator(); unmodifiableIterator.hasNext(); ) { LocalEvent event = (LocalEvent)unmodifiableIterator.next();
/*  69 */       switch (event.requiredPhase) { case 0:
/*  70 */           localEvents0.add(event);
/*  71 */         case 1: localEvents1.add(event);
/*  72 */         case 2: localEvents2.add(event); }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*  77 */   private static final List<GlobalEvent> globalEvents0 = new ArrayList<>();
/*  78 */   private static final List<GlobalEvent> globalEvents1 = new ArrayList<>();
/*  79 */   private static final List<GlobalEvent> globalEvents2 = new ArrayList<>();
/*     */   
/*     */   @Nonnull
/*     */   public static List<GlobalEvent> getAvailableGlobalEvents(int phase) {
/*  83 */     switch (phase) { case 0: 
/*     */       case 1:
/*     */       
/*     */       case 2:
/*  87 */        }  throw new IllegalStateException("Unexpected value: " + phase);
/*     */   }
/*     */ 
/*     */   
/*  91 */   private static final List<LocalEvent> localEvents0 = new ArrayList<>();
/*  92 */   private static final List<LocalEvent> localEvents1 = new ArrayList<>();
/*  93 */   private static final List<LocalEvent> localEvents2 = new ArrayList<>(); public final MinecraftServer server; private final Map<ServerPlayer, PlayerEventTracker> playerEventTrackers; private final Map<ServerLevel, LevelEventTracker> levelEventTrackers;
/*     */   
/*     */   @Nonnull
/*     */   public static List<LocalEvent> getAvailableLocalEvents(int phase) {
/*  97 */     switch (phase) { case 0: 
/*     */       case 1:
/*     */       
/*     */       case 2:
/* 101 */        }  throw new IllegalStateException("Unexpected value: " + phase);
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
/*     */   private EventManager(MinecraftServer server) {
/* 114 */     this.playerEventTrackers = new HashMap<>();
/* 115 */     this.levelEventTrackers = new HashMap<>(); this.server = server; for (ServerLevel level : server.getAllLevels()) {
/*     */       LevelEventTracker tracker = new LevelEventTracker(level); this.levelEventTrackers.put(level, tracker);
/*     */       tracker.init();
/* 118 */     }  } public void tick() { this.playerEventTrackers.values().forEach(PlayerEventTracker::tick);
/* 119 */     this.levelEventTrackers.values().forEach(LevelEventTracker::tick); }
/*     */ 
/*     */   
/*     */   public void addPlayer(ServerPlayer player) {
/* 123 */     PlayerEventTracker tracker = new PlayerEventTracker(player);
/* 124 */     this.playerEventTrackers.put(player, tracker);
/* 125 */     tracker.init();
/*     */   }
/*     */   public void removePlayer(ServerPlayer player) {
/* 128 */     this.playerEventTrackers.remove(player);
/*     */   }
/*     */   
/*     */   public List<Component> getDebugForPlayer(ServerPlayer player) {
/* 132 */     List<Component> debug = new ArrayList<>();
/* 133 */     debug.add(Component.literal("Local event trackers for " + player.getName().getString() + ":")
/* 134 */         .withColor(4521796));
/* 135 */     PlayerEventTracker tracker = this.playerEventTrackers.get(player);
/* 136 */     Map<LocalEvent, CountDown> trackers = tracker.trackers;
/* 137 */     for (Map.Entry<LocalEvent, CountDown> entry : trackers.entrySet()) {
/* 138 */       debug.add(Component.literal("> " + String.valueOf(((LocalEvent)entry.getKey()).registeredId) + ": " + ((CountDown)entry.getValue()).ticker + "t")
/* 139 */           .withColor(16753920));
/*     */     }
/* 141 */     return debug;
/*     */   }
/*     */   public List<Component> getDebugForLevel(ServerLevel level) {
/* 144 */     List<Component> debug = new ArrayList<>();
/* 145 */     debug.add(Component.literal("Global event trackers for " + String.valueOf(level.dimension().location()) + ":")
/* 146 */         .withColor(4521796));
/* 147 */     LevelEventTracker tracker = this.levelEventTrackers.get(level);
/* 148 */     Map<GlobalEvent, CountDown> trackers = tracker.trackers;
/* 149 */     for (Map.Entry<GlobalEvent, CountDown> entry : trackers.entrySet()) {
/* 150 */       debug.add(Component.literal("> " + String.valueOf(((GlobalEvent)entry.getKey()).registeredId) + ": " + ((CountDown)entry.getValue()).ticker + "t")
/* 151 */           .withColor(16753920));
/*     */     }
/* 153 */     return debug;
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\EventManager.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */