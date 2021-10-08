package hackerrank.acm_icpc_team;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/acm-icpc-team
 */
public final class SolutionCore {
    private SolutionCore() {
    }

    protected static int[] bestTeam(final List<String> people) {
        int n = people.size();
        int m = people.get(0).length();
        int coverageCount = 0;
        int teamsCount = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int count = 0;
                for (int k = 0; k < m; k++) {
                    if (people.get(i).charAt(k) == '1' || people.get(j).charAt(k) == '1') {
                        count++;
                    }
                }
                if (count > coverageCount) {
                    coverageCount = count;
                    teamsCount = 1;
                } else if (count == coverageCount) {
                    teamsCount++;
                }
            }
        }
        return new int[] { coverageCount, teamsCount };
    }
}
