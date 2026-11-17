/*    */ package nl.melonstudios.error422.newsys;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ 
/*    */ public class ErrMessageProvider
/*    */ {
/* 10 */   public static final List<String> ERROR_TYPES = List.of("GL ERROR", "SECURITY ERROR", "RENDER ERROR", "CHUNK ERROR", "?????? ERROR", "OPENAL ERROR", "LWJGL ERROR", "JINPUT ERROR", "UNKNOWN ERROR");
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
/* 21 */   public static final List<String> ERROR_TYPES_SERVER = List.of("CHUNK ERROR", "?????? ERROR", "UNKNOWN ERROR", "CHUNK ERROR", "SECURITY ERROR");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   public static final List<String> ERROR_TYPES_CLIENT = List.of("GL ERROR", "RENDER ERROR", "OPENAL ERROR", "LWJGL ERROR", "JINPUT ERROR");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Component provide(String forcedError) {
/* 37 */     RandomSource random = RandomSource.create();
/* 38 */     String obf = "Null".repeat(random.nextInt(8, 12));
/*    */     
/* 40 */     return (Component)Component.literal(String.format("§4# %s §r§e[dump: §k%s§r§e]", new Object[] { forcedError, obf }));
/*    */   }
/*    */   public static Component provide() {
/* 43 */     RandomSource random = RandomSource.create();
/* 44 */     String error = ERROR_TYPES.get(random.nextInt(ERROR_TYPES.size()));
/* 45 */     String obf = "Null".repeat(random.nextInt(8, 12));
/*    */     
/* 47 */     return (Component)Component.literal(String.format("§4# %s §r§e[dump: §k%s§r§e]", new Object[] { error, obf }));
/*    */   }
/*    */   public static Component provideWithContext(Player player) {
/* 50 */     if (player.getName().getString().toLowerCase().contains("swayle")) {
/* 51 */       String obf = "Null".repeat(RandomSource.create().nextInt(8, 12));
/* 52 */       return (Component)Component.literal(String.format("§4# %s §r§e[dump: §k%s§r§e]", new Object[] { "SCAMMER ERROR", obf }));
/*    */     } 
/* 54 */     return provide();
/*    */   }
/*    */   public static Component provideServer() {
/* 57 */     RandomSource random = RandomSource.create();
/* 58 */     String error = ERROR_TYPES_SERVER.get(random.nextInt(ERROR_TYPES_SERVER.size()));
/* 59 */     String obf = "Null".repeat(random.nextInt(8, 12));
/*    */     
/* 61 */     return (Component)Component.literal(String.format("§4# %s §r§e[dump: §k%s§r§e]", new Object[] { error, obf }));
/*    */   }
/*    */   public static Component provideClient() {
/* 64 */     RandomSource random = RandomSource.create();
/* 65 */     String error = ERROR_TYPES_CLIENT.get(random.nextInt(ERROR_TYPES_CLIENT.size()));
/* 66 */     String obf = "Null".repeat(random.nextInt(8, 12));
/*    */     
/* 68 */     return (Component)Component.literal(String.format("§4# %s §r§e[dump: §k%s§r§e]", new Object[] { error, obf }));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\ErrMessageProvider.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */