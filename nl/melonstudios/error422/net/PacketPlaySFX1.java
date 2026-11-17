/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.neoforged.neoforge.network.handling.IPayloadContext;
/*    */ 
/*    */ public final class PacketPlaySFX1 extends Record implements CustomPacketPayload {
/*    */   private final boolean b;
/*    */   
/* 11 */   public PacketPlaySFX1(boolean b) { this.b = b; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketPlaySFX1;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketPlaySFX1; } public boolean b() { return this.b; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketPlaySFX1;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketPlaySFX1; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketPlaySFX1;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketPlaySFX1;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketPlaySFX1> TYPE = new CustomPacketPayload.Type(ResourceLocation.fromNamespaceAndPath("error422", "play_sfx_1"));
/* 13 */   public static final StreamCodec<ByteBuf, PacketPlaySFX1> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketPlaySFX1::b, PacketPlaySFX1::new);
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
/* 25 */     context.player().playSound((SoundEvent)Err422SFX.g.get(), 1.0F, this.b ? 2.0F : 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketPlaySFX1.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */