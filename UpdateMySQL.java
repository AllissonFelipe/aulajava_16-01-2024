import java.sql.*;
import java.util.*;

public class UpdateMySQL {
    public static void main(String[] args) {
        Scanner scnLogin = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        String strLogin, strSenha, status = "Nada aconteceu ainda...";
        
        try {
        Connection conn = App.conectar();
        System.out.println("Digite seu login: ");
        strLogin = scnLogin.nextLine();
        System.out.println("Digite sua senha: ");
        strSenha = scnSenha.nextLine();
        String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strLogin + "' and `senha` = '" + strSenha + "';";
        Statement stmSql = conn.createStatement();
        ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
        String nome = "";
        while (rsSql.next()) {
                nome += "[" + rsSql.getString("nome") + "] ";
                
        }
        status = nome;
        stmSql.close();
        rsSql.close();
    } catch (Exception e){
            System.out.println("Ops! Ocorreu o erro " + e);
        }
    System.out.println(status);
        if (status == "") {
        System.out.println("Login Invalido");
        } 
    }
}
