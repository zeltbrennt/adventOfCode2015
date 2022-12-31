import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 implements AOCProblem {

    private final List<String> puzzle;

    public Day07(String puzzleFile) {
        this.puzzle = InputReader.multipleLines(puzzleFile);
        parseInput();
    }

    private void parseInput() {
        Pattern notGate = Pattern.compile("NOT");
        Pattern orGate = Pattern.compile("OR");
        Pattern andGate = Pattern.compile("AND");
        Pattern xorGate = Pattern.compile("XOR");
        Pattern lsGate = Pattern.compile("LSHIFT");
        Pattern rsGate = Pattern.compile("RSHIFT");
        for (String line : puzzle) {
            String[] components = line.split(" ");
            if (notGate.matcher(line).find()) {
                new NotGate(components[3], components[1]);
            } else if (orGate.matcher(line).find()) {
                new OrGate(components[4], components[0], components[2]);
            } else if (andGate.matcher(line).find()) {
                new AndGate(components[4], components[0], components[2]);
            } else if (xorGate.matcher(line).find()) {
                new XorGate(components[4], components[0], components[2]);
            } else if (lsGate.matcher(line).find()) {
                new LShift(components[4], components[0], components[2]);
            } else if (rsGate.matcher(line).find()) {
                new RShift(components[4], components[0], components[2]);
            } else {
                new Wire(components[2], components[0]);
            }

        }
    }

    @Override
    public int solvePart1() {
        Map<String, Component> assembled = Component.PARTS;
        return assembled.get("a").produceOutput();
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}

abstract class Component {

    static final Pattern NUMBER = Pattern.compile("\\d+");
    static final Map<String, Component> PARTS = new HashMap<>();

    private String name;

    public Component(String name) {
        this.name = name;
        PARTS.put(name, this);
    }

    public static Component getPart(String part) {
        return PARTS.get(part);
    }

    public abstract int produceOutput();
}

abstract class OneInputComponent extends Component {

    private final String input;

    public OneInputComponent(String name, String input) {
        super(name);
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}

class Wire extends OneInputComponent {

    public Wire(String name, String input) {
        super(name, input);
    }


    @Override
    public int produceOutput() {
        Matcher numeric = Component.NUMBER.matcher(getInput());
        return numeric.find() ? Integer.parseInt(getInput()) : Component.getPart(getInput()).produceOutput();
    }
}

class NotGate extends OneInputComponent {


    public NotGate(String name, String input) {
        super(name, input);
    }

    @Override
    public int produceOutput() {
        int value = Component.NUMBER.matcher(getInput()).find() ? Integer.parseInt(getInput()) : Component.getPart(getInput()).produceOutput();
        return ~value;
    }
}

abstract class TwoInputComponent extends Component {

    private final String inputA;
    private final String inputB;

    public TwoInputComponent(String name, String inputA, String inputB) {
        super(name);
        this.inputA = inputA;
        this.inputB = inputB;
    }

    public String getInputA() {
        return inputA;
    }

    public String getInputB() {
        return inputB;
    }
}

class AndGate extends TwoInputComponent {

    public AndGate(String name, String inputA, String inputB) {
        super(name, inputA, inputB);

    }

    @Override
    public int produceOutput() {
        int valueA = Component.NUMBER.matcher(getInputA()).find() ? Integer.parseInt(getInputA()) : Component.getPart(getInputA()).produceOutput();
        int valueB = Component.NUMBER.matcher(getInputB()).find() ? Integer.parseInt(getInputB()) : Component.getPart(getInputB()).produceOutput();
        return valueA & valueB;
    }
}

class OrGate extends TwoInputComponent {


    public OrGate(String name, String inputA, String inputB) {
        super(name, inputA, inputB);
    }

    @Override
    public int produceOutput() {
        int valueA = Component.NUMBER.matcher(getInputA()).find() ? Integer.parseInt(getInputA()) : Component.getPart(getInputA()).produceOutput();
        int valueB = Component.NUMBER.matcher(getInputB()).find() ? Integer.parseInt(getInputB()) : Component.getPart(getInputB()).produceOutput();
        return valueA | valueB;
    }
}

class XorGate extends TwoInputComponent {

    public XorGate(String name, String inputA, String inputB) {
        super(name, inputA, inputB);
    }

    @Override
    public int produceOutput() {
        int valueA = Component.NUMBER.matcher(getInputA()).find() ? Integer.parseInt(getInputA()) : Component.getPart(getInputA()).produceOutput();
        int valueB = Component.NUMBER.matcher(getInputB()).find() ? Integer.parseInt(getInputB()) : Component.getPart(getInputB()).produceOutput();
        return valueA ^ valueB;
    }
}

class LShift extends TwoInputComponent {
    public LShift(String name, String inputA, String inputB) {
        super(name, inputA, inputB);
    }

    @Override
    public int produceOutput() {
        int valueA = Component.NUMBER.matcher(getInputA()).find() ? Integer.parseInt(getInputA()) : Component.getPart(getInputA()).produceOutput();
        int valueB = Integer.parseInt(getInputB());
        return valueA << valueB;
    }
}

class RShift extends TwoInputComponent {
    public RShift(String name, String inputA, String inputB) {
        super(name, inputA, inputB);
    }

    @Override
    public int produceOutput() {
        int valueA = Component.NUMBER.matcher(getInputA()).find() ? Integer.parseInt(getInputA()) : Component.getPart(getInputA()).produceOutput();
        int valueB = Integer.parseInt(getInputB());
        return valueA >> valueB;
    }
}