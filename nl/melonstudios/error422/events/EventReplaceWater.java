/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public class EventReplaceWater
/*    */   extends SpookyEvent {
/*    */   public EventReplaceWater() {
/* 14 */     super(8);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 19 */     return (Err422PhaseManager.phase > 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 24 */     Level level = Objects.<Level>requireNonNull(player.level());
/* 25 */     BlockPos origin = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
/*    */     
/* 27 */     for (int x = -8; x <= 8; x++) {
/* 28 */       for (int z = -8; z <= 8; z++) {
/* 29 */         for (int y = 8; y >= -8; y--) {
/* 30 */           BlockPos pos = origin.offset(x, y, z);
/* 31 */           if (level.getFluidState(pos).is(FluidTags.WATER))
/* 32 */             level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 18); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventReplaceWater.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */