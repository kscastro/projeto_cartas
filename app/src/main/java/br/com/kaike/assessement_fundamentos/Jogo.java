package br.com.kaike.assessement_fundamentos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by kaike on 24/07/2017.
 */
public class Jogo {

    public List<Cartas> embaralhar() {
        ArrayList<Cartas> cartas = new ArrayList<>();
        cartas.add(Cartas.CORINGA);
        cartas.add(Cartas.AS);
        cartas.add(Cartas.CORINGA);
        Collections.shuffle(cartas, new Random((long) (Math.random() * 100000)));
        return cartas;
    }
}
