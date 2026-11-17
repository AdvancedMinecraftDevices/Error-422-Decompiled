/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({WorldCreationUiState.class})
/*    */ public abstract class WorldCreationUiStateMixin
/*    */ {
/*    */   @Shadow
/*    */   private String targetFolder;
/*    */   @Shadow
/*    */   private String name;
/*    */   
/*    */   @Overwrite
/*    */   public void setName(String name) {
/* 22 */     String obf = Error422.hexName(name);
/* 23 */     this.name = obf;
/* 24 */     this.targetFolder = findResultFolder(obf);
/* 25 */     onChanged();
/*    */   }
/*    */   
/*    */   @Shadow
/*    */   protected abstract String findResultFolder(String paramString);
/*    */   
/*    */   @Shadow
/*    */   public abstract void onChanged();
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\WorldCreationUiStateMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */