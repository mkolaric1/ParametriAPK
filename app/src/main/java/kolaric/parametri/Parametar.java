package kolaric.parametri;

import java.io.Serializable;

public class Parametar implements Serializable {

    private int sifra;
    private String ime;
    private String grad;

    public Parametar(int sifra, String ime, String grad) {
        this.sifra = sifra;
        this.ime = ime;
        this.grad = grad;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }
}