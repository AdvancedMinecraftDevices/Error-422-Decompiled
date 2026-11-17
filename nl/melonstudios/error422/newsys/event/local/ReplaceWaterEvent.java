/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.minecraft.world.level.chunk.ChunkAccess;
/*    */ import net.minecraft.world.level.chunk.LevelChunkSection;
/*    */ import net.neoforged.neoforge.common.Tags;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class ReplaceWaterEvent extends LocalEvent {
/*    */   public ReplaceWaterEvent() {
/* 17 */     super(EventInterval.capture(Ticks.fromMinutes(30), Ticks.fromMinutes(40)), 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 22 */     ServerLevel level = player.serverLevel();
/* 23 */     BlockPos bp = player.blockPosition();
/* 24 */     for (int x = -16; x <= 16; x++) {
/* 25 */       for (int z = -16; z <= 16; z++) {
/* 26 */         for (int y = -16; y <= 16; y++) {
/* 27 */           BlockPos pos = bp.offset(x, y, z);
/* 28 */           BlockState old = level.getBlockState(pos);
/* 29 */           if (old.getFluidState().is(Tags.Fluids.WATER)) {
/* 30 */             ChunkAccess chunk = level.getChunk(pos);
/* 31 */             LevelChunkSection section = chunk.getSection(chunk.getSectionIndex(pos.getY()));
/* 32 */             section.setBlockState(pos.getX() & 0xF, pos.getY() & 0xF, pos.getZ() & 0xF, Blocks.LAVA.defaultBlockState());
/* 33 */             level.sendBlockUpdated(pos, old, Blocks.LAVA.defaultBlockState(), 2);
/* 34 */             chunk.setUnsaved(true);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\ReplaceWaterEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */