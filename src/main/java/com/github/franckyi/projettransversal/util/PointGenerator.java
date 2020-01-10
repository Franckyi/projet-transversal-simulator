package com.github.franckyi.projettransversal.util;

import com.github.franckyi.projettransversal.common.dao.DAOFactory;
import com.github.franckyi.projettransversal.common.model.Point;

import java.sql.SQLException;

public class PointGenerator {

    private static final double[][] DATA = {
            {45.774467801245976, 4.82128143310547},
            {45.77410860663812, 4.831581115722657},
            {45.77542564222295, 4.843597412109376},
            {45.77554537118828, 4.8595619201660165},
            {45.77458753226778, 4.872779846191407},
            {45.774348069967054, 4.888744354248048},
            {45.769199381796014, 4.8224830627441415},
            {45.76967834958357, 4.828319549560548},
            {45.768241433882004, 4.841022491455079},
            {45.76907963920648, 4.853725433349609},
            {45.76907963920648, 4.873981475830079},
            {45.76907963920648, 4.899730682373048},
            {45.76488848662397, 4.900074005126953},
            {45.764648982679404, 4.890460968017579},
            {45.764648982679404, 4.878787994384766},
            {45.765247740612885, 4.86248016357422},
            {45.765247740612885, 4.851837158203126},
            {45.765127989540304, 4.838790893554688},
            {45.75973892521535, 4.828147888183595},
            {45.758780814838076, 4.84050750732422},
            {45.75806222125844, 4.846515655517579},
            {45.75674477566032, 4.859046936035157},
            {45.759259872083206, 4.872264862060547},
            {45.759259872083206, 4.885139465332032},
            {45.75494820880817, 4.8993873596191415},
            {45.75602615586017, 4.887371063232423},
            {45.75602615586017, 4.877929687500001},
            {45.75566684248977, 4.86745834350586},
            {45.75566684248977, 4.858875274658203},
            {45.75614592646953, 4.833984375000001},
            {45.754109791149894, 4.8266029357910165},
            {45.75303180708059, 4.8442840576171875},
            {45.751594462598035, 4.858016967773438},
            {45.750995558138385, 4.872608184814454},
            {45.75039664725208, 4.8827362060546875},
            {45.75027686430362, 4.897499084472657},
            {45.743448811311815, 4.89543914794922},
            {45.74308941696574, 4.886169433593751},
            {45.74356860891304, 4.871063232421876},
            {45.74380820334431, 4.858016967773438},
            {45.74356860891304, 4.847717285156251},
            {45.74237062133277, 4.833812713623047},
            {45.73745860348957, 4.8293495178222665},
            {45.736739735363464, 4.841022491455079},
            {45.73661992310937, 4.861793518066407},
            {45.73650011059822, 4.877243041992188},
            {45.73578123013284, 4.890460968017579},
            {45.735901044186406, 4.90093231201172},
            {45.729790199691195, 4.8925209045410165},
            {45.729071232850465, 4.879646301269532},
            {45.72967037252707, 4.868144989013672},
            {45.729790199691195, 4.85424041748047},
            {45.729191061299936, 4.843082427978516},
            {45.731347929431244, 4.825572967529298},
            {45.72667460987643, 4.825401306152345},
            {45.72559609599041, 4.83999252319336},
            {45.7254762598288, 4.853553771972656},
            {45.72595560293273, 4.8650550842285165},
            {45.724996912611786, 4.871063232421876},
            {45.724996912611786, 4.88719940185547}
    };

    public static void main(String[] args) throws SQLException {
        if (args.length > 0) {
            if (args[0].equals("reset")) {
                System.out.println("Reset...");
                DAOFactory.getPointDAO().executeRawNoArgs("TRUNCATE TABLE points");
                System.out.println("OK\n-----");
            }
        }
        DAOFactory.getPointDAO().callBatchTasks(() -> {
            System.out.println("Generating points...");
            int n = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 6; j++) {
                    double[] coords = DATA[n++];
                    DAOFactory.getPointDAO().create(new Point(i, j, coords[0], coords[1]));
                    System.out.println(String.format("Added %d,%d (%f,%f)", i, j, coords[0], coords[1]));
                }
            }
            System.out.println("Done!");
            return null;
        });
    }

}
