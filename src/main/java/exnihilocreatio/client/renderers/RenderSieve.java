package exnihilocreatio.client.renderers;

import exnihilocreatio.blocks.BlockSieve;
import exnihilocreatio.tiles.TileSieve;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class RenderSieve extends TileEntitySpecialRenderer<TileSieve> {
    @Override
    public void render(TileSieve te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        Tessellator tes = Tessellator.getInstance();

        BufferBuilder wr = tes.getBuffer();

        float offsetX = 0;
        float offsetY = 0;
        float offsetZ = 0;
        if (te.autoSifter != null && te.autoSifter.toSift != null) {
            offsetX = te.autoSifter.offsetX;
            offsetY = te.autoSifter.offsetY;
            offsetZ = te.autoSifter.offsetZ;
        }

        renderSieve(te, x + offsetX, y + offsetY, z + offsetZ, wr);
        renderBlockInSieve(te, x + offsetX, y + offsetY, z + offsetZ, tes, wr);

    }

    private void renderBlockInSieve(TileSieve te, double x, double y, double z, Tessellator tessellator, BufferBuilder worldRendererBuffer) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        // GlStateManager.translate(0, 1, 0);

        if (te.getTexture() != null && te.getCurrentStack().isValid()) {
            TextureAtlasSprite icon = te.getTexture();
            double minU = (double) icon.getMinU();
            double maxU = (double) icon.getMaxU();
            double minV = (double) icon.getMinV();
            double maxV = (double) icon.getMaxV();

            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

            worldRendererBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
            double height = (100.0 - te.getProgress()) / 100;
            float fillAmount = (float) (0.15625 * height + 0.84375);

            worldRendererBuffer.pos(0.0625f, fillAmount, 0.0625f).tex(minU, minV).normal(0, 1, 0).endVertex();
            worldRendererBuffer.pos(0.0625f, fillAmount, 0.9375f).tex(minU, maxV).normal(0, 1, 0).endVertex();
            worldRendererBuffer.pos(0.9375f, fillAmount, 0.9375f).tex(maxU, maxV).normal(0, 1, 0).endVertex();
            worldRendererBuffer.pos(0.9375f, fillAmount, 0.0625f).tex(maxU, minV).normal(0, 1, 0).endVertex();

            tessellator.draw();
        }

        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

    }

    private void renderSieve(TileSieve tile, double x, double y, double z, BufferBuilder worldRendererBuffer) {
        final BlockRendererDispatcher blockRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher();
        Block blocktype = tile.getBlockType();

        if (!(blocktype instanceof BlockSieve)) return;

        IBlockState state = blocktype.getDefaultState().withProperty(BlockSieve.MESH, tile.getMeshType());

        List<BakedQuad> quadsSieve = blockRenderer.getModelForState(state).getQuads(state, null, 0);

        Tessellator tessellator = Tessellator.getInstance();
        GlStateManager.pushMatrix();

        GlStateManager.translate(x, y, z);

        GlStateManager.blendFunc(770, 771);
        GlStateManager.enableBlend();
        GlStateManager.disableCull();


        RenderHelper.disableStandardItemLighting();

        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        worldRendererBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        // worldRendererBuffer.setTranslation(-.5, -.5, -.5);

        RenderUtils.renderModelTESRFast(quadsSieve, worldRendererBuffer, tile.getWorld(), tile.getPos());

        // worldRendererBuffer.setTranslation(0, 0, 0);
        tessellator.draw();

        GlStateManager.popMatrix();
        RenderHelper.enableStandardItemLighting();

        GlStateManager.disableBlend();
        GlStateManager.enableCull();
    }

}