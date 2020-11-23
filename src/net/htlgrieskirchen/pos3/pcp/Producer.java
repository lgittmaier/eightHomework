/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.pcp;

import java.util.LinkedList;
import java.util.List;

public class Producer /* implement this */ {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> sent;
    private final int numberOfItems;
    
    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
       this.name = name;
       this.storage = storage;
       this.sleepTime = sleepTime;
       this.numberOfItems = numberOfItems;
       this.sent = new LinkedList<>();
    }
 
    public void run(){
        for(int i =0; i <= numberOfItems; i++){
            if(!storage.isProductionComplete()){
                sent.add(i);
            }else{
                getSleepTime();
                sent.add(i);
            }
            if(storage.isProductionComplete()){
                storage.isProductionComplete();
            }

        }

    }



    public List<Integer> getSent() {
        return sent;
    }

    public String getName() {
        return name;
    }

    public Storage getStorage() {
        return storage;
    }

    public int getSleepTime() {
        return sleepTime;
    }
}
