package edu.mum.request;

public interface Subject {
    public void attach(Observer observer);
    public void detach(Observer observer);
    public void notifyObservers(String msg);
}
