package Absorber.relics;

import Absorber.actions.DrainAllAction;
import Absorber.powers.DrainLashPower;
import Absorber.powers.ProtectedPower;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Absorber.DefaultMod.makeRelicOutlinePath;
import static Absorber.DefaultMod.makeRelicPath;

public class DrainAllRelic extends CustomRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    private static final Logger logger = LogManager.getLogger(SurgicalGlovesRelic .class.getName());
    public static final String ID = DefaultMod.makeID("DrainAllRelic");

    private static final Texture FRESH = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));

    public DrainAllRelic() {
        super(ID, FRESH, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
        tips.clear();
        tips.add(new PowerTip(name, description));
    }
    @Override
    public void atBattleStart() {
        AbstractDungeon.actionManager.addToBottom(new DrainAllAction(1));
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}