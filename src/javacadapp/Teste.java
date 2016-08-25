/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacadapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Computador
 */
public class Teste {

    public static void main(String[] args) {
         /*
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.set(1981, 11-1, 12);
            java.sql.Date dataNasc = new Date(calendario.getTime().getTime());
            Cadastro.Excluir_PessoaFisica(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
       
        PessoaFisica pf = Cadastro.Buscar_PessoaFisicaPorCodigo(1);
        System.out.println(pf.getNome());
        System.out.println(pf.getDataNasc().toLocalDate());
        

        List<PessoaFisica> lista = Cadastro.Buscar_PessoaFisicaPorNome("");
        for(PessoaFisica pf : lista)
        {
            System.out.println(pf.getNome() + " --- " + pf.getDataNasc().toLocalDate() + " - " + pf.getEmail() + " - " + pf.getRenda());
        }
        */
         
         System.out.println(Cadastro.Efetuar_Login("Anderson", "121181"));
         
        
        
    }
}
