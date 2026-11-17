/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.neoforged.bus.api.IEventBus;
/*    */ import net.neoforged.neoforge.registries.DeferredHolder;
/*    */ import net.neoforged.neoforge.registries.DeferredRegister;
/*    */ import nl.melonstudios.error422.Err422Registries;
/*    */ import nl.melonstudios.error422.newsys.ErrMessageProvider;
/*    */ 
/*    */ public class Err422Events {
/* 11 */   private static final DeferredRegister<SpookyEvent> EVENTS = DeferredRegister.create(Err422Registries.EVENTS_REGISTRY_KEY, "error422");
/*    */   
/* 13 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> LIGHTNING = EVENTS.register("lightning", EventLightning::new);
/*    */ 
/*    */ 
/*    */   
/* 17 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> ERROR_DUMP = EVENTS.register("error_dump", () -> new EventRandomErrorMessage(ErrMessageProvider.ERROR_TYPES));
/*    */ 
/*    */ 
/*    */   
/* 21 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> RANDOM_EFFECT = EVENTS.register("random_effect", EventRandomMobEffect::new);
/*    */ 
/*    */ 
/*    */   
/* 25 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> PUSH = EVENTS.register("push", EventPush::new);
/*    */ 
/*    */ 
/*    */   
/* 29 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> ERR_SCREEN = EVENTS.register("err_screen", EventErrScreen::new);
/*    */ 
/*    */ 
/*    */   
/* 33 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> TIME_CHANGE = EVENTS.register("time_change", EventTimeChange::new);
/*    */ 
/*    */ 
/*    */   
/* 37 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> FIRE = EVENTS.register("fire", EventFire::new);
/*    */ 
/*    */ 
/*    */   
/* 41 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> BREAK_GLASS = EVENTS.register("break_glass", EventBreakGlass::new);
/*    */ 
/*    */ 
/*    */   
/* 45 */   public static final DeferredHolder<SpookyEvent, SpookyEvent> LAVA = EVENTS.register("lava", EventReplaceWater::new);
/*    */ 
/*    */ 
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 50 */     EVENTS.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\Err422Events.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */