import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameTable extends JFrame {

    public FrameTable(){

        super("Contatos");
        ContatoDAO dao = new ContatoDAO();
        ContatoTableModel tableModel = new ContatoTableModel(dao);
        JTable table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(table);
        add(scroll);
        JButton bt = new JButton("Adicionar");
        bt.addActionListener((event) -> {
            JFrame frame = new JFrame();

            // Criação do painel e campo de texto do nome.
            JPanel nome = new JPanel();
            nome.setLayout(new FlowLayout());
            nome.add(new JLabel("Nome: "));
            JTextField txtNome = new JTextField(20);
            nome.add(txtNome);
            frame.add(nome);

            // Criação do painel e campo de texto do email.
            JPanel email = new JPanel();
            email.setLayout(new FlowLayout());
            email.add(new JLabel("Email: "));
            JTextField txtEmail = new JTextField(20);
            email.add(txtEmail);
            frame.add(email);

            // Criação do painel e campo de texto do telefone.
            JPanel fone = new JPanel();
            fone.setLayout(new FlowLayout());
            fone.add(new JLabel("Telefone: "));
            JTextField txtFone = new JTextField(20);
            fone.add(txtFone);
            frame.add(fone);

            // Criação do botão salvar para adicionar o contato.
            JButton salvar = new JButton("Salvar");
            salvar.setLayout(new FlowLayout());
            salvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = txtNome.getText();
                    String email = txtEmail.getText();
                    String fone = txtFone.getText();
                    tableModel.addContato(nome,email,fone);
                    JOptionPane.showMessageDialog(null,"Salvo com sucesso.");
                }
            });
            frame.add(salvar);


            /* Tentativa de criar um botão fechar ou limpar campos.

            JButton fechar = new JButton("Fechar");
            fechar.setLayout(new FlowLayout());
            fechar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
            frame.add(fechar);
             */

            frame.setLayout(new FlowLayout());
            frame.setVisible(true);
            frame.setSize(350,250);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        add(bt, BorderLayout.SOUTH);
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
