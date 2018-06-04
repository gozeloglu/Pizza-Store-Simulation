public class HotPepper extends DecoratorPizza {
    protected String description = "HotPepper ";
    protected Pizza pizza;

    public HotPepper(Pizza pizza) {
        this.pizza = pizza;
        this.description += pizza.getDescription();
        //System.out.println("HotPepper added");
    }

    public HotPepper() {

    }

    @Override
    public int cost() {
        return pizza.cost() + 1;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
