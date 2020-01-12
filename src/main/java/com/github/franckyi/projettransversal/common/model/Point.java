package com.github.franckyi.projettransversal.common.model;

import com.github.franckyi.projettransversal.common.dao.PointDAO;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "points", daoClass = PointDAO.class)
public class Point implements Pos {

    @DatabaseField(generatedId = true, columnName = "id_point")
    private int idPoint;

    @DatabaseField(columnName = "colonne")
    private int colonne;

    @DatabaseField(columnName = "ligne")
    private int ligne;

    @DatabaseField(columnName = "longitude")
    private double longitude;

    @DatabaseField(columnName = "latitude")
    private double latitude;

    public Point() {
    }

    public Point(int colonne, int ligne, double longitude, double latitude) {
        this(0, colonne, ligne, longitude, latitude);
    }

    public Point(int idPoint, int colonne, int ligne, double longitude, double latitude) {
        this.idPoint = idPoint;
        this.colonne = colonne;
        this.ligne = ligne;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getIdPoint() {
        return idPoint;
    }

    public void setIdPoint(int idPoint) {
        this.idPoint = idPoint;
    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
