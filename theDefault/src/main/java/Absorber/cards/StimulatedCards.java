package Absorber.cards;

import Absorber.actions.Stimulated;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public abstract class StimulatedCards extends AbstractDynamicCard {
    protected static boolean is_stimulated;
    public StimulatedCards(final String id,
                           final String img,
                           final int cost,
                           final CardType type,
                           final CardColor color,
                           final CardRarity rarity,
                           final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }
    @Override
    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
        if (AbstractDungeon.player.hasRelic("Absorber:Catalyst")){
            is_stimulated = EnergyPanel.totalCount - 1 - cardPlayed.costForTurn > AbstractDungeon.player.energy.energy;
        }
        else{
            is_stimulated = EnergyPanel.totalCount-cardPlayed.costForTurn> AbstractDungeon.player.energy.energy;
        }
        update_glow();
    }
    @Override
    public void triggerWhenDrawn() {
        is_stimulated = Stimulated.update();
        update_glow();
    }


    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        is_stimulated = Stimulated.update();
        update_glow();
    }

    public void update_glow(){
        if(is_stimulated){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else{
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
