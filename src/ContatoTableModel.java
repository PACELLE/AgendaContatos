import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContatoTableModel extends AbstractTableModel implements TableModelListener {
    ContatoDAO dao;
    List<Contato> contatos;
    List<String> colunas;
    public ContatoTableModel (ContatoDAO dao){
        this.dao = dao;
        contatos = dao.retrive();
        colunas = List.of("nome","email","telefone");
        this.addTableModelListener(this);
    }
    @Override
    public int getRowCount() {
        return contatos.size();
    }
    @Override
    public int getColumnCount() {
        return colunas.size();
    }
    @Override
    public String getColumnName(int column) {
        return colunas.get(column);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contato contato = contatos.get(rowIndex);
        switch (columnIndex){
            case 0: return contato.getNome();
            case 1: return contato.getEmail();
            case 2: return contato.getTelefone();
        }
        return null;
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Contato contato = contatos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                contato.setNome((String) aValue);
                break;
            case 1:
                contato.setEmail((String) aValue);
                break;
            case 2:
                contato.setTelefone((String) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex,columnIndex);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int i=e.getFirstRow();
        Contato contato = contatos.get(i);
        dao.atualizar(contato);
    }

    public void addContato(String nome, String email, String fone) {
        Contato contato = new Contato(nome,email,fone);
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelefone(fone);
        contatos.add(contato);
        dao.add(contato);
        fireTableDataChanged();
    }
}
