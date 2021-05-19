public class Automaton {
    private Rule rule;
    private Generation[] generations;

    public Automaton(int ruleNum, int verticalSize, int size) {
        StringBuilder zeroes = new StringBuilder();
        String ones = "1";
        String passable;

        for (int i = 0; i < (size / 2); i++) {
            zeroes.append("0");
        }

        passable = zeroes + ones + zeroes;

        generations = new Generation[verticalSize + 1];
        generations[0] = new Generation(passable, '1');
        rule = new Rule(ruleNum);
    }

    public void evolve(int index) {
        if (index == 0) return;

        generations[index] = rule.evolve(generations[index - 1]);
    }

    public Generation getGeneration(int index) {
        return generations[index];
    }


}