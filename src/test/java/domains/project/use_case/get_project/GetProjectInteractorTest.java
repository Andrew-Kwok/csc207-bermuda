package domains.project.use_case.get_project;

import data_access.in_memory.*;
import domains.project.use_case.get_project.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
class GetProjectInteractorTest {
   public static GetProjectSqlDataAccessInterface sqldao;
   public static GetProjectApiDataAccessInterface apidao;

   @BeforeAll
    public static void initiateDAOs() {
       InMemorySQLDataAccessObject sqldao = new InMemorySQLDataAccessObject();
       InMemoryAPIDataAccessObject apidao = new InMemoryAPIDataAccessObject();
      sqldao.permissions.add(List.of("1", "1", "1", "owner", ""));
      sqldao.permissions.add(List.of("2", "2", "2", "owner", ""));
      sqldao.permissions.add(List.of("3", "3", "3", "owner", ""));
      sqldao.permissions.add(List.of("4", "4", "4", "owner", ""));
      sqldao.permissions.add(List.of("5", "5", "5", "owner", ""));
      sqldao.permissions.add(List.of("6", "2", "1", "editor", ""));
      sqldao.users.add(List.of("1", "test1", "pass1", "0"));
      sqldao.users.add(List.of("2", "test2", "pass2", "0"));
      sqldao.users.add(List.of("3", "test3", "pass3", "0"));
      sqldao.users.add(List.of("4", "test4", "pass4", "0"));
      sqldao.users.add(List.of("5", "test5", "pass5", "0"));
      sqldao = sqldao;
   }
}