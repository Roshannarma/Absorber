package Absorber.relics;

import Absorber.cards.SyringeCard;
import Absorber.cards.SyringeDrainCard;
import Absorber.powers.DrainLashPower;
import Absorber.powers.ProtectedPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class DoubleDoseRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
//    private static final Logger logger = LogManager.getLogger(SurgicalGlovesRelic .class.getName());
    public static final String ID = DefaultMod.makeID("DoubleDoseRelic");

    private static final Texture FRESH = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));

    private static boolean first = true;

    public DoubleDoseRelic() {
        super(ID, FRESH, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if((targetCard instanceof SyringeCard || targetCard instanceof SyringeDrainCard)&& first){
            flash();
            AbstractMonster m = null;
            if (useCardAction.target != null) {
                m = (AbstractMonster)useCardAction.target;
            }

            AbstractCard tmp = targetCard.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = targetCard.current_x;
            tmp.current_y = targetCard.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, targetCard.energyOnUse, true, true), true);
//            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player,AbstractDungeon.player,3));
            first = false;
        }
    }
    @Override
    public void atBattleStart() {
        first = true;
    }
    @Override
    public void atTurnStart(){
        first = true;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
