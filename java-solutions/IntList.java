import java.util.Arrays;

public class IntList {
    private int[] numbers;
    private int counterNum;
    
    IntList() {
        this.numbers = new int[1];
        this.counterNum = 0;
    }

    IntList(int number) {
        this.numbers = new int[1];
        this.counterNum = 0;
        add(number);
    }

    public int getLength() {
        return counterNum;
    }

    public int getElement(int index) {
        if (counterNum > index - 1) {
            return numbers[index];
        }
        return -1;
    }

    public void changeElement(int index, int value) {
        if (counterNum > index - 1) {
            numbers[index] = value;
        }
    }

    public void add(int number) {
        if (counterNum == numbers.length) {
            numbers = Arrays.copyOf(numbers, numbers.length * 2);
        }
        numbers[counterNum++] = number;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(counterNum);
        for (int i = 0; i < counterNum; i++) {
            result.append(" " + numbers[i]);
        }
        return result.toString();
    }
}
