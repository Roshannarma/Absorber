package Absorber.relics;

import Absorber.cards.starter.LivingDagger;
import Absorber.patches.MonsterRarityEnum;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class FreshSamplesRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("FreshSamplesRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RelicBig.png"));
    private static final int ENERGY_DRAW =1;

    public FreshSamplesRelic() {
        super(ID, IMG, RelicTier.COMMON, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    @Override
    public void atBattleStart() {
        this.counter = 0;
    }
    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if(targetCard.rarity == MonsterRarityEnum.MONSTER){
            this.counter++;

            if(this.counter % 3 == 0){
                flash();
                this.counter = 0;
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player,this));
                AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(ENERGY_DRAW));
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(ENERGY_DRAW));
            }

        }

    }




    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}





