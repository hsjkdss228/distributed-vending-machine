import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SelectPaymentGUI extends JFrame {

    JButton cashButton = new JButton("현금으로 결제");
    JButton cardButton = new JButton("카드로 결제");
    JButton cancelButton = new JButton("취소");
    DVM DVM;
    Item Selected_Item;
    Container ct = getContentPane();

    SelectPaymentGUI(DVM dvm, Item Selected_item, int x, int y) {
        this.Selected_Item = Selected_item;
        this.DVM = dvm;
        setTitle(String.format("%s - Payment method choose", DVM.getRegion()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(x, y);
        ct.setLayout(new FlowLayout());

        ct.add(cashButton);
        ct.add(cardButton);
        ct.add(cancelButton);
        setSize(500, 100);
        InitListener();
        setVisible(true);
    }

    void InitListener() {
        cashButton.addActionListener(event -> {
            CashGUI cashGUI = new CashGUI(DVM, Selected_Item, getLocation().x, getLocation().y);
            dispose();
        });
        cardButton.addActionListener(event -> {
            CardGUI cardGUI = new CardGUI(DVM, Selected_Item, getLocation().x, getLocation().y);
            dispose();
        });
        cancelButton.addActionListener(event -> {
            dispose();
            ItemListGUI ILF = new ItemListGUI(DVM, getLocation().x, getLocation().y);
        });
    }
}
