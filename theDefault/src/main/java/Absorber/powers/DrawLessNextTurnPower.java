package Absorber.powers;
import Absorber.DefaultMod;
import Absorber.cards.ConsumeCards.GremlinStab;
import Absorber.powers.CommonPower;
import Absorber.util.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import static Absorber.DefaultMod.makePowerPath;

public class DrawLessNextTurnPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID("DrawLessNextTurnPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public DrawLessNextTurnPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;
        this.amount = amount;

        type = PowerType.BUFF;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }
    public void onInitialApplication() {
        int handsize = AbstractDungeon.player.gameHandSize;
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof DrawCardNextTurnPower){
                if(p.amount > this.amount){
                    p.amount -= this.amount;
                    addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Absorber:DrawLessNextTurnPower"));
                }
                else if(p.amount < this.amount){
                    this.amount -= p.amount;
                    addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Draw Card"));
                }
                else{
                    addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Draw Card"));
                    addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Absorber:DrawLessNextTurnPower"));
                }
            }

        }
        if(this.amount>handsize){
            this.amount = handsize;
        }
        AbstractDungeon.player.gameHandSize -= this.amount;
    }
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "Absorber:DrawLessNextTurnPower"));
    }
    public void onRemove() {
        AbstractDungeon.player.gameHandSize += this.amount;
    }
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }
    @Override
    public AbstractPower makeCopy() {
        return new DrawLessNextTurnPower(owner,source,amount);
    }
}

