/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.util.RandomSource;
/*    */ import net.minecraft.world.entity.EntityType;
/*    */ import net.minecraft.world.entity.monster.Creeper;
/*    */ import net.minecraft.world.level.Level;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ @Mixin({Creeper.class})
/*    */ public class CreeperMixin
/*    */ {
/*    */   @Inject(method = {"<init>"}, at = {@At("RETURN")})
/*    */   public void constructor(EntityType entityType, Level level, CallbackInfo ci) {
/* 19 */     if (!level.isClientSide() && RandomSource.create().nextInt(4) == 0) this.maxSwell = 5; 
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   private int maxSwell;
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\CreeperMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */