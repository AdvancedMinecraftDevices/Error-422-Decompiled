/*    */ package nl.melonstudios.error422;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import net.neoforged.bus.api.SubscribeEvent;
/*    */ import net.neoforged.fml.common.EventBusSubscriber;
/*    */ import net.neoforged.fml.event.config.ModConfigEvent;
/*    */ import net.neoforged.neoforge.common.ModConfigSpec;
/*    */ 
/*    */ @EventBusSubscriber(modid = "error422", bus = EventBusSubscriber.Bus.MOD)
/*    */ public class Config
/*    */ {
/* 12 */   private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
/*    */   
/* 14 */   private static final ModConfigSpec.BooleanValue ENABLE_ERR_422_BOSS_FIGHT = BUILDER
/* 15 */     .comment("Whether to enable the WIP error 422 boss fight")
/* 16 */     .define("enableBossFight", false);
/*    */   
/* 18 */   private static final ModConfigSpec.BooleanValue ENABLE_MULTIPLAYER = BUILDER
/* 19 */     .comment("Whether to re-enable the multiplayer button")
/* 20 */     .define("enableMultiplayer", false);
/*    */   
/* 22 */   private static final ModConfigSpec.BooleanValue DISABLE_ANTICHEAT = BUILDER
/* 23 */     .comment(new String[] {
/*    */         
/*    */         "Whether to disable the anticheat", "The anticheat prevents players from playing in creative", "Disabling this also exposes the /error422 command"
/*    */ 
/*    */       
/* 28 */       }).define("disableAnticheat", false);
/*    */   
/* 30 */   private static final ModConfigSpec.BooleanValue DISABLE_OPTIONS = BUILDER
/* 31 */     .comment("Whether to change some option buttons to 'Undefined'")
/* 32 */     .define("undefinedOptions", true);
/*    */   
/* 34 */   private static final ModConfigSpec.BooleanValue DISABLE_STATIC = BUILDER
/* 35 */     .comment("Whether to disable Minecraft music being replaced by jarring static")
/* 36 */     .define("disableStatic", false);
/*    */   
/* 38 */   private static final ModConfigSpec.BooleanValue ENABLE_VHS_OVERLAY = BUILDER
/* 39 */     .comment("Whether to enable a VHS overlay in-game")
/* 40 */     .define("enableVhsOverlay", false);
/*    */   
/* 42 */   private static final ModConfigSpec.IntValue AVERAGE_EVENT_DELAY = BUILDER
/* 43 */     .comment("The average delay between events.")
/* 44 */     .defineInRange("averageEventDelay", 2400, 2, 12000);
/*    */   
/* 46 */   private static final ModConfigSpec.DoubleValue EVENT_TIME_SCALE = BUILDER
/* 47 */     .comment("The scale of events. Higher number means more time in between events and thus a longer playtime.")
/* 48 */     .comment("0.1 means 6-9minutes of playtime, 10.0 means 10-15hours of playtime")
/* 49 */     .defineInRange("eventTimeScale", 1.0D, 0.1D, 10.0D);
/*    */   
/* 51 */   private static final ModConfigSpec.BooleanValue ADVANCED_RENDER = BUILDER
/* 52 */     .comment("Whether to use Advanced Rendering Shenanigans.")
/* 53 */     .comment("(These most likely don't work)")
/* 54 */     .define("advancedRender", false);
/*    */   
/* 56 */   private static final ModConfigSpec.BooleanValue ENFORCE_SILLY_MODE = BUILDER
/* 57 */     .comment("silly mode :3")
/* 58 */     .define("enforceSillyMode", false);
/*    */   
/* 60 */   private static final ModConfigSpec.BooleanValue DONT_ESCAPE_PLS = BUILDER
/* 61 */     .comment("Prevent ERROR422 from escaping Minecraft")
/* 62 */     .comment("(Prevents the ERROR422 executable from being placed on your desktop)")
/* 63 */     .comment("Disable this if you don't mind, it's for lore purposes only")
/* 64 */     .define("dontEscapePlease", false);
/*    */   
/* 66 */   static final ModConfigSpec SPEC = BUILDER.build();
/*    */   
/*    */   public static boolean enableError422BossFight;
/*    */   public static boolean enableMultiplayer;
/*    */   public static boolean disableAnticheat;
/*    */   public static boolean undefinedOptions;
/*    */   public static boolean disableStatic;
/*    */   public static boolean enableVHSOverlay;
/*    */   public static int averageEventDelay;
/*    */   public static double eventTimeScale;
/*    */   public static boolean advancedRender;
/*    */   public static boolean sillyMode;
/*    */   public static boolean extractExe;
/*    */   
/*    */   @SubscribeEvent
/*    */   static void onLoad(ModConfigEvent event) {
/* 82 */     enableError422BossFight = ((Boolean)ENABLE_ERR_422_BOSS_FIGHT.get()).booleanValue();
/* 83 */     enableMultiplayer = ((Boolean)ENABLE_MULTIPLAYER.get()).booleanValue();
/* 84 */     disableAnticheat = ((Boolean)DISABLE_ANTICHEAT.get()).booleanValue();
/* 85 */     undefinedOptions = ((Boolean)DISABLE_OPTIONS.get()).booleanValue();
/* 86 */     disableStatic = ((Boolean)DISABLE_STATIC.get()).booleanValue();
/* 87 */     enableVHSOverlay = ((Boolean)ENABLE_VHS_OVERLAY.get()).booleanValue();
/* 88 */     averageEventDelay = ((Integer)AVERAGE_EVENT_DELAY.get()).intValue() / 2;
/* 89 */     eventTimeScale = ((Double)EVENT_TIME_SCALE.get()).doubleValue();
/* 90 */     advancedRender = ((Boolean)ADVANCED_RENDER.get()).booleanValue();
/* 91 */     sillyMode = (((Boolean)ENFORCE_SILLY_MODE.get()).booleanValue() || isApril1st());
/* 92 */     extractExe = !((Boolean)DONT_ESCAPE_PLS.get()).booleanValue();
/*    */   }
/*    */   
/*    */   private static boolean isApril1st() {
/* 96 */     return (Calendar.getInstance().get(2) == 3 && Calendar.getInstance().get(5) == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Config.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */