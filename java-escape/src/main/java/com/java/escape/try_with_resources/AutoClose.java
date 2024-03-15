package com.java.escape.try_with_resources;

public class AutoClose implements AutoCloseable {

    @Override
    public void close() {
        System.out.println(">>> close()");
        throw new RuntimeException("Exception in close()");
    }

    public void work() {
        System.out.println(">>> work()");
        throw new RuntimeException("Exception in work()");
    }

    public static void main(String[] args) throws MyException {
        // AutoClose autoClose = new AutoClose();
        // try {
        //     autoClose.work();
        // } finally {
        //     autoClose.close();
        // }

        try (AutoClose autoClose = new AutoClose()){
            autoClose.work();
        }
    }
}
