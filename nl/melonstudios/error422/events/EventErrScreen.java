/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.NonNullList;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.player.Inventory;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import net.neoforged.neoforge.network.PacketDistributor;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import nl.melonstudios.error422.net.PacketNotifyErrScreen;
/*    */ 
/*    */ public class EventErrScreen extends SpookyEvent {
/*    */   public EventErrScreen() {
/* 20 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 25 */     return (Err422PhaseManager.phase > 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 30 */     enact(player, 200);
/*    */   }
/*    */   
/*    */   public static void enact(Player player, int duration) {
/* 34 */     if (player instanceof ServerPlayer) { ServerPlayer sp = (ServerPlayer)player;
/* 35 */       PacketDistributor.sendToPlayer(sp, (CustomPacketPayload)new PacketNotifyErrScreen(duration), new CustomPacketPayload[0]);
/* 36 */       errScreenMap.put(sp, Integer.valueOf(duration)); }
/*    */   
/*    */   }
/*    */   
/* 40 */   public static final HashMap<ServerPlayer, Integer> errScreenMap = new HashMap<>();
/*    */   public static void tick() {
/* 42 */     RandomSource rand = RandomSource.create();
/* 43 */     for (ServerPlayer sp : errScreenMap.keySet()) {
/* 44 */       Inventory inventory = sp.getInventory();
/* 45 */       NonNullList<ItemStack> items = inventory.items;
/* 46 */       NonNullList<ItemStack> copy = NonNullList.withSize(items.size(), ItemStack.EMPTY);
/* 47 */       List<Integer> freeSpaces = new ArrayList<>();
/* 48 */       List<Integer> scrambledSpaces = new ArrayList<>(); int i;
/* 49 */       for (i = 0; i < items.size(); i++) {
/* 50 */         freeSpaces.add(Integer.valueOf(i));
/*    */       }
/* 52 */       for (i = 0; i < items.size(); i++) {
/* 53 */         int idx = rand.nextInt(freeSpaces.size());
/* 54 */         int ext = ((Integer)freeSpaces.remove(idx)).intValue();
/* 55 */         scrambledSpaces.add(Integer.valueOf(ext));
/*    */       } 
/* 57 */       for (i = 0; i < items.size(); i++) {
/* 58 */         copy.set(i, items.get(i));
/*    */       }
/* 60 */       for (ItemStack stack : copy) {
/* 61 */         items.set(((Integer)scrambledSpaces.removeFirst()).intValue(), stack);
/*    */       }
/*    */     } 
/*    */     
/* 65 */     ArrayList<ServerPlayer> bin = new ArrayList<>();
/* 66 */     for (ServerPlayer sp : errScreenMap.keySet()) {
/* 67 */       int i = ((Integer)errScreenMap.get(sp)).intValue();
/* 68 */       if (i > 1) { errScreenMap.put(sp, Integer.valueOf(i - 1)); continue; }
/* 69 */        bin.add(sp);
/*    */     } 
/* 71 */     Objects.requireNonNull(errScreenMap); bin.forEach(errScreenMap::remove);
/* 72 */     bin.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventErrScreen.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */