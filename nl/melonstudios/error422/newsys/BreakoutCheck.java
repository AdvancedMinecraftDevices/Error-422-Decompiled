/*    */ package nl.melonstudios.error422.newsys;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.jar.JarEntry;
/*    */ import java.util.jar.JarFile;
/*    */ import nl.melonstudios.error422.newsys.unsure.BossfightSideEffects;
/*    */ 
/*    */ public class BreakoutCheck {
/*    */   private static boolean isExePresent = false;
/*    */   public static final boolean censoredVersion = true;
/*    */   
/*    */   public static void checkForExe() {
/* 13 */     File file = BossfightSideEffects.findMyJar();
/* 14 */     if (file != null)
/* 15 */       try { JarFile jar = new JarFile(file); 
/* 16 */         try { JarEntry jentry = BossfightSideEffects.findError422Exe(jar);
/* 17 */           isExePresent = (jentry != null);
/* 18 */           jar.close(); } catch (Throwable throwable) { try { jar.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (IOException iOException) {} 
/*    */   }
/*    */   
/*    */   public static boolean isExePresent() {
/* 22 */     return isExePresent;
/*    */   }
/*    */   
/*    */   public static boolean displaysMessage() {
/* 26 */     return !isExePresent();
/*    */   }
/*    */   public static String getError() {
/* 29 */     return "[SRC.ERR NOT FOUND]";
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsys\BreakoutCheck.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */