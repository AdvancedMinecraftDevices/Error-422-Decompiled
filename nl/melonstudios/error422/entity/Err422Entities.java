/*    */ package nl.melonstudios.error422.entity;
/*    */ 
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.MobCategory;
/*    */ import net.neoforged.bus.api.IEventBus;
/*    */ import net.neoforged.neoforge.registries.DeferredHolder;
/*    */ import net.neoforged.neoforge.registries.DeferredRegister;
/*    */ 
/*    */ public class Err422Entities {
/* 12 */   private static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create((Registry)BuiltInRegistries.ENTITY_TYPE, "error422");
/*    */   
/* 14 */   public static final DeferredHolder<EntityType<?>, EntityType<TheEntityCrash>> THE_ENTITY_CRASH = REGISTER.register("the_entity_crash", () -> EntityType.Builder.of(TheEntityCrash::new, MobCategory.MISC).clientTrackingRange(128).sized(0.8F, 1.8F).noSave().noSummon().build("error422:the_entity_crash"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public static final DeferredHolder<EntityType<?>, EntityType<TheEntityKill>> THE_ENTITY_KILL = REGISTER.register("the_entity_kill", () -> EntityType.Builder.of(TheEntityKill::new, MobCategory.MISC).clientTrackingRange(128).sized(0.8F, 1.8F).noSave().noSummon().fireImmune().build("error422:the_entity_kill"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public static final DeferredHolder<EntityType<?>, EntityType<TheEntityFight>> THE_ENTITY_FIGHT = REGISTER.register("the_entity_fight", () -> EntityType.Builder.of(TheEntityFight::new, MobCategory.MISC).clientTrackingRange(1024).sized(0.8F, 1.8F).noSave().noSummon().fireImmune().build("error422:the_entity_fight"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 45 */     REGISTER.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\entity\Err422Entities.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */