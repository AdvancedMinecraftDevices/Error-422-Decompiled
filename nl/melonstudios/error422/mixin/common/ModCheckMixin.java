/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.util.ModCheck;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ 
/*    */ @Mixin({ModCheck.class})
/*    */ public class ModCheckMixin {
/*    */   @Overwrite
/*    */   public boolean shouldReportAsModified() {
/* 11 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\ModCheckMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */