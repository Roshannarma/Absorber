package Absorber.cards.ConsumeCards;

import Absorber.actions.SpecialDiscoveryAction;
import Absorber.actions.Stimulated;
import Absorber.cards.ConsumeCards.Daggers.*;
import Absorber.cards.StimulatedCards;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Absorber.DefaultMod;
import Absorber.characters.TheDefault;

import java.util.ArrayList;

import static Absorber.DefaultMod.makeCardPath;

//@AutoAdd.Ignore
public class ReptoDag extends StimulatedCards {


    public static final String ID = DefaultMod.makeID("ReptoDag");
    public static final String IMG = makeCardPath("Attack.png"); // ConsumeDagger.png


    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;
    //    private static final int UPGRADE_PLUS_COST = 0;

//    private static final int DAMAGE = 8;    // DAMAGE = ${DAMAGE}

//    private static final int STIM_REFRESH = 1;

    public ReptoDag() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!Stimulated.update()){
            ArrayList<AbstractCard> cards = new ArrayList<>();
            cards.add(new ReptoDaggerR());
            cards.add(new ReptoDaggerG());
            cards.add(new ReptoDaggerB());
            cards.add(new ReptoDaggerP());
            AbstractDungeon.actionManager.addToBottom(new SpecialDiscoveryAction(cards));
        }
        else{
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new ReptoDaggerM(),1));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
