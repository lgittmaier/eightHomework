/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.concurrent.ArrayBlockingQueue;

public class Storage { 
    private final ArrayBlockingQueue<Integer> queue;
    
    private int fetchedCounter;
    private int storedCounter;
    private int underflowCounter;
    private int overflowCounter;
    private boolean productionComplete;
    
    public Storage(ArrayBlockingQueue<Integer> queue, int fetchedCounter, int storedCounter, int underflowCounter, int overflowCounter, boolean productionComplete) {
        this.queue = queue;
        this.fetchedCounter = fetchedCounter;
        this.storedCounter = storedCounter;
        this.underflowCounter = underflowCounter;
        this.overflowCounter = overflowCounter;
        this.productionComplete = productionComplete;
    }
    
    public synchronized boolean put(Integer data) throws InterruptedException {
        boolean erg = false;
        if(queue.remainingCapacity() > 0){
            storedCounter++;
            erg = true;
        }else{
            overflowCounter++;
        }
        return erg;
    }
 
    public synchronized Integer get() {
        Integer erg;
        if(!queue.stream().findFirst().equals(null)){
            fetchedCounter++;
            erg=  fetchedCounter;
        }else{
            underflowCounter++;
            erg = null;
        }
        return erg;
    }

    public boolean isProductionComplete() {
        return productionComplete;
    }

    public void setProductionComplete() {
        this.productionComplete = true;
    }

    public int getFetchedCounter() {
        return this.fetchedCounter;
    }

    public int getStoredCounter() {
        return this.storedCounter;
    }

    public int getUnderflowCounter() {
        return this.underflowCounter;
    }

    public int getOverflowCounter() {
        return this.overflowCounter;
    }
}
