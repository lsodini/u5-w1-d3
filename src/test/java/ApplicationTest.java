import LucaSodini.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private Ordine ordine;
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

        assertEquals(7.0, pizza.getPrice()); // Prezzo aggiornato
        assertEquals(700, pizza.getCalories()); // Calorie aggiornate
    }

    @Test
    public void testCalcolaTotaleOrdine() {
        Pizza pizza = new Pizza("Margherita", 5.0, 600);
        Drink cola = new Drink("Cola", 2.0, 150);

        Ordine ordine = new Ordine(new Tavolo(1, 4,false), 4, 1.5); // 4 coperti, 1.5 costo per coperto
        ordine.aggiungiItem(pizza);
        ordine.aggiungiItem(cola);

        double totale = ordine.calcolaTotale();
        assertEquals(14.0, totale); // (5.0 + 2.0) + (4 * 1.5) = 14.0
    }
}