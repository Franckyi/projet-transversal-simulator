package com.github.franckyi.projettransversal.simulator;

import com.github.franckyi.projettransversal.common.ConnectionHandler;
import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Feu;
import com.github.franckyi.projettransversal.common.model.Point;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

public class Simulator {

    private static final Random RANDOM = new Random();

    // intervalle de temps entre 2 feux (secondes)
    private static final int MIN_INTERVAL = 30;
    private static final int MAX_INTERVAL = 60;

    private static final int MAX_FIRES = 100;

    public static void main(String[] args) throws InterruptedException, SQLException, ClassNotFoundException {
        ConnectionHandler.init(ConnectionHandler.Database.SIMULATION);
        if (args.length > 0) {
            if (args[0].equals("reset")) {
                System.out.println("Reset...");
                DAOFactory.getFeuDAO().executeRawNoArgs("TRUNCATE TABLE feux");
                System.out.println("OK\n-----");
            }
        }
        while (true) {
            if (DAOFactory.getFeuDAO().countOf() < MAX_FIRES) {
                generateFire();
            } else {
                System.out.println(String.format("Limite de feux atteinte (%d)", MAX_FIRES));
            }
            int sleep = RANDOM.nextInt((MAX_INTERVAL - MIN_INTERVAL) * 1000) + MIN_INTERVAL * 1000;
            System.out.println(String.format("----- Attente pendant %d secondes", sleep / 1000));
            Thread.sleep(sleep);
        }
    }

    public static void generateFire() throws SQLException {
        System.out.println("Génération nouveau feu");
        int idPoint = 0;
        boolean valid = false;
        while (!valid) {
            idPoint = RANDOM.nextInt(60) + 1;
            valid = DAOFactory.getFeuDAO().isPointValid(idPoint);
        }
        Point point = DAOFactory.getPointDAO().queryForId(idPoint);
        Feu feu = new Feu(point, RANDOM.nextInt(9) + 1, Timestamp.from(Instant.now()));
        System.out.println(String.format("idPoint=%d; intensite=%d; date=%d", idPoint, feu.getIntensite(), feu.getDate().getTime()));
        DAOFactory.getFeuDAO().create(feu);
        System.out.println("OK");
    }
}
