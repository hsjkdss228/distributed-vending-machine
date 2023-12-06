import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PaymentTest {

    // 정상
    @Test
    void calculatePriceCash() {
        Payment myPayment = new Payment();
        int inputCash = 2000;
        Item selectedItem = new Item("Coke", 1234, 1200, 3);
        int result = myPayment.calculatePriceCash(inputCash, selectedItem);
        assertEquals(800, result);
    }

    // 입금이 부족한 경우
    @Test
    void calculatePriceCash2() {
        Payment myPayment = new Payment();
        int inputCash = 1000;
        Item selectedItem = new Item("Coke", 1234, 1200, 3);
        int result = myPayment.calculatePriceCash(inputCash, selectedItem);
        assertTrue(result == -1);
    }

    // itemPrice가 음수일 경우
    @Test
    void calculatePriceCash3() {
        Payment myPayment = new Payment();
        int inputCash = 0;
        Item selectedItem = new Item("Coke", 1234, -1200, 3);
        int result = myPayment.calculatePriceCash(inputCash, selectedItem);
        assertTrue(result == -2);
    }

    //============================================================

    // 정상
    @Test
    void isSufficient() {
        Payment myPayment = new Payment();
        int limit = 2000;
        int itemPrice = 1200;
        boolean result = myPayment.isSufficient(limit, itemPrice);
        assertTrue(result);
    }

    // 입금이 부족한 경우
    @Test
    void isSufficient2() {
        Payment myPayment = new Payment();
        int limit = 2000;
        int itemPrice = 2200;
        boolean result = myPayment.isSufficient(limit, itemPrice);
        assertTrue(!result);
    }

    //============================================================

    // 정상
    @Test
    void calculateChange() {
        Payment myPayment = new Payment();
        int inputCash = 2000;
        int itemPrice = 1200;
        int result = myPayment.calculateChange(inputCash, itemPrice);
        assertEquals(800, result);
    }

    //============================================================

    // 정상
    @Test
    void calculatePriceCard() {
        Payment myPayment = new Payment();
        String inputCardNumber = "1234";
        Item selectedItem = new Item("Coke", 1234, 1200, 3);
        int result = myPayment.calculatePriceCard(inputCardNumber, selectedItem);
        assertEquals(1, result);
    }

    // 불량 카드
    @Test
    void calculatePriceCard2() {
        Payment myPayment = new Payment();
        String inputCardNumber = "0000";
        Item selectedItem = new Item("Coke", 1234, 1200, 3);
        int result = myPayment.calculatePriceCard(inputCardNumber, selectedItem);
        assertEquals(-1, result);
    }

    // 잔액부족
    @Test
    void calculatePriceCard3() {
        Payment myPayment = new Payment();
        String inputCardNumber = "3572";
        Item selectedItem = new Item("Coke", 1234, 1200, 3);
        int result = myPayment.calculatePriceCard(inputCardNumber, selectedItem);
        assertEquals(0, result);
    }

    // 아이템 가격 음수
    @Test
    void calculatePriceCard4() {
        Payment myPayment = new Payment();
        String inputCardNumber = "3572";
        Item selectedItem = new Item("Coke", 1234, -1200, 3);
        int result = myPayment.calculatePriceCard(inputCardNumber, selectedItem);
        assertEquals(-2, result);
    }

    //============================================================

    // 정상
    @Test
    void consumeCard() {
        Payment myPayment = new Payment();
        String cardNumber = "0088";
        int itemPrice = 1200;
        myPayment.consumeCard(cardNumber, itemPrice);
        int remain = Payment.validCardList.get("0088");
        assertEquals(2800, remain);
    }

    //============================================================

    // 정상
    @Test
    void isValidCard() {
        Payment myPayment = new Payment();
        String cardNumber = "1234";
        boolean result = myPayment.isValidCard("1234");
        assertTrue(result);
    }

    // 유효하지 않은 카드인 경우
    @Test
    void isValidCard2() {
        Payment myPayment = new Payment();
        String cardNumber = "0000";
        boolean result = myPayment.isValidCard("0000");
        assertTrue(!result);
    }
}
