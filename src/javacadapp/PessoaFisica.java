package javacadapp;

import java.sql.Date;

public class PessoaFisica implements IPessoa {

    private int codigo;
    private String nome;
    private String email;
    private float renda;
    private Date dataNasc;
    private String sexo;

    @Override
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    public float getRenda() {
        return renda;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public PessoaFisica(int codigo, String nome, String email, float renda, Date dataNasc, String sexo) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.renda = renda;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
    }
    
    public PessoaFisica() {
    }
}
