/*    */ package nl.melonstudios.error422.entity;
/*    */ 
/*    */ import net.minecraft.client.model.HumanoidModel;
/*    */ import net.minecraft.client.model.geom.ModelPart;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.neoforged.api.distmarker.Dist;
/*    */ import net.neoforged.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class TheEntityKillModel extends HumanoidModel<TheEntityKill> {
/*    */   public TheEntityKillModel(ModelPart root) {
/* 12 */     super(root, RenderType::entityTranslucentEmissive);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\entity\TheEntityKillModel.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */