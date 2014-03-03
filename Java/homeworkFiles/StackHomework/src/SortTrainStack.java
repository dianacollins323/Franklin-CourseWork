import java.util.Stack;
import java.io.*;
import java.util.Scanner;

public class SortTrainStack {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SortTrainStack train = new SortTrainStack();
        Stack<Integer> input = new Stack<Integer>();
        Stack<Integer> siding = new Stack<Integer>();
        Stack<Integer> exit = new Stack<Integer>();
        String cars = null;
        int i = 0;

        /**
         * reads the car number file using readFile()
         */
        try {
            cars = (train.readFile());
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        /**
         * splits cars String and populates input stack
         */
        if (cars != null) {
            String [] carFrags = cars.split(" ");
            while (i < carFrags.length) {
                int carNum = Integer.parseInt(carFrags[i]);
                input.push(carNum);
                i++;
            }
        }
        else {
            System.out.println("There are no cars to sort.");
        }

        /**
         * Compares stacks to find smallest element
         * Prints instructions to move smallest 
         * element to exit track
         */
        while (!input.empty() || !siding.empty()) {
            int smallestInput = train.smallestElementInStack(input);
            int smallestSiding = train.smallestElementInStack(siding);

            if (smallestSiding < 0) {
                if (smallestInput == input.peek()) {
                    System.out.println("Move " + input.peek() + " from input to exit.");
                    exit.push(input.pop());
                }
                else {
                    System.out.println("Move " + input.peek() + " from input to siding.");
                    siding.push(input.pop());
                }
            }
            else if (smallestInput < 0) {
                if (smallestSiding == siding.peek()) {
                    System.out.println("Move " + smallestSiding + " from siding to exit.");
                    exit.push(siding.pop());
                }
                else {
                    System.out.println("Move " + smallestSiding + " from siding to input.");
                    input.push(siding.pop());
                }
            }
            else {
                if (smallestInput < smallestSiding) {
                    if (smallestInput == input.peek()) {
                        System.out.println("Move " + input.peek() + " from input to exit.");
                        exit.push(input.pop());
                    }
                    else {
                        System.out.println("Move " + input.peek() + " from input to siding.");
                        siding.push(input.pop());
                    }
                }
                else {
                    if (smallestSiding == siding.peek()) {
                        System.out.println("Move " + smallestSiding + " from siding to exit.");
                        exit.push(siding.pop());
                    }
                    else {
                        System.out.println("Move " + smallestSiding + " from siding to input.");
                        input.push(siding.pop());
                    }
                }
            }
        }

        /**
         * Remove the comments from the following 
         * lines to print the exit track 
         * first in will print first
         * last in will print last
         */
        //Stack<Integer> temp = new Stack<Integer>();
        //while (!exit.empty()) {
        //    temp.push(exit.pop());
        //}
        //while (!temp.empty()) {
        //    System.out.println(temp.peek());
        //    exit.push(temp.pop());
        //}
    }

    /**
     * reads file containing car numbers
     * 
     * @return String of car numbers
     * @throws FileNotFoundException
     */
    public String readFile() throws FileNotFoundException {
        FileReader inFile = new FileReader("/Users/dianacollins/Desktop/cars.txt");
        Scanner readFile = null;
        try {
            readFile = new Scanner(inFile);
            String carNumbers = readFile.nextLine();
            return carNumbers;
        }
        finally {
            readFile.close();
        }
    }

    /**
     * searches stack and returns smallest element
     * 
     * @param stack, stack to search
     * @return value of smallest element in stack
     */
    public int smallestElementInStack(Stack<Integer> stack) {
        int smallest = -1;

        if (!stack.empty()) {
            smallest = stack.peek();
            Stack<Integer> temp = new Stack<Integer>();

            while (!stack.empty()) {
                temp.push(stack.pop());
                if (stack.empty() || temp.peek() < stack.peek()) {
                    if (temp.peek() < smallest) {
                        smallest = temp.peek();
                    }
                }
            }

            while (!temp.empty()) {
                stack.push(temp.pop());
            }
        }

        return smallest;
    }

}
