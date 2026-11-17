/*    */ package nl.melonstudios.error422.net;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import net.minecraft.network.codec.StreamCodec;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.resources.ResourceLocation;
/*    */ 
/*    */ public final class PacketSyncStackSize extends Record implements CustomPacketPayload {
/*    */   private final int size;
/*    */   
/* 11 */   public PacketSyncStackSize(int size) { this.size = size; } public final String toString() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> toString : (Lnl/melonstudios/error422/net/PacketSyncStackSize;)Ljava/lang/String;
/*    */     //   6: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/* 11 */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketSyncStackSize; } public int size() { return this.size; }
/*    */   public final int hashCode() { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: <illegal opcode> hashCode : (Lnl/melonstudios/error422/net/PacketSyncStackSize;)I
/*    */     //   6: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	7	0	this	Lnl/melonstudios/error422/net/PacketSyncStackSize; } public final boolean equals(Object o) { // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: <illegal opcode> equals : (Lnl/melonstudios/error422/net/PacketSyncStackSize;Ljava/lang/Object;)Z
/*    */     //   7: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #11	-> 0
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	8	0	this	Lnl/melonstudios/error422/net/PacketSyncStackSize;
/* 12 */     //   0	8	1	o	Ljava/lang/Object; } public static final CustomPacketPayload.Type<PacketSyncStackSize> TYPE = new CustomPacketPayload.Type(ResourceLocation.fromNamespaceAndPath("error422", "sync_stack_size"));
/* 13 */   public static final StreamCodec<ByteBuf, PacketSyncStackSize> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, PacketSyncStackSize::size, PacketSyncStackSize::new);
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
/* 25 */     Error422.setMaxStackSize(this.size);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\net\PacketSyncStackSize.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */