package javacadapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Controle {

    private Usuario usu;
    private PessoaFisica pf;
    private Connection conexao;
    private Statement stmt;

    public Controle(PessoaFisica pf) {
        this.pf = pf;
        this.conexao = Conexao.Conectar();
    }

    public Controle(Usuario usu) {
        this.usu = usu;
        this.conexao = Conexao.Conectar();
    }

    private int GerarCodigoInclusao() {

        try {
            int codigo = 0;
            Statement stmt = this.conexao.createStatement();
            String sql = "SELECT MAX(CODIGO) + 1 FROM PESSOA";
            ResultSet resultado = stmt.executeQuery(sql);
            if (resultado.getInt(1) > 0) {
                codigo = resultado.getInt(1);
            } else {
                codigo = 1;
            }

            stmt.close();
            return codigo;
        } catch (Exception e) {
            return -1;
        }
    }

    private int IncluirPessoa(int codigo, String nome, String email) {
        try {
            String sql = "INSERT INTO PESSOA VALUES(?, ?, ?)";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    private int EditarPessoa(int codigo, String nome, String email) {
        try {
            String sql = "UPDATE PESSOA SET NOME = ?, EMAIL = ? WHERE CODIGO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(3, codigo);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    private int ExcluirPessoa(int codigo) {
        try {
            String sql = "DELETE FROM PESSOA WHERE CODIGO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    private int IncluirPessoaFisica() {

        try {
            String sql = "INSERT INTO PESSOAFISICA VALUES (?,?,?,?,?)";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, this.pf.getCodigo());
            stmt.setInt(2, this.pf.getCodigo());
            stmt.setFloat(3, this.pf.getRenda());
            stmt.setDate(4, this.pf.getDataNasc());
            stmt.setString(5, this.pf.getSexo());
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    private int EditarPessoaFisica() {

        try {
            String sql = "UPDATE PESSOAFISICA SET RENDA = ?, DATANASC = ?, SEXO = ? WHERE CODIGO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setFloat(1, this.pf.getRenda());
            stmt.setDate(2, this.pf.getDataNasc());
            stmt.setString(3, this.pf.getSexo());
            stmt.setInt(4, this.pf.getCodigo());
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    private int ExcluirPessoaFisica() {

        try {
            String sql = "DELETE FROM PESSOAFISICA WHERE CODIGO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, this.pf.getCodigo());
            stmt.execute();
            stmt.close();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int Incluir_PessoaFisica() {

        try {
            int codigo = this.GerarCodigoInclusao();
            this.pf.setCodigo(codigo);
            if (codigo > 0) {
                if (this.IncluirPessoa(this.pf.getCodigo(), this.pf.getNome(), this.pf.getEmail()) == 1) {
                    if (this.IncluirPessoaFisica() == 1) {
                        this.conexao.commit();
                        this.conexao.close();
                        return 1;
                    } else {
                        this.conexao.rollback();
                        this.conexao.close();
                        return -1;
                    }
                } else {
                    this.conexao.rollback();
                    this.conexao.close();
                    return -1;
                }
            } else {
                this.conexao.close();
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    public int Editar_PessoaFisica() {

        try {
            if (this.EditarPessoa(this.pf.getCodigo(), this.pf.getNome(), this.pf.getEmail()) == 1) {
                if (this.EditarPessoaFisica() == 1) {
                    this.conexao.commit();
                    this.conexao.close();
                    return 1;
                } else {
                    this.conexao.rollback();
                    this.conexao.close();
                    return -1;
                }
            } else {
                this.conexao.rollback();
                this.conexao.close();
                return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }

    public int Excluir_PessoaFisica() {

        try {
            if (this.ExcluirPessoaFisica() == 1) {
                if (this.ExcluirPessoa(this.pf.getCodigo()) == 1) {
                    this.conexao.commit();
                    this.conexao.close();
                    return 1;
                } else {
                    this.conexao.rollback();
                    this.conexao.close();
                    return -1;
                }
            } else {
                this.conexao.rollback();
                this.conexao.close();
                return -1;
            }

        } catch (Exception e) {
            return -1;
        }
    }

    public PessoaFisica BuscarPessoaFisicaPorCodigo() {

        try {
            String sql = "SELECT * FROM PESSOA INNER JOIN PESSOAFISICA ON PESSOA.CODIGO = PESSOAFISICA.PESSOA_CODIGO WHERE PESSOA.CODIGO = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setInt(1, this.pf.getCodigo());
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                this.pf.setNome(res.getString(2));
                this.pf.setEmail(res.getString(3));
                this.pf.setRenda(res.getFloat(6));
                this.pf.setDataNasc(res.getDate(7));
                this.pf.setSexo(res.getString(8));
                return this.pf;
            }
            stmt.close();
            this.conexao.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PessoaFisica> BuscarPessoaFisicaPorNome() {

        try {
            String sql = "SELECT * FROM PESSOA INNER JOIN PESSOAFISICA ON PESSOA.CODIGO = PESSOAFISICA.PESSOA_CODIGO WHERE PESSOA.NOME LIKE ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, this.pf.getNome() + '%');
            ResultSet res = stmt.executeQuery();
            List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
            while (res.next()) {
                lista.add(new PessoaFisica(res.getInt(1), res.getString(2), res.getString(3), res.getFloat(6), res.getDate(7), res.getString(8)));
            }
            stmt.close();
            this.conexao.close();
            return lista;
        } catch (Exception e) {
            return null;
        }

    }

    public int Efetuar_Login() {

        try {
            String sql = "SELECT * FROM USUARIO WHERE USUARIO.LOGIN = ? AND USUARIO.SENHA = ?";
            PreparedStatement stmt = this.conexao.prepareStatement(sql);
            stmt.setString(1, this.usu.getLogin());
            stmt.setString(2, stringHexa(gerarHash(this.usu.getSenha())));
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                stmt.close();
                this.conexao.close();
                return 1;
            }
            stmt.close();
            this.conexao.close();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    //Código MD5 - Fonte: http://codare.net/2007/02/02/java-gerando-codigos-hash-md5-sha/
    private byte[] gerarHash(String frase) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}
