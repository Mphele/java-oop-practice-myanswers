## Section A — OOP Theory (20%)

1. Encapsulation is the act of making sure data is only accessible and modifiable by the specific class it is created in and by other classes with permission. 
The way this is enforced is through access modifiers (public, private, protected keywords) an example of this in vehicle is we create mutliple attributes such
as make and model using the private keyword and we create getter methods for them using the public keyword. Making them only accessible through the method not 
through he actual attribute itself, a much safer way of sharing attributes. Abstraction is the act of keeping implementation details away from a class that does 
not need to know how a method is implemented it just know when to use it. An example within Vehicle is the calculateRentCost method. The vehicle class does not need
to know the mathematical formula behind this calculation, all it knows is that when it enters the amount of days it was rented for, and that calculates the cost.
And by having this as an abstract class the differnet vehicle types can then implement their own formula 

2. Making this a normal method would break the seperation of concerns rule. It does not make sense for a class dealt with creating and managing base vehicle objects 
to implement an administrative method that calculates rental cost, this should be implemented in a more specific class that inherits from the base class such as a truck.
These child classes are more exact and can now implement the calculation for their exact specifications rather than a vehicle that is inherently broad.

3. Method overloading is when you create multiple methods with the same name and return type but differ in the amount of arguments. An example of this a car class can have
is by having 2 different setNumberOfSeats methods. One takes in the number of seats (as the current car class) and the other takes in number of seats and car type. This enables
the methods to be useful depending on how much information is at the disposal of the system. With a car type added the method can further validate that the desired seat allocation
is valid. for example a supercar cannot have more than 2 seats, a validation that could not be done with the one argument version of the method. 
Method overriding is the act of using the same method , signature and all, of another class but changing the implementation. an example of this in car is overriding the toString method. 
This is a method that already exists within java but this class overrides it to ensure it prints crucial class information that the original method would not have access to.

4. This is a great example of Encapsulation. By making the boolean immutable and not giving it a setter you ensure the value cannot be changed at all once it has been defined. A
developer opts for making this decision in situations where they do not want the value to be changed under any circumstance. This class is a great example as the fact of a bakkie 
being a double cab or not is not something that can realistically be changed, it either is or isn't therefore this should not be mutable.

5. Composition is the act of creating or interacting with an instance of one class within another, this is defined as a "has-a" relationship. Inheritance is the act of inheriting the 
aspects of another class and is defined as a "is-a" kind of relationship, for example car inherits from vehicle because a car is a vehicle. The relationship between RentalAgency and Vehicle
is an example of composition as the RentalAgency interacts with multiple vehicle objects and adds them to it's fleet.

6. ```java

    public class Motorbike extends Vehicle{
    
    public static final double DAILY_RATE = 300.00;
    private final int engineCC;
    public Motorbike(String registrationNumber, String make, String model, int year, int engineCC) {
        super(registrationNumber, make, model, year);
        this.engineCC = engineCC;
    }
    
    public int engineCC(){
        return engineCC;
    }

    @Override
    public double calculateRentalCost(int days) {
        if(days<1){
            throw new IllegalArgumentException();
            
        }
        
        return DAILY_RATE * days;
    }
    }

```

