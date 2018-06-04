public class Salami extends DecoratorPizza {
    protected String description = "Salami ";
    protected Pizza pizza;

    public Salami() {

    }
    public Salami(Pizza pizza) {
        this.pizza = pizza;
        this.description += pizza.getDescription();
        //System.out.println("Salami added");

    }

    @Override
    public int cost() {
        return pizza.cost() + 3;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
