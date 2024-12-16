package net.pinto.mythandmetal.item.customfun;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

 public class RenderTypeMixin {
     private static final ResourceLocation UNCENCHANTED_GLINT_ENTITYTEXT = new ResourceLocation("textures/misc/uncenchanted_glint_entity.png");
     private static final ResourceLocation UNCENCHANTED_GLINT_ITEMTEXT = new ResourceLocation("textures/misc/uncenchanted_glint_item.png");

     public static  RenderType UNCGLINT_DIRECT ;
     public static  RenderType UNCENTITY_GLINT_DIRECT ;
     private static final RenderStateShard.CullStateShard NO_CULL = new RenderStateShard.CullStateShard(false);
     private static final RenderStateShard.WriteMaskStateShard COLOR_WRITE = new RenderStateShard.WriteMaskStateShard(true, false);
     private static final RenderStateShard.ShaderStateShard RENDERTYPE_ENTITY_GLINT_DIRECT_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEntityGlintDirectShader);
     private static final RenderStateShard.DepthTestStateShard EQUAL_DEPTH_TEST = new RenderStateShard.DepthTestStateShard("==", 514);
     private static final RenderStateShard.TexturingStateShard ENTITY_GLINT_TEXTURING = new RenderStateShard.TexturingStateShard("entity_glint_texturing", () -> {
         setupGlintTexturing(0.16F);
     }, () -> {
         RenderSystem.resetTextureMatrix();
     });
     private static final RenderStateShard.TexturingStateShard GLINT_TEXTURING = new RenderStateShard.TexturingStateShard("glint_texturing", () -> {
         setupGlintTexturing(8.0F);
     }, () -> {
         RenderSystem.resetTextureMatrix();
     });
     private static void setupGlintTexturing(float pScale) {
         long i = (long)((double) Util.getMillis() * Minecraft.getInstance().options.glintSpeed().get() * 8.0D);
         float f = (float)(i % 110000L) / 110000.0F;
         float f1 = (float)(i % 30000L) / 30000.0F;
         Matrix4f matrix4f = (new Matrix4f()).translation(-f, f1, 0.0F);
         matrix4f.rotateZ(0.17453292F).scale(pScale);
         RenderSystem.setTextureMatrix(matrix4f);
     }
     private static final RenderStateShard.TransparencyStateShard GLINT_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("glint_transparency", () -> {
         RenderSystem.enableBlend();
         RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
     }, () -> {
         RenderSystem.disableBlend();
         RenderSystem.defaultBlendFunc();
     });
     private static final RenderStateShard.ShaderStateShard RENDERTYPE_GLINT_DIRECT_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeGlintDirectShader);

     public static RenderType UncglintDirect() {

         System.out.println("UNCGLINT_DIRECT: " + RenderTypeMixin.UNCGLINT_DIRECT);
         System.out.println("UNCGLINT_DIRECT: " + RenderType.glintDirect());
         return RenderTypeMixin.UNCGLINT_DIRECT;

     }
     public static RenderType UncentityGlintDirect() {

         System.out.println("UNCGLINT_DIRECT: " + RenderTypeMixin.UNCENTITY_GLINT_DIRECT);
         System.out.println("UNCGLINT_DIRECT: " + RenderType.glintDirect());
         return RenderTypeMixin.UNCENTITY_GLINT_DIRECT;
     }


     public static void createstuff(CallbackInfo info) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {



            Class<?> renderTypeClass = RenderType.class;

            // Get the private 'create' method with the correct signature
            Method createMethod = renderTypeClass.getDeclaredMethod(
                    "create",
                    String.class,
                    VertexFormat.class,
                    VertexFormat.Mode.class,
                    int.class,
                    RenderType.CompositeState.class
            );

            // Make the method accessible
            createMethod.setAccessible(true);


            System.out.println("creating variablesGlint");
           UNCGLINT_DIRECT = (RenderType) createMethod.invoke(null,"glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(UNCENCHANTED_GLINT_ITEMTEXT, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
           UNCENTITY_GLINT_DIRECT = (RenderType) createMethod.invoke(null,"entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(UNCENCHANTED_GLINT_ENTITYTEXT, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));

         System.out.println(UNCGLINT_DIRECT);

    }}
