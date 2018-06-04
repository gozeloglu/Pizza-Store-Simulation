public class AmericanPan extends DecoratorPizza {
    private String description = "AmericanPan";

    public AmericanPan() {
    }

    public int cost() {
        return 5;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
