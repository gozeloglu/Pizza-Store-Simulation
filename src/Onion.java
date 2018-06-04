public class Onion extends DecoratorPizza {
    private String description = "Onion ";
    protected Pizza pizza;

    public Onion(Pizza pizza) {
        this.pizza = pizza;
        this.description += pizza.getDescription();
        //System.out.println("Onion added");
    }

    public Onion() {

    }
    @Override
    public int cost() {
        return pizza.cost() + 2;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
