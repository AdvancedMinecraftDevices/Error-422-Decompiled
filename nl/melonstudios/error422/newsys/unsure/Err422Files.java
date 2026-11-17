/*    */ package nl.melonstudios.error422.newsys.unsure;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ public class Err422Files {
/*  6 */   private static final String USER_HOME = System.getProperty("user.home");
/*  7 */   public static final File DESKTOP = new File(USER_HOME + "/Desktop");
/*  8 */   public static final File DOCUMENTS = new File(USER_HOME + "/Documents");
/*  9 */   public static final File DOWNLOADS = new File(USER_HOME + "/Downloads");
/*    */   
/*    */   public static File desktop(String path) {
/* 12 */     return new File(DESKTOP, path);
/*    */   }
/*    */   public static File documents(String path) {
/* 15 */     return new File(DOCUMENTS, path);
/*    */   }
/*    */   public static File downloads(String path) {
/* 18 */     return new File(DOWNLOADS, path);
/*    */   }
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\newsy\\unsure\Err422Files.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */