package Absorber.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import Absorber.DefaultMod;
import Absorber.util.TextureLoader;
import Absorber.actions.AddCardFromConsume;

import static Absorber.DefaultMod.makePowerPath;

public class ConsumeCardGainPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = "ConsumeCardGain";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ConsumeCardGainPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("ConsumeCardGainPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("ConsumeCardGainPower32.png"));
    public AbstractCreature target;
    ConsumeCardGainPower(final AbstractCreature owner, AbstractCreature target){
        this.name = NAME;
        this.ID = "ConsumeCardGain";
        this.owner = owner;
        this.target = target;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] ;
    }

    public void onVictory(AbstractCreature target) {
        AddCardFromConsume temp = new AddCardFromConsume(target);


    }
    @Override
    public AbstractPower makeCopy() {
        return new ConsumeCardGainPower(owner, target);
    }
}

