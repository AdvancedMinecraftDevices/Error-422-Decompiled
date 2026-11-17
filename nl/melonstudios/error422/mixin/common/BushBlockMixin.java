/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.core.BlockPos;
/*    */ import net.minecraft.world.level.LevelReader;
/*    */ import net.minecraft.world.level.block.BushBlock;
/*    */ import net.minecraft.world.level.block.WaterlilyBlock;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({BushBlock.class})
/*    */ public class BushBlockMixin {
/*    */   @Inject(method = {"canSurvive"}, at = {@At("RETURN")}, cancellable = true)
/*    */   protected void canSurvive(BlockState state, LevelReader level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
/* 17 */     if (getClass() == WaterlilyBlock.class) cir.setReturnValue(Boolean.valueOf(true)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\BushBlockMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */