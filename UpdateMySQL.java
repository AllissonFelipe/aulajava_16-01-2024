import java.sql.*;
import java.util.*;

public class UpdateMySQL {
    public static void main(String[] args) {
        Scanner scnLogin = new Scanner(System.in);
        Scanner scnSenha = new Scanner(System.in);
        Scanner scnResp = new Scanner(System.in);
        Scanner scnCadastroUpdate = new Scanner(System.in);
        String strLogin, strSenha, status = "Nada aconteceu ainda...";
        int resp;
        boolean update = false, validLogin = false;
        String novoNome, novaSenha;
        
        try {
            while (validLogin == false) {
                Connection conn = App.conectar();
                System.out.println("\nDigite seu login: ");
                strLogin = scnLogin.nextLine();
                System.out.println("Digite sua senha: ");
                strSenha = scnSenha.nextLine();
                String strSqlSelect = "select * from `mysql_connector`.`tbl_login` where `login` = '" + strLogin + "' and `senha` = '" + strSenha + "';";
                Statement stmSql = conn.createStatement();
                ResultSet rsSql = stmSql.executeQuery(strSqlSelect);
                String login = "";
                String senha = "";
                    while (rsSql.next()) {
                        login = "[" + rsSql.getString("login") + "] ";
                        senha = "[" + rsSql.getString("senha") + "] ";
                    }
                    if (login == "" || senha == "") {
                        status = "\nLogin Invalido! Tente Novamente.";
                        System.out.println(status);
                    } else {
                        status = "Login usado: " + login + "\nSenha usada: " + senha;
                        System.out.println("\nBem vindo " + login);
                        validLogin = true;
                        while (update == false) {
                            System.out.println("\n--- Update de cadastro ---");
                            System.out.println("Digite [1] para alterar o nome.\nDigite [2] para alterar a senha.\nDigite [3] para sair.");
                            resp = scnResp.nextInt();
                            if (resp == 1) {
                                System.out.println("Digite o novo nome: ");
                                novoNome = scnCadastroUpdate.nextLine();
                                String stmSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `nome` = '" + novoNome + "' WHERE (`login` = '" + strLogin + "')";
                                PreparedStatement preparedStm = conn.prepareStatement(stmSqlUpdate);
                                preparedStm.executeUpdate();
                                System.out.println("\nNome alterado com sucesso para " + "[" + novoNome + "]");
                            } else if (resp == 2) {
                                System.out.println("Digite a nova senha: ");
                                novaSenha = scnCadastroUpdate.nextLine();
                                String stmSqlUpdate = "UPDATE `mysql_connector`.`tbl_login` SET `senha` = '" + novaSenha + "' WHERE (`login` = '" + strLogin + "')";
                                PreparedStatement preparedStm = conn.prepareStatement(stmSqlUpdate);
                                preparedStm.executeUpdate();
                                System.out.println("\nSenha alterada com sucesso para " + "[" + novaSenha + "]");
                            } else if (resp == 3) {
                                status = "\nVolte Novamente! " + login;
                                update = true;
                            } else if (resp != 1 || resp != 2 || resp != 3) {
                                System.out.println("Ocorreu um erro! Tente Novamente.");
                            }
                        }
                    }
                    stmSql.close();
                    rsSql.close();
                }
                scnLogin.close();
                scnSenha.close();
                scnResp.close();
                scnCadastroUpdate.close();    
        } catch (Exception e) {
            System.out.println("Ops! Ocorreu o erro " + e);
        }
        System.out.println(status);
    }
}
