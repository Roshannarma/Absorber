package Absorber.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

public class AbsorberRenderHelper {
    public static void helper(AbstractCard __instance, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY){
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
    }
    public static void helper(SingleCardViewPopup __instance, SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img){
        if (img != null) {
            sb.draw(img, x + img.offsetX - (float)img.originalWidth / 2.0F, y + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, Settings.scale, Settings.scale, 0.0F);
        }
    }
    public static void renderDynamicFrame(SingleCardViewPopup __instance,AbstractCard card,SpriteBatch sb, float x, float y, float typeOffset, float typeWidth) {
        if (typeWidth > 1.1F) {
            switch(card.rarity) {
                case UNCOMMON:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_MID_L, 0.0F, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_LEFT_L, -typeOffset, 1.0F);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_RIGHT_L, typeOffset, 1.0F);
                    break;
                case RARE:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_MID_L, 0.0F, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_LEFT_L, -typeOffset, 1.0F);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_RIGHT_L, typeOffset, 1.0F);
                    break;
                case COMMON:
                case BASIC:
                case CURSE:
                case SPECIAL:
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_MID_L, 0.0F, typeWidth);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_LEFT_L, -typeOffset, 1.0F);
                    dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_RIGHT_L, typeOffset, 1.0F);
            }

        }
    }

    public static void dynamicFrameRenderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float xOffset, float xScale) {
        sb.draw(img, (float)Settings.WIDTH / 2.0F + img.offsetX - (float)img.originalWidth / 2.0F + xOffset * 2.0F, (float)Settings.HEIGHT / 2.0F + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, Settings.scale * xScale, Settings.scale, 0.0F);
    }
}
