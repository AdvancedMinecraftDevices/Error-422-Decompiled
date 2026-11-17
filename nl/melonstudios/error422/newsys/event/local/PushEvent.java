/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.neoforged.neoforge.network.PacketDistributor;
/*    */ import nl.melonstudios.error422.net.PacketPush;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class PushEvent extends LocalEvent {
/*    */   public PushEvent() {
/* 12 */     super(EventInterval.capture(Ticks.fromMinutes(4), Ticks.fromMinutes(20)), 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 17 */     int angle = this.random.nextInt(360);
/* 18 */     PacketDistributor.sendToPlayer(player, (CustomPacketPayload)new PacketPush(angle), new CustomPacketPayload[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\PushEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */