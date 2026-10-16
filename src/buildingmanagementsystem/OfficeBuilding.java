package buildingmanagementsystem;

import java.util.Comparator;

public class OfficeBuilding extends Building {

    // ===== свои поля =====
    private int offices;
    private boolean hasParking;
    private int stateEnterprises;

    public OfficeBuilding(String name, String location, double height,
                          int offices, boolean hasParking, int stateEnterprises) {
        super("Офисное", height, name, location);
        this.offices          = offices;
        this.hasParking       = hasParking;
        this.stateEnterprises = stateEnterprises;
    }

    public int getOffices()          { return offices; }
    public boolean isHasParking()    { return hasParking; }
    public int getStateEnterprises() { return stateEnterprises; }

    public void setOffices(int o)          { this.offices = o; }
    public void setHasParking(boolean p)   { this.hasParking = p; }
    public void setStateEnterprises(int s) { this.stateEnterprises = s; }

    @Override
    public boolean editField(String field, String value) {
        try {
            switch (field.toLowerCase().trim()) {
                case "офисы":       this.offices = Integer.parseInt(value); return true;
                case "паркинг":     this.hasParking = Boolean.parseBoolean(value); return true;
                case "предприятия": this.stateEnterprises = Integer.parseInt(value); return true;
                default:            return super.editField(field, value);
            }
        } catch (NumberFormatException e) { return false; }
    }

    // ============ МЕТОД 5. ЗДАНИЯ ВЫШЕ 50 М ============
    public static String showAbove50() {
        StringBuilder sb = new StringBuilder("=== Здания выше 50 м ===\n\n");
        boolean found = false;
        for (Building b : allBuildings) {
            if (b.getHeight() > 50) {
                sb.append(b.getInfo()).append("\n\n");
                found = true;
            }
        }
        return found ? sb.toString() : "Нет зданий выше 50 метров.";
    }

    // ============ МЕТОД 6. САМОЕ ВЫСОКОЕ ЗДАНИЕ ============
    public static Building getTallest() {
        return allBuildings.stream()
                .max(Comparator.comparingDouble(Building::getHeight))
                .orElse(null);
    }

    @Override
    public String getInfo() {
        return super.getInfo()
             + "\n    Офисов: " + offices
             + " | Паркинг: " + (hasParking ? "есть" : "нет")
             + " | Гос. предприятий: " + stateEnterprises;
    }
}