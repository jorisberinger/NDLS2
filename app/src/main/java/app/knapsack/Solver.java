package app.knapsack;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;

@Path("/knapsack")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Solver {
    @Inject
    private SolverManager<KnapsackSolution, UUID> solverManager;

    @POST
    @Path("/solve")
    public KnapsackSolution solve(KnapsackSolution problem) {
        UUID problemId = UUID.randomUUID()

        SolverJob<KnapsackSolution, UUID> solverJob = solverManager.solve(problemId, problem);
        KnapsackSolution solution;
        try {
            solution = solverJob.getFinalBestSolution();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException("Solving failed.", e);
        }
        return solution;
    }
}