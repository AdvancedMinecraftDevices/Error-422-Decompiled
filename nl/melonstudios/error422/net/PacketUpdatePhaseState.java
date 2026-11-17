/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import net.minecraft.network.codec.ByteBufCodecs;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public final class PacketUpdatePhaseState extends Record implements CustomPacketPayload {
/*    */   private final boolean error422Defeat;
/*    */   private final boolean bossFightInEffect;
/*    */   
/* 11 */   public PacketUpdatePhaseState(boolean error422Defeat, boolean bossFightInEffect) { this.error422Defeat = error422Defeat; this.bossFightInEffect = bossFightInEffect; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketUpdatePhaseState;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketUpdatePhaseState; } public boolean error422Defeat() { return this.error422Defeat; } public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketUpdatePhaseState;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketUpdatePhaseState; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketUpdatePhaseState;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketUpdatePhaseState;
/* 11 */     //   0	8	1	o	Ljava/lang/Object; } public boolean bossFightInEffect() { return this.bossFightInEffect; }
/* 12 */    public static final CustomPacketPayload.Type<PacketUpdatePhaseState> TYPE = new CustomPacketPayload.Type(
/* 13 */       ResourceLocation.parse("error422:update_phase_state"));
/* 14 */   public static final StreamCodec<ByteBuf, PacketUpdatePhaseState> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketUpdatePhaseState::error422Defeat, ByteBufCodecs.BOOL, PacketUpdatePhaseState::bossFightInEffect, PacketUpdatePhaseState::new);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
/* 23 */     return (CustomPacketPayload.Type)TYPE;
/*    */   }
/*    */   
/*    */   public void handle(IPayloadContext context) {
/* 27 */     Err422PhaseManager.error422Defeat = this.error422Defeat;
/* 28 */     Err422PhaseManager.bossFightInEffect = this.bossFightInEffect;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketUpdatePhaseState.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */