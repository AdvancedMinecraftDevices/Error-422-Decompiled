/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.neoforged.neoforge.network.handling.IPayloadContext;
/*    */ 
/*    */ public final class PacketPush extends Record implements CustomPacketPayload {
/*    */   private final int angle;
/*    */   
/* 11 */   public PacketPush(int angle) { this.angle = angle; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketPush;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketPush; } public int angle() { return this.angle; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketPush;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketPush; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketPush;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketPush;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketPush> TYPE = new CustomPacketPayload.Type(ResourceLocation.fromNamespaceAndPath("error422", "push"));
/* 13 */   public static final StreamCodec<ByteBuf, PacketPush> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, PacketPush::angle, PacketPush::new);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
/* 21 */     return (CustomPacketPayload.Type)TYPE;
/*    */   }
/*    */   
/*    */   public void handle(IPayloadContext context) {
/* 25 */     double sin = Math.sin(Math.toRadians(this.angle)) * 0.4D;
/* 26 */     double cos = Math.cos(Math.toRadians(this.angle)) * 0.4D;
/* 27 */     context.player().addDeltaMovement(new Vec3(sin, 0.1D, cos));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketPush.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */