package Absorber.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class SyringeDrainCard extends DrainCard {
    public SyringeDrainCard(final String id,
                final String img,
                final int cost,
                final CardType type,
                final CardColor color,
                final CardRarity rarity,
                final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }


}
