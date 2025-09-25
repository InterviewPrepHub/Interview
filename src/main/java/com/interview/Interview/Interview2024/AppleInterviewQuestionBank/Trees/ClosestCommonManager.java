package com.interview.Interview.Interview2024.AppleInterviewQuestionBank.Trees;

import java.util.ArrayList;
import java.util.List;

public class ClosestCommonManager {

    public static void main(String[] args) {

        Employee bill = new Employee("Bill");

        Employee dom = new Employee("Dom");
        Employee bob = new Employee("Bob");
        dom.addReports(new Employee("Peter"));
        dom.addReports(bob);
        dom.addReports(new Employee("Porter"));

        Employee samir = new Employee("Samir");

        Employee micheal = new Employee("Micheal");

        // Adding reports to Bill
        bill.addReports(dom);
        bill.addReports(samir);
        bill.addReports(micheal);

        // Adding reports to Peter
        Employee milton = new Employee("Milton");
        Employee nina = new Employee("Nina");
        dom.reports.get(0).addReports(milton);
        dom.reports.get(0).addReports(nina);


        ClosestCommonManager c = new ClosestCommonManager();
        Employee res = c.closestCommonManager(bill, bob, nina);
        System.out.println(res.name);
    }

    public Employee closestCommonManager(Employee ceo, Employee e1, Employee e2) {

        if (ceo==null || ceo==e1 || ceo==e2) {
            return ceo;
        }

        boolean judgeEmp1 = false;
        boolean judgeEmp2 = false;

        for (Employee emp : ceo.reports) {
            Employee result = closestCommonManager(emp, e1, e2);
            if (result == e1) {
                judgeEmp1 = true;
            }
            else if (result == e2) {
                judgeEmp2 = true;
            } else if (result != null) {
                return result;
            }
        }

        if (judgeEmp1 == true && judgeEmp2 == true) {
            return ceo;
        } else if (judgeEmp1 == true) {
            return e1;
        } else if (judgeEmp2 == true) {
            return e2;
        }
        return null;
    }

    public BTNode lca(BTNode curr, BTNode A, BTNode B) {

        if (curr == null) return null;

        if (curr == A || curr == B) return curr;

        BTNode left = lca(curr.left, A, B);
        BTNode right = lca(curr.right, A, B);

        if (left != null && right!= null) {
            return curr;
        }

        if (left == null) return right;
        else return left;
    }
}

class Employee {

    String name;
    List<Employee> reports;

    public Employee(String name) {
        this.name = name;
        this.reports = new ArrayList<>();
    }

    public void addReports(Employee emp) {
        this.reports.add(emp);
    }
}

