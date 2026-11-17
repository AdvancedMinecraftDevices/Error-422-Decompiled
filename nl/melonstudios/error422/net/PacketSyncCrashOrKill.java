/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public final class PacketSyncCrashOrKill extends Record implements CustomPacketPayload {
/*    */   private final boolean crashOrKill;
/*    */   
/* 11 */   public PacketSyncCrashOrKill(boolean crashOrKill) { this.crashOrKill = crashOrKill; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketSyncCrashOrKill;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketSyncCrashOrKill; } public boolean crashOrKill() { return this.crashOrKill; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketSyncCrashOrKill;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketSyncCrashOrKill; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketSyncCrashOrKill;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketSyncCrashOrKill;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketSyncCrashOrKill> TYPE = new CustomPacketPayload.Type(
/* 13 */       ResourceLocation.fromNamespaceAndPath("error422", "sync_crash_kill"));
/* 14 */   public static final StreamCodec<ByteBuf, PacketSyncCrashOrKill> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketSyncCrashOrKill::crashOrKill, PacketSyncCrashOrKill::new);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
/* 22 */     return (CustomPacketPayload.Type)TYPE;
/*    */   }
/*    */   
/*    */   public void handle(IPayloadContext context) {
/* 26 */     Err422PhaseManager.crashOrKill = crashOrKill();
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketSyncCrashOrKill.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */