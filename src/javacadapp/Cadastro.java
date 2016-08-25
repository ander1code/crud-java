package javacadapp;

import java.sql.Date;
import java.util.List;

public class Cadastro {

    public static int Incluir_PessoaFisica(String nome, String email, float renda, Date dataNasc, String sexo) {

        PessoaFisica pf = new PessoaFisica();
        pf.setNome(nome);
        pf.setEmail(email);
        pf.setRenda(renda);
        pf.setDataNasc(dataNasc);
        pf.setSexo(sexo);
        Controle ctr = new Controle(pf);
        if (ctr.Incluir_PessoaFisica() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int Editar_PessoaFisica(int codigo, String nome, String email, float renda, Date dataNasc, String sexo) {

        PessoaFisica pf = new PessoaFisica();
        pf.setCodigo(codigo);
        pf.setNome(nome);
        pf.setEmail(email);
        pf.setRenda(renda);
        pf.setDataNasc(dataNasc);
        pf.setSexo(sexo);
        Controle ctr = new Controle(pf);
        if (ctr.Editar_PessoaFisica() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int Excluir_PessoaFisica(int codigo) {

        PessoaFisica pf = new PessoaFisica();
        pf.setCodigo(codigo);
        Controle ctr = new Controle(pf);
        if (ctr.Excluir_PessoaFisica() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static PessoaFisica Buscar_PessoaFisicaPorCodigo(int codigo) {

        PessoaFisica pf = new PessoaFisica();
        pf.setCodigo(codigo);
        Controle ctr = new Controle(pf);
        return ctr.BuscarPessoaFisicaPorCodigo();
    }

    public static List<PessoaFisica> Buscar_PessoaFisicaPorNome(String nome) {

        PessoaFisica pf = new PessoaFisica();
        pf.setNome(nome);
        Controle ctr = new Controle(pf);
        return ctr.BuscarPessoaFisicaPorNome();
    }
    
    public static int Efetuar_Login(String login, String senha) {

        Usuario usu = new Usuario();
        usu.setLogin(login);
        usu.setSenha(senha);
        Controle ctr = new Controle(usu);
        return ctr.Efetuar_Login();
    }
}
