/*    */ package nl.melonstudios.error422.idk;
/*    */ 
/*    */ import com.mojang.blaze3d.vertex.DefaultVertexFormat;
/*    */ import com.mojang.blaze3d.vertex.VertexFormat;
/*    */ import net.minecraft.client.renderer.RenderBuffers;
/*    */ import net.minecraft.client.renderer.RenderStateShard;
/*    */ import net.minecraft.client.renderer.RenderType;
/*    */ import net.minecraft.client.renderer.texture.TextureAtlas;
/*    */ import net.neoforged.api.distmarker.Dist;
/*    */ import net.neoforged.api.distmarker.OnlyIn;
/*    */ 
/*    */ @OnlyIn(Dist.CLIENT)
/*    */ public class ErrRenderTypes {
/* 14 */   public static RenderBuffers RENDER_BUFFERS = null;
/* 15 */   public static final RenderStateShard.TextureStateShard REPLACEMENT_SHEET_MIPPED = new RenderStateShard.TextureStateShard(TextureAtlas.LOCATION_BLOCKS, true, true);
/*    */ 
/*    */   
/* 18 */   public static final RenderType REPLACEMENT = (RenderType)RenderType.create("replacement", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 4194304, true, false, 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 25 */       RenderType.CompositeState.builder()
/* 26 */       .setLightmapState(RenderStateShard.LIGHTMAP)
/* 27 */       .setShaderState(RenderStateShard.RENDERTYPE_SOLID_SHADER)
/* 28 */       .setTextureState((RenderStateShard.EmptyTextureStateShard)REPLACEMENT_SHEET_MIPPED)
/* 29 */       .createCompositeState(true));
/*    */ }


/* Location:              C:\Users\jacob.HomeSchool\Downloads\error422-2.2.0.jar!\nl\melonstudios\error422\idk\ErrRenderTypes.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */