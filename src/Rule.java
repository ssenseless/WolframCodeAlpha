import org.jetbrains.annotations.NotNull;

public class Rule {
    private int wolframCode;
    private String wolframCodeBinary;
    private boolean[] ruleArray;

    public Rule(int ruleNum) {
        wolframCode = ruleNum;

        wolframCodeBinary =
                String.format("%8s", Integer.toBinaryString(ruleNum)).replace(' ', '0');
        ruleArray = binaryToArray(wolframCodeBinary);
    }

    public int getRuleNum() {
        return wolframCode;
    }

    public int getRuleNumBinary() {
        return Integer.parseInt(wolframCodeBinary);
    }

    public static boolean[] getNeighborhood(int index, Generation gen) {
        boolean[] neighborhood = new boolean[3];
        if (index == 0) {
            neighborhood[0] = gen.getState(gen.getSize() - 1);
            neighborhood[1] = gen.getState(index);
            neighborhood[2] = gen.getState(index + 1);
        }
        else if (index == gen.getSize() - 1) {
            neighborhood[0] = gen.getState(index - 1);
            neighborhood[1] = gen.getState(index);
            neighborhood[2] = gen.getState(0);
        }
        else {
            neighborhood[0] = gen.getState(index - 1);
            neighborhood[1] = gen.getState(index);
            neighborhood[2] = gen.getState(index + 1);
        }
        return neighborhood;
    }

    public boolean evolve(boolean @NotNull [] neighborhood) {
        StringBuilder switchKey = new StringBuilder();
        boolean returnValue = false;

        for (boolean key : neighborhood) {
            if (key)
                switchKey.append("1");
            else
                switchKey.append("0");
        }

        switch (switchKey.toString()) {
            case "111":
                returnValue = ruleArray[0];
                break;
            case "110":
                returnValue = ruleArray[1];
                break;
            case "101":
                returnValue = ruleArray[2];
                break;
            case "100":
                returnValue = ruleArray[3];
                break;
            case "011":
                returnValue = ruleArray[4];
                break;
            case "010":
                returnValue = ruleArray[5];
                break;
            case "001":
                returnValue = ruleArray[6];
                break;
            case "000":
                returnValue = ruleArray[7];
                break;
            default:
                System.out.println("Something wrong in evolve switch. Returning false.");
                break;
        }
        return returnValue;
    }

    public Generation evolve(Generation gen) {
        StringBuilder binaryRep = new StringBuilder();

        for (int i = 0; i < gen.getSize(); i++) {
            if (evolve(getNeighborhood(i, gen)))
                binaryRep.append("1");
            else
                binaryRep.append("0");
        }
        return new Generation(binaryRep.toString(), '1');
    }

    public boolean[] binaryToArray(String binaryRep) {
        boolean[] returnArray = new boolean[8];

        for (int i = 0; i < 8; i++) {
            returnArray[i] = binaryRep.charAt(i) == '1';
        }
        return returnArray;
    }
}
