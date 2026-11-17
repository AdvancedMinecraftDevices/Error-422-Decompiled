/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.level.Level;
/*    */ import net.minecraft.world.level.block.BedBlock;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({BedBlock.class})
/*    */ public class BedBlockMixin {
/*    */   @Inject(method = {"canSetSpawn"}, at = {@At("RETURN")}, cancellable = true)
/*    */   private static void canSetSpawn(Level level, CallbackInfoReturnable<Boolean> cir) {
/* 15 */     if (cir.getReturnValue() == Boolean.TRUE)
/* 16 */       cir.setReturnValue(Boolean.valueOf((RandomSource.create().nextFloat() > 0.01F))); 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\BedBlockMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */