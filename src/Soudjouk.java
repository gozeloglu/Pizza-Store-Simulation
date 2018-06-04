public class Soudjouk extends DecoratorPizza {
    protected String description = "Soudjouk ";
    protected Pizza pizza;

    public Soudjouk(Pizza pizza) {
        this.pizza = pizza;
        this.description += pizza.getDescription();
        //System.out.println("Soudjouk added");
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
