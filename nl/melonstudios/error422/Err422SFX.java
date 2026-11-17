/*    */ package nl.melonstudios.error422;
/*    */ 
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.minecraft.sounds.SoundEvent;
/*    */ import net.neoforged.bus.api.IEventBus;
/*    */ import net.neoforged.neoforge.registries.DeferredHolder;
/*    */ import net.neoforged.neoforge.registries.DeferredRegister;
/*    */ 
/*    */ public class Err422SFX
/*    */ {
/* 12 */   private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "error422");
/*    */   
/* 14 */   public static final DeferredHolder<SoundEvent, SoundEvent> g = SOUNDS.register("g", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:g"), 16.0F));
/*    */   
/* 16 */   public static final DeferredHolder<SoundEvent, SoundEvent> g422 = SOUNDS.register("g422", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:g422"), 16.0F));
/*    */   
/* 18 */   public static final DeferredHolder<SoundEvent, SoundEvent> n = SOUNDS.register("n", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:n"), 16.0F));
/*    */   
/* 20 */   public static final DeferredHolder<SoundEvent, SoundEvent> th = SOUNDS.register("th", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:th"), 16.0F));
/*    */   
/* 22 */   public static final DeferredHolder<SoundEvent, SoundEvent> te = SOUNDS.register("te", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:te"), 1024.0F));
/*    */   
/* 24 */   public static final DeferredHolder<SoundEvent, SoundEvent> s = SOUNDS.register("s", () -> SoundEvent.createFixedRangeEvent(ResourceLocation.parse("error422:s"), 0.0F));
/*    */ 
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 28 */     SOUNDS.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422SFX.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */