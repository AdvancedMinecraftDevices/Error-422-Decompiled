/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.BlockGetter;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.CropBlock;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ 
/*    */ @Mixin({CropBlock.class})
/*    */ public class CropBlockMixin {
/*    */   @Overwrite
/*    */   public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
/* 15 */     return true;
/*    */   }
/*    */   
/*    */   @Overwrite
/*    */   public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
/* 20 */     return true;
/*    */   }
/*    */   
/*    */   @Overwrite
/*    */   protected static float getGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos) {
/* 25 */     return 1.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\CropBlockMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */