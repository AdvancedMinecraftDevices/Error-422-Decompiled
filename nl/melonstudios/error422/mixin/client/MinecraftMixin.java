/*    */ package nl.melonstudios.error422.mixin.client;
/*    */ 
/*    */ import com.mojang.blaze3d.platform.IconSet;
/*    */ import com.mojang.blaze3d.platform.Window;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.main.GameConfig;
/*    */ import net.minecraft.server.packs.VanillaPackResources;
/*    */ import net.minecraft.server.packs.repository.PackRepository;
/*    */ import net.minecraft.sounds.Music;
/*    */ import net.minecraft.sounds.Musics;
/*    */ import nl.melonstudios.error422.Err422PhaseManager;
/*    */ import nl.melonstudios.error422.Error422;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Overwrite;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({Minecraft.class})
/*    */ public abstract class MinecraftMixin
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private Window window;
/*    */   @Shadow
/*    */   @Final
/*    */   private VanillaPackResources vanillaPackResources;
/*    */   
/*    */   @Inject(method = {"getSituationalMusic"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void getSituationalMusic(CallbackInfoReturnable<Music> cir) {
/* 34 */     if (cir.getReturnValue() != Error422.SILENCE)
/* 35 */       if (cir.getReturnValue() == Musics.MENU) { cir.setReturnValue(Error422.MENU_MUSIC_REPLACEMENT); }
/*    */       
/* 37 */       else if (Err422PhaseManager.bossFightInEffect)
/* 38 */       { cir.setReturnValue(Error422.BOSSFIGHT_OST); }
/*    */       else
/* 40 */       { cir.setReturnValue(Error422.DEFAULT_MUSIC_REPLACEMENT); }
/*    */        
/*    */   } @Shadow
/*    */   private volatile boolean pause;
/*    */   @Shadow
/*    */   public abstract PackRepository getResourcePackRepository();
/*    */   @Inject(method = {"hasSingleplayerServer"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void hasSinglePlayerServer(CallbackInfoReturnable<Boolean> cir) {
/* 48 */     if (Err422PhaseManager.bossFightInEffect) cir.setReturnValue(Boolean.valueOf(false)); 
/*    */   }
/*    */   
/*    */   @Overwrite
/*    */   public String createTitle() {
/* 53 */     return "Ė̸̗̺̹̮̙̻̥̓̊̄̿̆̓̓͊R̵͑̂͆͜R̸̨͈̹͇̖̈͗͗̃͜͜͝Ô̵̢̧͓͉̞̒̑͗̽̏͑̄͘R̴̞̙̘̖͑̈́́̈̆̍̉4̷̧̗͔̯̟̾̀̈́̚͝2̶̧̜̼̝̭̼͂̐̂̏̾̌̽2̶̢̬͈̹̗͐̒";
/*    */   }
/*    */   
/*    */   @Inject(method = {"<init>"}, at = {@At("RETURN")})
/*    */   public void constructor(GameConfig gameConfig, CallbackInfo ci) {
/*    */     try {
/* 59 */       PackRepository packs = Minecraft.getInstance().getResourcePackRepository();
/* 60 */       this.window.setIcon(packs.getPack("mod_resources").open(), IconSet.RELEASE);
/* 61 */     } catch (Exception e) {
/* 62 */       System.err.println("ERR422 icon exception: " + e.getClass().getCanonicalName() + ": " + e.getLocalizedMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\mixin\client\MinecraftMixin.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */