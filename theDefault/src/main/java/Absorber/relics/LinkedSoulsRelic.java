package Absorber.relics;

import Absorber.cards.starter.LivingDagger;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.unique.IncreaseMaxHpAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class LinkedSoulsRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("LinkedSoulsRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LinkedSoulsBig.png"));

//    private static final Texture FRESH = TextureLoader.getTexture(makeRelicPath("FreshSampleFull.png"));

//    private static final Texture USED = TextureLoader.getTexture(makeRelicPath("FreshSampleEmpty.png"));

//    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.
//    private boolean charged = true;

    public LinkedSoulsRelic() {
        super(ID, IMG, RelicTier.STARTER, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    public void refresh() {
        AbstractDungeon.player.increaseMaxHp(1, true);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

