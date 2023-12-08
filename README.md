# Programming Assignment 2: Disaster Relief

**Assignment Spec:**

When natural disasters strike, governments, relief organizations, and even individual donors must often wrestle with how best to allocate available resources to help those who have been affected. This is generally a very complex decision, balancing countless logistical, economic, political, and other factors. One particular challenge is that different geographic areas can require different financial or other resources for relief, even if the populations of the areas are similar. (Or, put another way, the cost to help a single person after a disaster is not always constant.) Organizations sometimes have to make difficult decisions in the hope of helping as many people as possible with the available resources.

In this assignment, you will implement a system to determine how to allocate a budget of relief resources to help as many people as possible.

For this assignment, you will implement only a single method:

**public static Allocation allocateRelief(double budget, List<Location> sites)**

This method takes a budget and a list of Location objects as parameter. The method will compute and return the allocation of resources that will result in the most people being helped with the given budget. If there is more than one allocation that will result in the most people being helped, the method will return the allocation that costs the least. If there is more than one allocation that will result in the most people being helped for the lowest cost, you may return any of these allocations.

For the purposes of our simulation, we will assume that providing relief to a location is atomic, meaning that either all people in the location are helped and the full cost is paid, or no relief is allocated to that location. We will not deal with the possibility of providing partial relief to a particular location.

You should implement your allocateRelief method where indicated in the provided Client.java file. You may also implement any additional helper methods you might like. (For example, you will likely want to implement a public-private pair for allocateRelief.)
