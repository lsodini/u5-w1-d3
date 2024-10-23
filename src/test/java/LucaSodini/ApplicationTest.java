package LucaSodini;

import LucaSodini.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.awt.SystemColor.menu;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {


    @Autowired
    private Menu menu;

    @Test
    public void testCreazionePizza() {
        System.out.println("TEST 1");
        Pizza pizza = new Pizza("Margherita", 5.0, 600);
        assertEquals("Margherita", pizza.getName());
        assertEquals(5.0, pizza.getPrice());
        assertEquals(600, pizza.getCalories());
    }

    @Test
    public void testAggiuntaTopping() {
        System.out.println("TEST 2");
        Pizza pizza = new Pizza("Margherita", 5.0, 600);
        Topping prosciutto = new Topping("Prosciutto", 2.0, 100);

        pizza.addTopping(prosciutto);

        assertEquals(7.0, pizza.getPrice());
        assertEquals(700, pizza.getCalories());
    }

    @Test
    public void testCalcolaTotaleOrdine() {
        System.out.println("TEST 3");
        Pizza pizza = menu.getPizzas().get(0);
        Drink cola = menu.getDrinks().get(0);


        double costoCoperto = 2.0;
        Ordine ordine = new Ordine(new Tavolo(1, 4, true), 4, costoCoperto);
        ordine.aggiungiItem(pizza);
        ordine.aggiungiItem(cola);

        double totale = ordine.calcolaTotale();
        assertEquals(15.0, totale);
    }

    @Test
    public void testCalcolaTotaleOrdineWithTopping() {
        System.out.println("TEST 4");
        Pizza pizza = menu.getPizzas().get(0);
        Topping prosciutto = menu.getToppings().get(0);
        Drink cola = menu.getDrinks().get(0);


        double costoCoperto = 2.0;
        Ordine ordine = new Ordine(new Tavolo(1, 4, true), 4, costoCoperto);
        pizza.addTopping(prosciutto);
        ordine.aggiungiItem(pizza);
        ordine.aggiungiItem(cola);

        double totale = ordine.calcolaTotale();
        assertEquals(17.0, totale);
    }

    @Test
    public void testCalcolaTotaleOrdineWithMultipleItems() {
        System.out.println("TEST 5");
        Pizza pizzaMargherita = menu.getPizzas().get(0);
        Pizza pizzaHawaiian = menu.getPizzas().get(1);
        Drink cola = menu.getDrinks().get(0);


        double costoCoperto = 2.0;
        Ordine ordine = new Ordine(new Tavolo(1, 4, true), 4, costoCoperto);
        ordine.aggiungiItem(pizzaMargherita);
        ordine.aggiungiItem(pizzaHawaiian);
        ordine.aggiungiItem(cola);

        double totale = ordine.calcolaTotale();
        assertEquals(23.5, totale);
    }
}
