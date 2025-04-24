package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {

    @Test
    void test() {
        ArrayStack arrayStack = new ArrayStack(15);
        try{
arrayStack.push(new Person(1,"Alana", 18));
arrayStack.push(new Person(2,"Pablo",20));
arrayStack.push(new Person(3, "Ana",21));
arrayStack.push(new Person(4,"Maria", 20));
            arrayStack.push(new Person(5,"Victoria", 18));
            arrayStack.push(new Person(6,"Nicole", 19));
            arrayStack.push(new Person(7,"Mateo", 18));
            arrayStack.push(new Person(8,"Nicole", 23));
            arrayStack.push(new Person(9,"Alana", 22));
            arrayStack.push(new Person(10,"Pablo", 19));
            arrayStack.push(new Person(11,"Ana", 18));
            System.out.println("Personas ");
            System.out.println(arrayStack);
            System.out.println("\nCase 1:");
            ArrayStack aux1 = new ArrayStack(arrayStack.size());
            int size1 = arrayStack.size();

            System.out.println("\nCase 2:");


            for (int i = 0; i < size1; i++) {
                Person p = (Person) arrayStack.pop();
                if (!(p.getAge() == 18 && p.getName().startsWith("A"))) {
                    aux1.push(p);
                }
            }

            while (!aux1.isEmpty()) {
                arrayStack.push(aux1.pop());
            }
            System.out.println(arrayStack);

        } catch (StackException ex) {
           System.out.println(ex.getMessage());
        }



//            try {
//                for (int i = 0; i < 10; i++) {
//                    int value = util.Utility.random(30);
//                    System.out.println("Value ["+ value+"] pushed");
//                    arrayStack.push(value);
//                }
//                System.out.println(arrayStack);
//                System.out.println(arrayStack);
//            } catch (StackException ex) {
//                System.out.println(ex.getMessage());
//            }

        }
    }
