package Task1;

class TransferThread extends Thread {
    private Bank bank;
    private int fromAccount;
    private int maxAmount;
    private int option;
    private static final int REPS = 1000;

    public TransferThread(Bank b, int from, int max, int option){
        bank = b;
        fromAccount = from;
        maxAmount = max;
        this.option = option;
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < REPS; i++) {
                int toAccount = (int)(bank.size() * Math.random());
                int amount = (int)(maxAmount * Math.random() / REPS);
                if (option == 1) bank.syncTransfer1(fromAccount, toAccount, amount);
                else if (option == 2) bank.syncTransfer2(fromAccount, toAccount, amount);
                else bank.syncTransfer3(fromAccount, toAccount, amount);
            }
        }
    }
}