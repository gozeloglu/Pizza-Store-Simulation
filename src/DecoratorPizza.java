public class DecoratorPizza implements Pizza {
    protected String description;
    protected Pizza pizza;
    protected int pizzaCost;

    @Override
    public int cost() {
        return pizzaCost;
    }


    @Override
    public String getDescription() {
        return description;
    }
}
