/*
 * Operator.java
 *
 * Copyright (C) 2008  Pei Wang
 *
 * This file is part of Open-NARS.
 *
 * Open-NARS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Open-NARS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */

package nars.operation;

import java.util.*;

import nars.language.*;
import nars.entity.Task;
import nars.main.Memory;

/**
 * An individual operator that can be execute by the system, though implemented
 * outside NARS.
 * <p>
 * This is the only file to modify when adding a new operator into NARS.
 */
public abstract class Operator extends Term {
    public Operator(String name) {
        super(name);
    }
    
    /**
     * Required method for every operation, specifying the operation
     * @param task The task with the arguments to be passed to the operator
     * @return The direct collectable results and feedback of the execution
     */
    public abstract ArrayList<Task> execute(Task task);

    /**
     * Execute an operation, then handle feedback
     * @param task The task to be executed
     */
    public void call(Task task) {
        ArrayList<Task> feedback = execute(task);
        showExecution((Statement) task.getContent());
        Memory.executedTask(task);
        if (feedback != null) {
            for (Task t : feedback) {
                Memory.inputTask(t);
            }
        }
    }

    /**
     * Register all built-in operators in the Memory
     * <p>
     * The only method to modify when adding a new operator into NARS.
     * An operator name should contain at least two characters after '^'.
     * @return A Map between Operator name and object
     */
    public static HashMap<String, Operator> setOperators() {
        HashMap<String, Operator> table = new HashMap<String, Operator>();
        table.put("^go-to", new GoTo("^go-to"));
        table.put("^pick", new Pick("^pick"));
        table.put("^open", new Open("^open"));
        table.put("^break", new Break("^break"));
        table.put("^drop", new Break("^drop"));
        table.put("^throw", new Break("^throw"));
        table.put("^strike", new Break("^strike"));
        return table;
    }
    
    /**
     * Display a message in the output stream to indicate the execution of an operation
     * @param operation The content of the operation to be executed
     */
    private void showExecution(Statement operation) {
        Term operator = operation.getPredicate();
        Term arguments = operation.getSubject();
        String argList = arguments.toString().substring(3);         // skip the product prefix "(*,"
        System.out.println("EXECUTE: " + operator + "(" + argList);
    }
}

