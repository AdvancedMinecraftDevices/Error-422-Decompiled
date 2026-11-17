/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.CactusBlock;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({CactusBlock.class})
/*    */ public class CactusBlockMixin
/*    */ {
/*    */   @Overwrite
/*    */   protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
/* 18 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\CactusBlockMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */