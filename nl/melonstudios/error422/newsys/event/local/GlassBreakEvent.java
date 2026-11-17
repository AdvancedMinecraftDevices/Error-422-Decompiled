/*    */ package nl.melonstudios.error422.newsys.event.local;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.atomic.AtomicBoolean;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.server.level.ServerLevel;
/*    */ import net.minecraft.server.level.ServerPlayer;
/*    */ import net.minecraft.tags.TagKey;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import net.neoforged.neoforge.common.Tags;
/*    */ import nl.melonstudios.error422.newsys.event.EventInterval;
/*    */ import nl.melonstudios.error422.newsys.event.LocalEvent;
/*    */ import nl.melonstudios.error422.newsys.event.Ticks;
/*    */ 
/*    */ public class GlassBreakEvent
/*    */   extends LocalEvent {
/*    */   public GlassBreakEvent() {
/* 20 */     super(EventInterval.capture(Ticks.fromMinutes(10), Ticks.fromMinutes(20)), 1);
/*    */   }
/*    */   
/* 23 */   private static final List<TagKey<Block>> GLASS = List.of(Tags.Blocks.GLASS_BLOCKS, Tags.Blocks.GLASS_PANES, Tags.Blocks.GLASS_BLOCKS_CHEAP, Tags.Blocks.GLASS_BLOCKS_COLORLESS, Tags.Blocks.GLASS_BLOCKS_TINTED, Tags.Blocks.GLASS_PANES_COLORLESS);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void occur(ServerPlayer player) {
/* 34 */     List<BlockPos> candidates = new ArrayList<>();
/* 35 */     ServerLevel level = player.serverLevel();
/* 36 */     BlockPos bp = player.blockPosition();
/* 37 */     for (int x = -10; x <= 10; x++) {
/* 38 */       for (int z = -10; z <= 10; z++) {
/* 39 */         for (int y = -5; y <= 5; y++) {
/* 40 */           BlockPos pos = bp.offset(x, y, z);
/* 41 */           BlockState state = level.getBlockState(pos);
/* 42 */           AtomicBoolean flag = new AtomicBoolean(false);
/* 43 */           GLASS.forEach(tag -> {
/*    */                 if (state.is(tag))
/*    */                   flag.set(true); 
/* 46 */               }); if (flag.get()) candidates.add(pos); 
/*    */         } 
/*    */       } 
/*    */     } 
/* 50 */     if (!candidates.isEmpty()) {
/* 51 */       int max = Math.max(candidates.size() / 10, 1);
/* 52 */       int c = this.random.nextInt(max) + 1;
/* 53 */       for (int i = 0; i < c && !candidates.isEmpty(); i++) {
/* 54 */         BlockPos pos = candidates.remove(this.random.nextInt(candidates.size()));
/* 55 */         level.destroyBlock(pos, false);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\event\local\GlassBreakEvent.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */