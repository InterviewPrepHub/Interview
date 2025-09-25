package com.interview.Interview.Interview2025.CapitalOne.Round1;

/*
There are two hubs: Alpha and Beta, connected by shuttles.

Shuttles from Alpha → Beta take 100 units of time and depart at times in sorted array alpha2beta.

Shuttles from Beta → Alpha also take 100 units of time and depart at times in sorted array beta2alpha.
You must complete missions, where each mission = go Alpha → Beta → Alpha.
Always take the earliest available shuttle.
Return the time when all missions are completed.

Example:
alpha2beta = [0,200,500], beta2alpha = [99,210,450], missions = 1
→ Output: 310
 */
public class SpaceShuttleMissions {

    // alpha2beta[i] and beta2alpha[j] are sorted departure times
    // Each flight takes exactly 100 time units.
    public static int solution(int[] alpha2beta, int[] beta2alpha, int missions) {
        int i = 0, j = 0;      // pointers into the schedules
        int t = 0;             // current time at the hub we're at (start at Alpha)

        while (missions-- > 0) {
            // Go Alpha -> Beta
            while (i < alpha2beta.length && alpha2beta[i] < t) i++;
            int departAB = alpha2beta[i];         // guaranteed to exist
            int arriveB  = departAB + 100;

            // Return Beta -> Alpha
            while (j < beta2alpha.length && beta2alpha[j] < arriveB) j++;
            int departBA = beta2alpha[j];         // guaranteed to exist
            t = departBA + 100;                   // back at Alpha, update current time
        }
        return t;  // time when all missions are completed at Alpha
    }

    public static void main(String[] args) {
        int[] a2b = {0, 200, 500};
        int[] b2a = {99, 210, 450};
        int missions = 1;
        System.out.println(solution(a2b, b2a, missions)); // 310
    }
}
