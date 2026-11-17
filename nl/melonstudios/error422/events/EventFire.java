/*    */ package nl.melonstudios.error422.events;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.player.Player;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.Blocks;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ 
/*    */ public class EventFire
/*    */   extends SpookyEvent {
/*    */   public EventFire() {
/* 14 */     super(64);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canOccur(Player player) {
/* 19 */     return (Err422PhaseManager.phase > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void occur(Player player) {
/* 24 */     RandomSource rnd = RandomSource.create();
/* 25 */     Level level = Objects.<Level>requireNonNull(player.level());
/* 26 */     BlockPos origin = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
/* 27 */     for (int x = -8; x <= 8; x++) {
/* 28 */       for (int y = -8; y <= 8; y++) {
/* 29 */         for (int z = -8; z <= 8; z++) {
/* 30 */           if (rnd.nextInt(5) == 0 && 
/* 31 */             x * x + y * y + z * z <= 64) {
/* 32 */             BlockPos pos = origin.offset(x, y, z);
/* 33 */             if (level.getBlockState(pos).canBeReplaced())
/* 34 */               level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3); 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\events\EventFire.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */