package code.of.the.rings;

import java.util.ArrayList;
import java.util.List;


public class Player {

    public static void main(String args[]) {
        String magicPhrase = "UMNE TALMAR RAHTAINE NIXENEN UMIR";
        String output = "+++++++++++++++++++++.--------.+.---------.>.++++++++++++++++++++.-------------------.+++++++++++.+.------------.+++++++++++++++++.>.++++++++++++++++++.-----------------.+++++++.++++++++++++.-------------------.++++++++.+++++.---------.>.++++++++++++++.-----.+++++++++++++++.-------------------.+++++++++.---------.+++++++++.>.+++++++++++++++++++++.--------.----.+++++++++.";

        char[] chars = magicPhrase.toCharArray();

        int whitespace = 64; //for upper case

        List<Integer> positions = new ArrayList<>();
        for (Character c : chars) {
            positions.add((int) c - whitespace);
        }
        Bilbo bilbo = new Bilbo();
        int lastCharPos = 0;
        for (Integer charPosition : positions) {
            if (charPosition == -32) {
                bilbo.moveRight();
                bilbo.trigger();
                lastCharPos = 0;
                continue;
            }
            int diff = Math.abs(charPosition - lastCharPos);
            if (diff < 26) {
                //stay
                if (lastCharPos - charPosition < 0) {
                    //increment
                    bilbo.increment(charPosition - lastCharPos);
                } else {
                    //decrement
                    bilbo.decrement(lastCharPos - charPosition);
                }
            } else {
                //move forward
                bilbo.moveRight();
                if (lastCharPos - charPosition < 0) {
                    //increment
                    bilbo.increment(charPosition - lastCharPos);
                } else {
                    //decrement
                    bilbo.decrement(lastCharPos - charPosition);

                }
            }
            lastCharPos = charPosition;
            bilbo.trigger();
        }

        System.out.println(bilbo.toString());
//        if (!output.equals(bilbo.toString())) {
//            System.out.println("wrong");
//        } else {
//            System.out.println("correct");
//        }
    }


    static class Bilbo {

        private StringBuilder sb;

        public Bilbo() {
            sb = new StringBuilder();
        }

        public void moveLeft() {
            sb.append("<");
        }

        public void moveRight() {
            sb.append(">");
        }

        public void increment(int i) {
            for (int j = 0; j < i; j++) {
                sb.append("+");
            }
        }

        public void decrement(int i) {
            for (int j = 0; j < i; j++) {
                sb.append("-");
            }
        }

        public void trigger() {
            sb.append(".");
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }
}