/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ import net.neoforged.neoforge.network.handling.IPayloadContext;
/*    */ 
/*    */ public final class PacketEnforceCaveNoise extends Record implements CustomPacketPayload {
/*    */   private final boolean idk;
/*    */   
/* 11 */   public PacketEnforceCaveNoise(boolean idk) { this.idk = idk; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketEnforceCaveNoise;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketEnforceCaveNoise; } public boolean idk() { return this.idk; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketEnforceCaveNoise;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketEnforceCaveNoise; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketEnforceCaveNoise;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketEnforceCaveNoise;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketEnforceCaveNoise> TYPE = new CustomPacketPayload.Type(
/* 13 */       ResourceLocation.fromNamespaceAndPath("error422", "enforce_cave_noise"));
/* 14 */   public static final StreamCodec<ByteBuf, PacketEnforceCaveNoise> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.BOOL, PacketEnforceCaveNoise::idk, PacketEnforceCaveNoise::new);
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
/* 26 */     context.player().playSound((SoundEvent)SoundEvents.AMBIENT_CAVE.value(), 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketEnforceCaveNoise.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */