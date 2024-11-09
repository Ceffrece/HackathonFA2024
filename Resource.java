public class Resource {
    private double food;
    private double water; 
    private double energy;
    private double fuel;
    
    public Resource(double food, double water, double energy, double fuel) {
        this.food = food;
        this.water = water;
        this.energy = energy;
        this.fuel = fuel;
    }


    public double getFood() {
        return food;
    }

    public void setFood(double food) {
        this.food = food;
    }

    
    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    
    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    
    public void displayResources() {
        System.out.println("Resource Information:");
        System.out.println("Food: " + food + " kg");
        System.out.println("Water: " + water + " L");
        System.out.println("Energy: " + energy + " kWh");
        System.out.println("Fuel: " + fuel + " L");
    }

    
    public void useResources(double foodUsage, double waterUsage, double energyUsage, double fuelUsage) {
        if (food - foodUsage >= 0) food -= foodUsage;
        if (water - waterUsage >= 0) water -= waterUsage;
        if (energy - energyUsage >= 0) energy -= energyUsage;
        if (fuel - fuelUsage >= 0) fuel -= fuelUsage;

        System.out.println("Resources updated after usage.");
    }

    
    public boolean areResourcesLow() {
        return food <= 0 || water <= 0 || energy <= 0 || fuel <= 0;
    }

    public static void main(String[] args) {
        
        Resource exampleConstraint = new Resource(100, 200, 1500, 300);
        
        exampleConstraint.displayResources(); 

        
        exampleConstraint.useResources(20, 50, 200, 30);
        exampleConstraint.displayResources(); 

        
        if (exampleConstraint.areResourcesLow()) {
            System.out.println("WARNING: Some resources are running low.");
        } else {
            System.out.println("Resources are sufficient.");
        }
    }
}
