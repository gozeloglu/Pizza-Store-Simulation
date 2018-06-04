public class Neapolitan extends DecoratorPizza {
    private String description = "Neapolitan";

    public Neapolitan() {

    }

    @Override
    public int cost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return description;
    }
}