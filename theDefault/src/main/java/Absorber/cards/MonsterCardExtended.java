package Absorber.cards;

import Absorber.patches.MonsterRarityEnum;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public abstract class MonsterCardExtended extends AbstractDynamicCard {
    private static final Texture MONSTER_BANNER = new Texture("AbsorberResources/images/Rarity/Banner.png");
    private static final TextureAtlas.AtlasRegion MONSTER_BANNER_ATLAS = new TextureAtlas.AtlasRegion(MONSTER_BANNER, 0, 0, MONSTER_BANNER.getWidth(), MONSTER_BANNER.getHeight());
    private static final Texture MONSTER_BANNER_L = new Texture("AbsorberResources/images/Rarity/Banner_p.png");
    private static final TextureAtlas.AtlasRegion MONSTER_BANNER_ATLAS_L = new TextureAtlas.AtlasRegion(MONSTER_BANNER_L, 0, 0, MONSTER_BANNER_L.getWidth(), MONSTER_BANNER_L.getHeight());;
    private static final Texture MONSTER_FRAME_ATTACK = new Texture("AbsorberResources/images/Rarity/Attack.png");
    private static final TextureAtlas.AtlasRegion MONSTER_FRAME_ATTACK_ATLAS = new TextureAtlas.AtlasRegion(MONSTER_FRAME_ATTACK, 0, 0, MONSTER_FRAME_ATTACK.getWidth(), MONSTER_FRAME_ATTACK.getHeight());
    private static final Texture MONSTER_FRAME_ATTACK_L = new Texture("AbsorberResources/images/Rarity/Attack_p.png");
    private static final TextureAtlas.AtlasRegion MONSTER_FRAME_ATTACK_L_ATLAS = new TextureAtlas.AtlasRegion(MONSTER_FRAME_ATTACK_L, 0, 0, MONSTER_FRAME_ATTACK_L.getWidth(), MONSTER_FRAME_ATTACK_L.getHeight());


    public MonsterCardExtended(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }
//    public TextureAtlas.AtlasRegion getBannerSmallRegion() {
//        if (this.bannerSmallRegion == null && this.textureBannerSmallImg != null) {
//            Texture t = this.getBannerSmallTexture();
//            this.bannerSmallRegion = new TextureAtlas.AtlasRegion(t, 0, 0, t.getWidth(), t.getHeight());
//        }
//
//        return this.bannerSmallRegion;
//    }
//
//    public TextureAtlas.AtlasRegion getBannerLargeRegion() {
//        if (this.bannerLargeRegion == null && this.textureBannerLargeImg != null) {
//            Texture t = this.getBannerLargeTexture();
//            this.bannerLargeRegion = new TextureAtlas.AtlasRegion(t, 0, 0, t.getWidth(), t.getHeight());
//        }
//
//        return this.bannerLargeRegion;
//    }
//
//    public void setBannerTexture(String bannerSmallImg, String bannerLargeImg) {
////        this.textureBannerSmallImg = bannerSmallImg;
////        this.textureBannerLargeImg = bannerLargeImg;
////        loadTextureFromString(bannerSmallImg);
////        loadTextureFromString(bannerLargeImg);
////        Texture t = this.getBannerSmallTexture();
////        this.bannerSmallRegion = new TextureAtlas.AtlasRegion(t, 0, 0, t.getWidth(), t.getHeight());
////        t = this.getBannerLargeTexture();
////        this.bannerLargeRegion = new TextureAtlas.AtlasRegion(t, 0, 0, t.getWidth(), t.getHeight());
//        this.bannerSmallRegion = MONSTER_BANNER_ATLAS;
//        this.bannerLargeRegion = MONSTER_BANNER_ATLAS_L;
//    }

    @Override
    public void setDisplayRarity(CardRarity rarity) {
        if (rarity == MonsterRarityEnum.MONSTER) {
            this.bannerSmallRegion = ImageMaster.CARD_BANNER_COMMON;
            this.bannerLargeRegion = ImageMaster.CARD_BANNER_COMMON_L;
            switch (this.type) {
                case ATTACK:
                    this.frameSmallRegion = ImageMaster.CARD_FRAME_ATTACK_COMMON;
                    this.frameLargeRegion = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
//            this.bannerSmallRegion = MONSTER_BANNER_ATLAS;
//            this.bannerLargeRegion = MONSTER_BANNER_ATLAS_L;
//            switch (this.type) {
//                case ATTACK:
////                    this.frameSmallRegion = ImageMaster.CARD_FRAME_ATTACK_COMMON;
//                    this.frameSmallRegion = MONSTER_FRAME_ATTACK_ATLAS;
//                    this.frameLargeRegion = MONSTER_FRAME_ATTACK_L_ATLAS;
                    break;
                case POWER:
                    this.frameSmallRegion = ImageMaster.CARD_FRAME_POWER_COMMON;
                    this.frameLargeRegion = ImageMaster.CARD_FRAME_POWER_COMMON_L;
                    break;
                default:
                    this.frameSmallRegion = ImageMaster.CARD_FRAME_SKILL_COMMON;
                    this.frameLargeRegion = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
            }

            this.frameMiddleRegion = ImageMaster.CARD_COMMON_FRAME_MID;
            this.frameLeftRegion = ImageMaster.CARD_COMMON_FRAME_LEFT;
            this.frameRightRegion = ImageMaster.CARD_COMMON_FRAME_RIGHT;
            this.frameMiddleLargeRegion = ImageMaster.CARD_COMMON_FRAME_MID_L;
            this.frameLeftLargeRegion = ImageMaster.CARD_COMMON_FRAME_LEFT_L;
            this.frameRightLargeRegion = ImageMaster.CARD_COMMON_FRAME_RIGHT_L;

        } else {
            switch (rarity) {
                case BASIC:
                case CURSE:
                case SPECIAL:
                case COMMON:
                    this.bannerSmallRegion = ImageMaster.CARD_BANNER_COMMON;
                    this.bannerLargeRegion = ImageMaster.CARD_BANNER_COMMON_L;
                    switch (this.type) {
                        case ATTACK:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_ATTACK_COMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_ATTACK_COMMON_L;
                            break;
                        case POWER:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_POWER_COMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_POWER_COMMON_L;
                            break;
                        default:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_SKILL_COMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_SKILL_COMMON_L;
                    }

                    this.frameMiddleRegion = ImageMaster.CARD_COMMON_FRAME_MID;
                    this.frameLeftRegion = ImageMaster.CARD_COMMON_FRAME_LEFT;
                    this.frameRightRegion = ImageMaster.CARD_COMMON_FRAME_RIGHT;
                    this.frameMiddleLargeRegion = ImageMaster.CARD_COMMON_FRAME_MID_L;
                    this.frameLeftLargeRegion = ImageMaster.CARD_COMMON_FRAME_LEFT_L;
                    this.frameRightLargeRegion = ImageMaster.CARD_COMMON_FRAME_RIGHT_L;
                    break;
                case UNCOMMON:
                    this.bannerSmallRegion = ImageMaster.CARD_BANNER_UNCOMMON;
                    this.bannerLargeRegion = ImageMaster.CARD_BANNER_UNCOMMON_L;
                    switch (this.type) {
                        case ATTACK:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_ATTACK_UNCOMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_ATTACK_UNCOMMON_L;
                            break;
                        case POWER:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_POWER_UNCOMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_POWER_UNCOMMON_L;
                            break;
                        default:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_SKILL_UNCOMMON;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_SKILL_UNCOMMON_L;
                    }

                    this.frameMiddleRegion = ImageMaster.CARD_UNCOMMON_FRAME_MID;
                    this.frameLeftRegion = ImageMaster.CARD_UNCOMMON_FRAME_LEFT;
                    this.frameRightRegion = ImageMaster.CARD_UNCOMMON_FRAME_RIGHT;
                    this.frameMiddleLargeRegion = ImageMaster.CARD_UNCOMMON_FRAME_MID_L;
                    this.frameLeftLargeRegion = ImageMaster.CARD_UNCOMMON_FRAME_LEFT_L;
                    this.frameRightLargeRegion = ImageMaster.CARD_UNCOMMON_FRAME_RIGHT_L;
                    break;
                case RARE:
                    this.bannerSmallRegion = ImageMaster.CARD_BANNER_RARE;
                    this.bannerLargeRegion = ImageMaster.CARD_BANNER_RARE_L;
                    switch (this.type) {
                        case ATTACK:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_ATTACK_RARE;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_ATTACK_RARE_L;
                            break;
                        case POWER:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_POWER_RARE;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_POWER_RARE_L;
                            break;
                        default:
                            this.frameSmallRegion = ImageMaster.CARD_FRAME_SKILL_RARE;
                            this.frameLargeRegion = ImageMaster.CARD_FRAME_SKILL_RARE_L;
                    }

                    this.frameMiddleRegion = ImageMaster.CARD_RARE_FRAME_MID;
                    this.frameLeftRegion = ImageMaster.CARD_RARE_FRAME_LEFT;
                    this.frameRightRegion = ImageMaster.CARD_RARE_FRAME_RIGHT;
                    this.frameMiddleLargeRegion = ImageMaster.CARD_RARE_FRAME_MID_L;
                    this.frameLeftLargeRegion = ImageMaster.CARD_RARE_FRAME_LEFT_L;
                    this.frameRightLargeRegion = ImageMaster.CARD_RARE_FRAME_RIGHT_L;
                    break;
                default:
                    System.out.println("Attempted to set display rarity to an unknown rarity: " + rarity.name());
            }
        }
    }
}
