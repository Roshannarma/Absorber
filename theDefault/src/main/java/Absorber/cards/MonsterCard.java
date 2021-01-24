package Absorber.cards;

import Absorber.patches.DefaultInsertPatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class MonsterCard extends AbstractDynamicCard {
    private static final float SHADOW_OFFSET_X;
    private static final Color HOVER_IMG_COLOR;
    private static final Color SELECTED_CARD_COLOR;
    private static final float SHADOW_OFFSET_Y;
    private final Color BANNER_COLOR_MONSTER = new Color(1.0F, 0.796F, 0.251F, 1.0F);
    private final Texture MONSTER_BANNER = new Texture("AbsorberResources/images/Rarity/Banner.png");
    private static final Logger logger = LogManager.getLogger(MonsterCard.class.getName());
    private Color renderColor;
    private Color frameShadowColor;


    public MonsterCard(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
        logger.info(id);
    }
    static{
        SHADOW_OFFSET_X = 18.0F * Settings.scale;
        SHADOW_OFFSET_Y = 14.0F * Settings.scale;
        HOVER_IMG_COLOR = new Color(1.0F, 0.815F, 0.314F, 0.8F);
        SELECTED_CARD_COLOR = new Color(0.5F, 0.9F, 0.9F, 1.0F);
    }


    @SpireOverride
    private void renderBannerImage(SpriteBatch sb, float drawX, float drawY) {
        logger.info("Monster Banner");
//        SpriteBatch sb, Color color, Texture img, float drawX, float drawY
        sb.setColor(BANNER_COLOR_MONSTER);
        sb.draw(MONSTER_BANNER, drawX + 256.0F, drawY + 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);

//        this.renderHelper(sb, this.renderColor, ImageMaster.CARD_BANNER_RARE, drawX, drawY);
    }

    @SpireOverride
    private void renderImage(SpriteBatch sb, boolean hovered, boolean selected) {
        if (AbstractDungeon.player != null) {
            if (selected) {
                this.renderHelper(sb, Color.SKY, this.getCardBgAtlas(), this.current_x, this.current_y, 1.03F);
            }

            this.renderHelper(sb, this.frameShadowColor, this.getCardBgAtlas(), this.current_x + SHADOW_OFFSET_X * this.drawScale, this.current_y - SHADOW_OFFSET_Y * this.drawScale);
            if (AbstractDungeon.player.hoveredCard == this && (AbstractDungeon.player.isDraggingCard && AbstractDungeon.player.isHoveringDropZone || AbstractDungeon.player.inSingleTargetMode)) {
                this.renderHelper(sb, HOVER_IMG_COLOR, this.getCardBgAtlas(), this.current_x, this.current_y);
            } else if (selected) {
                this.renderHelper(sb, SELECTED_CARD_COLOR, this.getCardBgAtlas(), this.current_x, this.current_y);
            }
        }

        this.renderCardBg(sb, this.current_x, this.current_y);
        if (!UnlockTracker.betaCardPref.getBoolean(this.cardID, false) && !Settings.PLAYTESTER_ART_MODE) {
            this.renderPortrait(sb);
        } else {
            this.renderJokePortrait(sb);
        }

        this.renderPortraitFrame(sb, this.current_x, this.current_y);
        this.renderBannerImage(sb, this.current_x, this.current_y);
    }
    @SpireOverride
    private void renderPortraitFrame(SpriteBatch sb, float x, float y) {
        float tWidth = 0.0F;
        float tOffset = 0.0F;
        switch(this.type) {
            case ATTACK:
                this.renderAttackPortrait(sb, x, y);
                tWidth = typeWidthAttack;
                tOffset = typeOffsetAttack;
                break;
            case CURSE:
                this.renderSkillPortrait(sb, x, y);
                tWidth = typeWidthCurse;
                tOffset = typeOffsetCurse;
                break;
            case STATUS:
                this.renderSkillPortrait(sb, x, y);
                tWidth = typeWidthStatus;
                tOffset = typeOffsetStatus;
                break;
            case SKILL:
                this.renderSkillPortrait(sb, x, y);
                tWidth = typeWidthSkill;
                tOffset = typeOffsetSkill;
                break;
            case POWER:
                this.renderPowerPortrait(sb, x, y);
                tWidth = typeWidthPower;
                tOffset = typeOffsetPower;
        }

        this.renderDynamicFrame(sb, x, y, tOffset, tWidth);
    }
    @SpireOverride
    private void renderAttackPortrait(SpriteBatch sb, float x, float y) {
        switch(this.rarity) {
            case BASIC:
            case CURSE:
            case SPECIAL:
            case COMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_COMMON, x, y);
                return;
            case UNCOMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_UNCOMMON, x, y);
                return;
            case RARE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_ATTACK_RARE, x, y);
                return;
            default:
        }
    }
    @SpireOverride
    private void renderDynamicFrame(SpriteBatch sb, float x, float y, float typeOffset, float typeWidth) {
        if (typeWidth > 1.1F) {
            switch(this.rarity) {
                case BASIC:
                case CURSE:
                case SPECIAL:
                case COMMON:
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_MID, x, y, 0.0F, typeWidth);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_LEFT, x, y, -typeOffset, 1.0F);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_COMMON_FRAME_RIGHT, x, y, typeOffset, 1.0F);
                    break;
                case UNCOMMON:
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_MID, x, y, 0.0F, typeWidth);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_LEFT, x, y, -typeOffset, 1.0F);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_UNCOMMON_FRAME_RIGHT, x, y, typeOffset, 1.0F);
                    break;
                case RARE:
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_MID, x, y, 0.0F, typeWidth);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_LEFT, x, y, -typeOffset, 1.0F);
                    this.dynamicFrameRenderHelper(sb, ImageMaster.CARD_RARE_FRAME_RIGHT, x, y, typeOffset, 1.0F);
            }

        }
    }
    @SpireOverride
    private void dynamicFrameRenderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float x, float y, float xOffset, float xScale) {
        sb.draw(img, x + img.offsetX - (float)img.originalWidth / 2.0F + xOffset * this.drawScale, y + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, this.drawScale * Settings.scale * xScale, this.drawScale * Settings.scale, this.angle);
    }
    @SpireOverride
    private void dynamicFrameRenderHelper(SpriteBatch sb, Texture img, float x, float y, float xOffset, float xScale) {
        sb.draw(img, x + xOffset * this.drawScale, y, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale * xScale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }
    @SpireOverride
    private void renderSkillPortrait(SpriteBatch sb, float x, float y) {
        switch(this.rarity) {
            case BASIC:
            case CURSE:
            case SPECIAL:
            case COMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_COMMON, x, y);
                break;
            case UNCOMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_UNCOMMON, x, y);
                break;
            case RARE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_SKILL_RARE, x, y);
        }
    }
    @SpireOverride
    private void renderPowerPortrait(SpriteBatch sb, float x, float y) {
        switch(this.rarity) {
            case BASIC:
            case CURSE:
            case SPECIAL:
            case COMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_COMMON, x, y);
                break;
            case UNCOMMON:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_UNCOMMON, x, y);
                break;
            case RARE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_FRAME_POWER_RARE, x, y);
        }

    }
    @SpireOverride
    private void renderPortrait(SpriteBatch sb) {
        float drawX = this.current_x - 125.0F;
        float drawY = this.current_y - 95.0F;
        Texture img = null;
        if (this.portraitImg != null) {
            img = this.portraitImg;
        }

        if (!this.isLocked) {
            if (this.portrait != null) {
                drawX = this.current_x - (float)this.portrait.packedWidth / 2.0F;
                drawY = this.current_y - (float)this.portrait.packedHeight / 2.0F;
                sb.setColor(this.renderColor);
                sb.draw(this.portrait, drawX, drawY + 72.0F, (float)this.portrait.packedWidth / 2.0F, (float)this.portrait.packedHeight / 2.0F - 72.0F, (float)this.portrait.packedWidth, (float)this.portrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
            } else if (img != null) {
                sb.setColor(this.renderColor);
                sb.draw(img, drawX, drawY + 72.0F, 125.0F, 23.0F, 250.0F, 190.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
            }
        } else {
            sb.draw(this.portraitImg, drawX, drawY + 72.0F, 125.0F, 23.0F, 250.0F, 190.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        }

    }
    @SpireOverride
    private void renderJokePortrait(SpriteBatch sb) {
        float drawX = this.current_x - 125.0F;
        float drawY = this.current_y - 95.0F;
        Texture img = null;
        if (this.portraitImg != null) {
            img = this.portraitImg;
        }

        if (!this.isLocked) {
            if (this.jokePortrait != null) {
                drawX = this.current_x - (float)this.portrait.packedWidth / 2.0F;
                drawY = this.current_y - (float)this.portrait.packedHeight / 2.0F;
                sb.setColor(this.renderColor);
                sb.draw(this.jokePortrait, drawX, drawY + 72.0F, (float)this.jokePortrait.packedWidth / 2.0F, (float)this.jokePortrait.packedHeight / 2.0F - 72.0F, (float)this.jokePortrait.packedWidth, (float)this.jokePortrait.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
            } else if (img != null) {
                sb.setColor(this.renderColor);
                sb.draw(img, drawX, drawY + 72.0F, 125.0F, 23.0F, 250.0F, 190.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
            }
        } else {
            sb.draw(this.portraitImg, drawX, drawY + 72.0F, 125.0F, 23.0F, 250.0F, 190.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 250, 190, false, false);
        }

    }

    @SpireOverride
    private void renderCardBg(SpriteBatch sb, float x, float y) {
        switch (this.type) {
            case ATTACK:
                this.renderAttackBg(sb, x, y);
                break;
            case CURSE:
                this.renderSkillBg(sb, x, y);
                break;
            case STATUS:
                this.renderSkillBg(sb, x, y);
                break;
            case SKILL:
                this.renderSkillBg(sb, x, y);
                break;
            case POWER:
                this.renderPowerBg(sb, x, y);
        }

    }
    @SpireOverride
    private void renderAttackBg(SpriteBatch sb, float x, float y) {
        switch(this.color) {
            case RED:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_RED, x, y);
                break;
            case GREEN:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_GREEN, x, y);
                break;
            case BLUE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_BLUE, x, y);
                break;
            case PURPLE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_PURPLE, x, y);
                break;
            case COLORLESS:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_ATTACK_BG_GRAY, x, y);
                break;
            case CURSE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                break;
            default:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
        }

    }
    @SpireOverride
    private void renderSkillBg(SpriteBatch sb, float x, float y) {
        switch(this.color) {
            case RED:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_RED, x, y);
                break;
            case GREEN:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_GREEN, x, y);
                break;
            case BLUE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLUE, x, y);
                break;
            case PURPLE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_PURPLE, x, y);
                break;
            case COLORLESS:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_GRAY, x, y);
                break;
            case CURSE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                break;
            default:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
        }

    }
    @SpireOverride
    private void renderPowerBg(SpriteBatch sb, float x, float y) {
        switch(this.color) {
            case RED:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_RED, x, y);
                break;
            case GREEN:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_GREEN, x, y);
                break;
            case BLUE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_BLUE, x, y);
                break;
            case PURPLE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_PURPLE, x, y);
                break;
            case COLORLESS:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_POWER_BG_GRAY, x, y);
                break;
            case CURSE:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
                break;
            default:
                this.renderHelper(sb, this.renderColor, ImageMaster.CARD_SKILL_BG_BLACK, x, y);
        }
    }
    @SpireOverride
    private void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle);
    }
    @SpireOverride
    private void renderHelper(SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY, float scale) {
        sb.setColor(color);
        sb.draw(img, drawX + img.offsetX - (float)img.originalWidth / 2.0F, drawY + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, this.drawScale * Settings.scale * scale, this.drawScale * Settings.scale * scale, this.angle);
    }
    @SpireOverride
    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX + 256.0F, drawY + 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }
    @SpireOverride
    private void renderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY, float scale) {
        sb.setColor(color);
        sb.draw(img, drawX, drawY, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale * scale, this.drawScale * Settings.scale * scale, this.angle, 0, 0, 512, 512, false, false);
    }
}