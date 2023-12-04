import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class GUI implements ActionListener {

    JFrame frame;
    JPanel[] panels;
    JButton[] buttons;
    JTextField[] textFields;

    GUI() {
        frame = new JFrame();
        frame.setTitle("Kalender");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 700);
        frame.setMinimumSize(new Dimension(700, 350));
        frame.setLocationRelativeTo(null);

        buttons = new JButton[7];
        textFields = new JTextField[7];
        panels = new JPanel[7];

        myPanels();
        addDateLabel();
        dateToday();
        eventMaker();
        frame.setVisible(true);
    }


    void myPanels() {
        Border myBorder = BorderFactory.createLineBorder(Color.BLUE);
        frame.setLayout(new GridLayout(0, 7, 2, 0));

        for (int i = 0; i < 7; i++) {
            panels[i] = new JPanel();
            panels[i].setBorder(myBorder);
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
            frame.add(panels[i]);

            JScrollPane scrollPane = new JScrollPane(panels[i]);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


            frame.add(scrollPane);
        }
    }


    void eventMaker() {
        for (int i = 0; i < 7; i++) {
            buttons[i] = new JButton("Skapa event");
            textFields[i] = new JTextField();
            textFields[i].setPreferredSize(new Dimension(400, 20)); //
            textFields[i].setMaximumSize(new Dimension(400, 20));
            panels[i].add(textFields[i]);
            panels[i].add(buttons[i]);

            buttons[i].addActionListener(this);
        }
    }


    void dateToday() {
        LocalDate markDay = LocalDate.now();
        int markDayNumber = markDay.getDayOfWeek().getValue();

        panels[markDayNumber - 1].setBackground(Color.GREEN);

    }

    void addDateLabel() {
        LocalDate now = LocalDate.now();
        LocalDate monday = now.minusDays(now.getDayOfWeek().getValue());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd/MM");

        for (int i = 0; i < 7; i++) {
            LocalDate eachDay = monday.plusDays(i + 1);
            String daysPlus = eachDay.format(formatter);
            JLabel day = new JLabel(daysPlus);
            panels[i].add(day);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 7; i++) {
            if (e.getSource() == buttons[i]) {
                String text = textFields[i].getText();
                JLabel label = new JLabel(text);
                panels[i].add(label);
                panels[i].repaint();
                panels[i].revalidate();

            }
        }
    }
}