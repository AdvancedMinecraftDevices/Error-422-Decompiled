/*    */ package nl.melonstudios.error422.mixin.common;
/*    */ 
/*    */ import net.minecraft.ChatFormatting;
/*    */ import net.minecraft.network.chat.Component;
/*    */ import net.minecraft.world.item.Item;
/*    */ import net.minecraft.world.item.ItemStack;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ 
/*    */ @Mixin({ItemStack.class})
/*    */ public abstract class ItemStackMixin
/*    */ {
/*    */   @Inject(method = {"getHoverName"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getHoverName(CallbackInfoReturnable<Component> cir) {
/* 20 */     Item item = getItem();
/* 21 */     if ((item.hashCode() & 0x1) == 0) cir.setReturnValue(Component.empty().append((Component)cir.getReturnValue()).withStyle(ChatFormatting.OBFUSCATED)); 
/*    */   } @Shadow
/*    */   public abstract Item getItem();
/*    */   @Inject(method = {"getMaxStackSize"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getMaxStackSize(CallbackInfoReturnable<Integer> cir) {
/* 26 */     if (((Integer)cir.getReturnValue()).intValue() == 64) cir.setReturnValue(Integer.valueOf(Error422.getMaxStackSize())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\common\ItemStackMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */