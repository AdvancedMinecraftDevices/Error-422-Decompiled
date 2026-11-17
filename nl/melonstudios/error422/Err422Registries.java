/*    */ package nl.melonstudios.error422;
/*    */ 
/*    */ import net.minecraft.core.Registry;
/*    */ import net.minecraft.resources.ResourceKey;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.neoforged.neoforge.registries.RegistryBuilder;
/*    */ import nl.melonstudios.error422.events.SpookyEvent;
/*    */ 
/*    */ public class Err422Registries {
/* 10 */   private static int totalWeight = 0;
/*    */   public static int getTotalEventWeight() {
/* 12 */     return totalWeight;
/*    */   }
/*    */   
/* 15 */   public static final ResourceKey<Registry<SpookyEvent>> EVENTS_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("error422", "events"));
/*    */ 
/*    */   
/*    */   public static final Registry<SpookyEvent> EVENTS_REGISTRY;
/*    */ 
/*    */   
/*    */   static {
/* 22 */     EVENTS_REGISTRY = (new RegistryBuilder(EVENTS_REGISTRY_KEY)).sync(true).defaultKey(ResourceLocation.fromNamespaceAndPath("error422", "empty")).onAdd((registry, id, key, value) -> totalWeight += value.weight()).create();
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\Err422Registries.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */