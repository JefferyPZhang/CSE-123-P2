import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {
    @Test
    @DisplayName("STUDENT TEST - only one is affordable")
    public void firstCaseTest() {
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 100, 400);
        Location secondLoc = new Location("Location #2", 150, 600);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);
        Set<Location> actual = Client.allocateRelief(500, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - both affordable, one has more population")
    public void secondCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 150, 400);
        Location secondLoc = new Location("Location #2", 100, 450);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);
        Set<Location> actual = Client.allocateRelief(budget, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - both same population and affordable, one more expensive than the other")
    public void thirdCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 150, 450);
        Location secondLoc = new Location("Location #2", 150, 400);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(secondLoc);
        Set<Location> actual = Client.allocateRelief(budget, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }
}

