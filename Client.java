// Jeffery Zhang
// TA: Jake Page
// CSE 123
// Due: November 15th, 2023
// P2: Disaster Relief

// A client class to create optimal relief allocation scenarios, given
// simulations with a certain list of impacted locations and a budget.

import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 1000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // This is a method that finds the most optimal allocation scenario, given a 
    // double budget and a list of Location objects (B, P). *It returns the optimal 
    // allocation scenario - the one that saves the most amount of people from the
    // list of locations within the given budget, or for the least amount of money 
    // if there exists multiple scenarios with the most people helped* (R).
    public static Allocation allocateRelief(double budget, List<Location> sites) {
        return findOptimalAlloc(budget, new ArrayList<>(sites), new Allocation(), null);
    }

    // Finds and returns the most optimal allocation scenario (so far) based on a 
    // certain budget and list of locations (B, R). Takes in a double budget, a 
    // list of potential locations, an allocation representing the current
    // state of a candidate allocation, and the current most optimal allocation
    // (if any) (P). 
    private static Allocation findOptimalAlloc(double budget, List<Location> remainingLocs,
                                               Allocation allocSoFar, Allocation optimalAlloc) {
        if (!hasAffordable(budget, remainingLocs)) {
            return (optimalAlloc == null || allocSoFar.totalPeople() > optimalAlloc.totalPeople() ||
                    allocSoFar.totalPeople() == optimalAlloc.totalPeople() && 
                    allocSoFar.totalCost() < optimalAlloc.totalCost()) ? allocSoFar : optimalAlloc;
        }
        for (int i = 0; i < remainingLocs.size(); i++) {
            Location currLoc = remainingLocs.get(i);
            if (budget - currLoc.getCost() >= 0) {
                // choose
                budget -= currLoc.getCost();
                allocSoFar = allocSoFar.withLoc(currLoc);
                remainingLocs.remove(i);
                // explore
                optimalAlloc = findOptimalAlloc(budget, remainingLocs, allocSoFar, optimalAlloc);
                // unchoose
                budget += currLoc.getCost();
                allocSoFar = allocSoFar.withoutLoc(currLoc);
                remainingLocs.add(i, currLoc);
            }
        }
        return optimalAlloc;
    }

    // Returns a boolean to check if there exists any affordable locations to add 
    // to the current allocation (B, R). Takes in a double budget and a list of
    // location candidates (P).
    private static boolean hasAffordable(double budget, List<Location> remainingLocs) {
        for (Location loc : remainingLocs) {
            if (loc.getCost() <= budget) {
                return true;
            }
        }
        return false;
    }

    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();
        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 1000));
        result.add(new Location("Location #2", 100, 1000));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}


