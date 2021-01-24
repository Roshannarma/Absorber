package Absorber.powers;

import Absorber.DefaultMod;
import Absorber.actions.DrainAction;
import Absorber.actions.DrainAllAction;
import Absorber.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static Absorber.DefaultMod.makePowerPath;

public class LifeLeechPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = DefaultMod.makeID("LifeLeechPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public LifeLeechPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;
        type = PowerType.DEBUFF;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        this.updateDescription();
    }


    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0] + ".";
        }
        else{
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new DrainAction(this.owner,2));
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Absorber:LifeLeechPower"));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, "Absorber:LifeLeechPower", 1));
        }
    }
//    public void atEndOfTurn(boolean isPlayer) {
//        AbstractDungeon.actionManager.addToBottom(new DrainAction(this.owner,2));
//        if (this.amount == 0) {
//            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Absorber:LifeLeechPower"));
//        } else {
//            addToBot(new ReducePowerAction(this.owner, this.owner, "Absorber:LifeLeechPower", 1));
//        }
//    }

    @Override
    public AbstractPower makeCopy() {
        return new LifeLeechPower(owner,source,amount);
    }
}
