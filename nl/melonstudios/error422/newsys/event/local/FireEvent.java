/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.core.Direction;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class FireEvent extends LocalEvent {
/*    */   public FireEvent() {
/* 14 */     super(EventInterval.capture(Ticks.fromMinutes(20), Ticks.fromMinutes(40)), 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 19 */     ServerLevel level = player.serverLevel();
/* 20 */     BlockPos bp = player.blockPosition();
/* 21 */     for (int x = -5; x <= 5; x++) {
/* 22 */       for (int z = -5; z <= 5; z++) {
/* 23 */         for (int y = -5; y <= 5; y++) {
/* 24 */           if (x * x + y * y + z * z <= 25 && this.random.nextInt(4) == 0) {
/* 25 */             BlockPos pos = bp.offset(x, y, z);
/* 26 */             if (level.getBlockState(pos).canBeReplaced() && 
/* 27 */               level.getBlockState(pos.below()).isFaceSturdy((BlockGetter)level, pos.below(), Direction.UP))
/* 28 */               level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3); 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\FireEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */