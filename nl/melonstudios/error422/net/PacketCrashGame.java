/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import nl.melonstudios.error422.Err422Client;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ 
/*    */ public final class PacketCrashGame extends Record implements CustomPacketPayload {
/*    */   private final boolean soundOnly;
/*    */   
/* 12 */   public PacketCrashGame(boolean soundOnly) { this.soundOnly = soundOnly; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketCrashGame;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 12 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketCrashGame; } public boolean soundOnly() { return this.soundOnly; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketCrashGame;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketCrashGame; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketCrashGame;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #12	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketCrashGame;
/* 13 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketCrashGame> TYPE = new CustomPacketPayload.Type(ResourceLocation.fromNamespaceAndPath("error422", "crash"));
/* 14 */   public static final StreamCodec<ByteBuf, PacketCrashGame> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketCrashGame::soundOnly, PacketCrashGame::new);
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
/* 26 */     if (this.soundOnly)
/* 27 */     { if (Error422.dist.isClient()) Err422Client.playCrashNoise();
/*    */        }
/* 29 */     else if (Error422.dist.isClient()) { Err422Client.endgameCrash(); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketCrashGame.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */