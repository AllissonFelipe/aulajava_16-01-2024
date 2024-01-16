import java.sql.*;
import java.util.*;

public class InsertMySQL {
    public static void main(String[] args) {
        Scanner scnLogin = new Scanner(System.in);
        Scanner scnNome = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        String status = "Nada aconteceu ainda";
        try {
            System.out.println("Bem vindo ao cadastro de login.");
            System.out.println("Digite o login que será cadastrado: ");
            String strLogin = scnLogin.nextLine();
            System.out.println("Digite o nome que será cadastrado: ");
            String strNome = scnNome.nextLine();
            System.out.println("Digite a senha que será cadastrado: ");
            String strSenha = scnSenha.nextLine();
            String strInsertMySQL = "insert into `mysql_connector`.`tbl_login` (`login`,`nome`,`senha`) values ('" + strLogin + "','" + strNome + "','" + strSenha + "');";
            Connection conn = App.conectar();
            Statement stmSql = conn.createStatement();
            stmSql.addBatch(strInsertMySQL);
            stmSql.executeBatch();
            stmSql.close();
            status = "Ok! Login inserido com sucesso";
        } catch (Exception e) {
            System.err.println("Ops! Ocorreu o erro " + e);
        }
        scnLogin.close();
        scnNome.close();
        scnSenha.close();
        System.out.println(status);
    }
}
