/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.neoforged.neoforge.client.ClientTooltipFlag;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ 
/*    */ @Mixin({ClientTooltipFlag.class})
/*    */ public class ClientTooltipFlagMixin {
/*    */   @Overwrite
/*    */   public boolean isAdvanced() {
/* 11 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\ClientTooltipFlagMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */