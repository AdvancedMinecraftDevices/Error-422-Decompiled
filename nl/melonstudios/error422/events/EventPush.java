/*    */ package nl.melonstudios.error422.events;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.neoforged.neoforge.network.PacketDistributor;
/*    */ import nl.melonstudios.error422.net.PacketPush;
/*    */ 
/*    */ public class EventPush extends SpookyEvent {
/* 10 */   private final RandomSource random = RandomSource.create();
/*    */   protected EventPush() {
/* 12 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 17 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 22 */     int angle = this.random.nextInt(360);
/* 23 */     if (player instanceof ServerPlayer) { ServerPlayer sp = (ServerPlayer)player; PacketDistributor.sendToPlayer(sp, (CustomPacketPayload)new PacketPush(angle), new CustomPacketPayload[0]); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventPush.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */