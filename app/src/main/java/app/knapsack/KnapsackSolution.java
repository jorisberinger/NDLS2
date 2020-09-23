package app.knapsack;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.CountableValueRange;
import org.optaplanner.core.api.domain.valuerange.ValueRangeFactory;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class KnapsackSolution {
    @PlanningEntityCollectionProperty
    private List<Ingot> ingots;

    @ValueRangeProvider(id = "selected")
    public CountableValueRange getSelected() {
        return ValueRangeFactory.createBooleanValueRange();
    }

    @ProblemFactProperty
    private Knapsack knapsack;

    @PlanningScore
    private HardSoftScore score;

    public List<Ingot> getIngots() {
        return ingots;
    }

    public void setIngots(List<Ingot> ingots) {
        this.ingots = ingots;
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

}