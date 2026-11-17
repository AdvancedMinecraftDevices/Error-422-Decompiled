/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.neoforged.neoforge.common.Tags;
/*    */ import net.neoforged.neoforge.network.PacketDistributor;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import nl.melonstudios.error422.net.PacketEnforceCaveNoise;
/*    */ 
/*    */ public class EventBreakGlass extends SpookyEvent {
/*    */   public EventBreakGlass() {
/* 19 */     super(32);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 24 */     return (Err422PhaseManager.phase > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 29 */     Level level = Objects.<Level>requireNonNull(player.level());
/* 30 */     BlockPos pos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
/* 31 */     List<BlockPos> candidates = new ArrayList<>();
/* 32 */     for (int x = -8; x <= 8; x++) {
/* 33 */       for (int y = -4; y <= 4; y++) {
/* 34 */         for (int z = -8; z <= 8; z++) {
/* 35 */           BlockPos bp = pos.offset(x, y, z);
/* 36 */           if (level.getBlockState(pos).is(Tags.Blocks.GLASS_BLOCKS) || level.getBlockState(pos).is(Tags.Blocks.GLASS_PANES)) {
/* 37 */             candidates.add(bp);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 42 */     if (!candidates.isEmpty())
/* 43 */     { RandomSource rnd = RandomSource.create();
/* 44 */       BlockPos bp = candidates.get(rnd.nextInt(candidates.size()));
/* 45 */       level.destroyBlock(bp, false); }
/* 46 */     else if (player instanceof ServerPlayer) { ServerPlayer sp = (ServerPlayer)player;
/* 47 */       PacketDistributor.sendToPlayer(sp, (CustomPacketPayload)new PacketEnforceCaveNoise(true), new CustomPacketPayload[0]); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventBreakGlass.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */