package ru.rutmiit.basketball.models.enums;

public enum Position {
    PointGuard("PG","Разыгрывающий защитник"),
    ShootingGuard("SG","Атакующий защитник"),
    SmallForward("SF","Лёгкий форвард"),
    PowerForward("PF", "Тяжёлый форвард"),
    Center("C", "Центровой");

    private final String abbreviation;
    private final String title;

    Position(String abbreviation,String title) {
        this.abbreviation = abbreviation;
        this.title = title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getTitle() {
        return title;
    }
}
