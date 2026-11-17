/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.IconSet;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.packs.PackResources;
/*    */ import net.minecraft.server.packs.resources.IoSupplier;
/*    */ import org.apache.commons.lang3.ArrayUtils;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({IconSet.class})
/*    */ public abstract class IconSetMixin
/*    */ {
/*    */   @Overwrite
/*    */   private IoSupplier<InputStream> getFile(PackResources resources, String filename) throws IOException {
/* 23 */     (new String[1])[0] = filename; String[] astring = filename.startsWith("assets/") ? new String[1] : (String[])ArrayUtils.add((Object[])this.path, filename);
/* 24 */     IoSupplier<InputStream> iosupplier = resources.getRootResource(astring);
/* 25 */     if (iosupplier == null) {
/* 26 */       throw new FileNotFoundException(String.join("/", astring));
/*    */     }
/* 28 */     return iosupplier;
/*    */   } @Shadow
/*    */   @Final
/*    */   private String[] path; @Overwrite
/*    */   public List<IoSupplier<InputStream>> getStandardIcons(PackResources resources) throws IOException {
/* 33 */     if (resources.location().id().equals("mod_resources")) {
/* 34 */       return List.of(
/* 35 */           getFile(resources, "assets/error422/textures/gui/icon_16x16.png"), 
/* 36 */           getFile(resources, "assets/error422/textures/gui/icon_32x32.png"), 
/* 37 */           getFile(resources, "assets/error422/textures/gui/icon_48x48.png"), 
/* 38 */           getFile(resources, "assets/error422/textures/gui/icon_128x128.png"), 
/* 39 */           getFile(resources, "assets/error422/textures/gui/icon_256x256.png"));
/*    */     }
/*    */     
/* 42 */     return List.of(
/* 43 */         getFile(resources, "icon_16x16.png"), 
/* 44 */         getFile(resources, "icon_32x32.png"), 
/* 45 */         getFile(resources, "icon_48x48.png"), 
/* 46 */         getFile(resources, "icon_128x128.png"), 
/* 47 */         getFile(resources, "icon_256x256.png"));
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\IconSetMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */