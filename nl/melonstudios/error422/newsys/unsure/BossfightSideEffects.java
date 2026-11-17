/*     */ package nl.melonstudios.error422.newsys.unsure;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Locale;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.neoforged.fml.ModContainer;
/*     */ import net.neoforged.fml.ModList;
/*     */ import nl.melonstudios.error422.Config;
/*     */ import nl.melonstudios.error422.Err422PhaseManager;
/*     */ import nl.melonstudios.error422.Error422;
/*     */ import nl.melonstudios.error422.entity.TheEntityFight;
/*     */ 
/*     */ public class BossfightSideEffects
/*     */ {
/*  22 */   public static TheEntityFight theGlitch = null; public static final int KiB = 1024;
/*     */   
/*     */   public static void onWin() {
/*  25 */     Err422PhaseManager.error422Defeat = true;
/*     */   }
/*     */   
/*     */   public static void onLose() {
/*  29 */     if (Config.extractExe) {
/*  30 */       File file = findMyJar();
/*  31 */       File exe = Err422Files.desktop("ERROR422.exe");
/*  32 */       if (file != null) {
/*     */         
/*  34 */         try { JarFile jar = new JarFile(file);
/*     */           
/*  36 */           try { JarEntry jentry = findError422Exe(jar);
/*  37 */             if (jentry != null) {
/*  38 */               extractERROR422To(exe, jar, jentry);
/*     */             }
/*  40 */             jar.close(); } catch (Throwable throwable) { try { jar.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException e)
/*  41 */         { couldNotEscapeError(e); }
/*     */       
/*     */       } else {
/*  44 */         NullPointerException npe = new NullPointerException("JAR file is null");
/*  45 */         couldNotEscapeError(npe);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static File findMyJar() {
/*  62 */     Minecraft mc = Minecraft.getInstance();
/*  63 */     File modsFolder = new File(mc.gameDirectory, "mods");
/*  64 */     assert modsFolder.exists() && modsFolder.isDirectory();
/*  65 */     File[] files = modsFolder.listFiles(file -> file.getName().endsWith(".jar"));
/*  66 */     File bestCandidate = null;
/*  67 */     String version = ((ModContainer)ModList.get().getModContainerById("error422").get()).getModInfo().getVersion().toString();
/*  68 */     String bestName = String.format("error422-%s.jar", new Object[] { version });
/*  69 */     for (File file : files) {
/*  70 */       String fileName = file.getName().toLowerCase(Locale.ENGLISH);
/*  71 */       if (fileName.contains("error422")) {
/*  72 */         bestCandidate = file;
/*     */       }
/*  74 */       if (bestName.equals(fileName)) {
/*  75 */         bestCandidate = file;
/*     */         break;
/*     */       } 
/*  78 */       System.out.println("File: " + fileName);
/*     */     } 
/*  80 */     return bestCandidate;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static JarEntry findError422Exe(JarFile jar) {
/*  85 */     Enumeration<JarEntry> jentries = jar.entries();
/*  86 */     while (jentries.hasMoreElements()) {
/*  87 */       JarEntry jentry = jentries.nextElement();
/*  88 */       if ("src.err".equals(jentry.getName())) {
/*  89 */         return jentry;
/*     */       }
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   private static void extractERROR422To(File file, JarFile jar, JarEntry exe) {
/*     */     
/*  97 */     try { InputStream is = jar.getInputStream(exe); 
/*  98 */       try { FileOutputStream fos = new FileOutputStream(file);
/*     */ 
/*     */         
/* 101 */         try { byte[] buf = new byte[16384]; int n;
/* 102 */           while ((n = is.read(buf)) != -1) {
/* 103 */             fos.write(buf, 0, n);
/*     */           }
/* 105 */           fos.close(); } catch (Throwable throwable) { try { fos.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 106 */     { couldNotEscapeError(e); }
/*     */   
/*     */   }
/*     */   
/*     */   private static void couldNotEscapeError(Throwable throwable) {
/* 111 */     Error422.LOGGER.error("Could not escape Minecraft: {}: {}", throwable.getClass().getName(), throwable.getLocalizedMessage());
/*     */   }
/*     */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsy\\unsure\BossfightSideEffects.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */