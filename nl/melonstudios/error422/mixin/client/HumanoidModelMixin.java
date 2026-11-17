/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Collections;
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.PlayerModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({HumanoidModel.class})
/*    */ public class HumanoidModelMixin {
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart body;
/*    */   @Shadow
/*    */   @Final
/*    */   public ModelPart rightArm;
/*    */   
/*    */   @Inject(method = {"headParts"}, at = {@At("RETURN")}, cancellable = true)
/*    */   protected void headParts(CallbackInfoReturnable<Iterable<ModelPart>> cir) {
/* 26 */     if (getClass() == PlayerModel.class)
/* 27 */       cir.setReturnValue(Collections.emptyList());  } @Shadow @Final
/*    */   public ModelPart leftArm; @Shadow
/*    */   @Final
/*    */   public ModelPart rightLeg; @Shadow
/*    */   @Final
/*    */   public ModelPart leftLeg; @Inject(method = {"bodyParts"}, at = {@At("RETURN")}, cancellable = true)
/* 33 */   public void bodyParts(CallbackInfoReturnable<Iterable<ModelPart>> cir) { if (getClass() == PlayerModel.class)
/* 34 */       cir.setReturnValue(ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg));  }
/*    */ 
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\HumanoidModelMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */