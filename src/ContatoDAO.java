import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/agenda";
    public ContatoDAO() {
        try {
            conn = DriverManager.getConnection(url,"root", "root");
            System.out.println("conectado");
        } catch (SQLException excecao) {
            throw new RuntimeException(excecao);
        }
    }
    public void add(Contato Contato) {
        String sql = "INSERT INTO agenda(nome,email,telefone) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Contato.getNome());
            stmt.setString(2, Contato.getEmail());
            stmt.setString(3, Contato.getTelefone());
            stmt.execute();
            stmt.close();
            System.out.print("inserido");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public void atualizar(Contato c) {
        String sql = "update agenda set email=?, telefone=? where nome=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, c.getEmail());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getNome());
            stmt.execute();
            stmt.close();
            System.out.print("inserido");
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    public List<Contato> retrive(){
        List contatos = new ArrayList<Contato>();
        String sql = "select * from agenda";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome= rs.getString("nome");
                String email= rs.getString("email");
                String telefone= rs.getString("telefone");
                Contato contato = new Contato(nome,email,telefone);
                contatos.add(contato);
            }
            stmt.close();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return contatos;
    }
}

