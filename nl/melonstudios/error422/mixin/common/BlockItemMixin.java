/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.core.Holder;
/*    */ import net.minecraft.core.registries.BuiltInRegistries;
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.item.BlockItem;
/*    */ import net.minecraft.world.item.context.BlockPlaceContext;
/*    */ import net.minecraft.world.level.block.Block;
/*    */ import net.minecraft.world.level.block.state.BlockState;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Unique;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({BlockItem.class})
/*    */ public abstract class BlockItemMixin {
/*    */   @Unique
/* 20 */   private final RandomSource error422$random = RandomSource.createThreadSafe();
/*    */   
/*    */   @Inject(method = {"getPlacementState"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getPlacementState(BlockPlaceContext context, CallbackInfoReturnable<BlockState> cir) {
/* 24 */     if (!context.getLevel().isClientSide() && this.error422$random.nextFloat() < 0.01F) {
/* 25 */       Optional<Holder.Reference<Block>> optionalBlockReference = BuiltInRegistries.BLOCK.getRandom(this.error422$random);
/* 26 */       if (optionalBlockReference.isPresent()) {
/* 27 */         Holder.Reference<Block> blockReference = optionalBlockReference.get();
/* 28 */         Block block = (Block)BuiltInRegistries.BLOCK.get(blockReference.getKey());
/* 29 */         if (block != null) cir.setReturnValue(block.defaultBlockState()); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Unique
/*    */   private static final boolean enforce_random = false;
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\BlockItemMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */