package Absorber.cards;

public abstract class SpecialScalingCards extends AbstractDynamicCard{
    public int extra_damage;
    public SpecialScalingCards(String id, String img, int cost, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, img, cost, type, color, rarity, target);
    }
}
