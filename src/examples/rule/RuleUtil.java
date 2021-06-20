package examples.rule;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadeen
 */
public class RuleUtil {
    /**
     * Initializes the rules accordingly
     * @return 
     */
    public static List<Rule> createRulesList() {
        List<Rule> rules = new ArrayList<>();
        rules.add(new RowRule());
        rules.add(new ColumnRule());
        rules.add(new DiagonalRule());

        return rules;
    }

}
