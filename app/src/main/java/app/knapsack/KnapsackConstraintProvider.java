package app.knapsack;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class KnapsackConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
            maxWeight(constraintFactory), maxValue(constraintFactory)
        }
    }

    private Constraint maxValue(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Ingot.class).filter(i -> i.getSelected())
        .groupBy(ConstraintCollectors.sum(i -> i.getWeight())).join(Knapsack.class)
        .filter((ws, k) -> ws > k.getMaxWeight())
        .penalize("Max Weight", HardSoftScore.ONE_HARD, (ws,k) -> ws -k.getMaxWeight());
    }

    private Constraint maxWeight(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Ingot.class)
        .filter(Ingot::getSelected)
        .reward("Max Value", HardSoftScore.ONE_SOFT, Ingot::getValue);
    }
    
}