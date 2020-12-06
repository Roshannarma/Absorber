package Absorber.relics;

import Absorber.powers.ProtectedPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class DoctorScrubsRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("DoctorScrubsRelic");

    private static final Texture FRESH = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));
    private boolean gainProtectedNext = false;
    private boolean firstTurn = false;

    public DoctorScrubsRelic() {
        super(ID, FRESH, OUTLINE, RelicTier.RARE, LandingSound.CLINK);

        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    public void atPreBattle() {
             flash();
             this.firstTurn = true;
             this.gainProtectedNext = true;
             if (!this.pulse) {
                  beginPulse();
                   this.pulse = true;
                }
           }
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        beginPulse();
         this.pulse = true;
         if (this.gainProtectedNext && !this.firstTurn) {
              flash();
              AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new ProtectedPower(p,1,false)));
//               addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
//               addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new ProtectedPower(p,1)));
             }
         this.firstTurn = false;
         this.gainProtectedNext = true;
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
             if (card.type == AbstractCard.CardType.ATTACK) {
                  this.gainProtectedNext = false;
                  this.pulse = false;
             }
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
