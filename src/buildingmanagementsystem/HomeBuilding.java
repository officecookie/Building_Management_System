package buildingmanagementsystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeBuilding extends Building {

    // ===== свои поля =====
    private int apartments;
    private int residents;
    private boolean hasPlayground;

    public HomeBuilding(String name, String location, double height,
                        int apartments, int residents, boolean hasPlayground) {
        super("Жилое", height, name, location);
        this.apartments    = apartments;
        this.residents     = residents;
        this.hasPlayground = hasPlayground;
    }

    public int getApartments()       { return apartments; }
    public int getResidents()        { return residents; }
    public boolean isHasPlayground() { return hasPlayground; }

    public void setApartments(int a)       { this.apartments = a; }
    public void setResidents(int r)        { this.residents = r; }
    public void setHasPlayground(boolean p){ this.hasPlayground = p; }

    // ===== правка своих полей; остальное — в super =====
    @Override
    public boolean editField(String field, String value) {
        try {
            switch (field.toLowerCase().trim()) {
                case "квартиры": this.apartments = Integer.parseInt(value); return true;
                case "жильцы":   this.residents  = Integer.parseInt(value); return true;
                case "площадка": this.hasPlayground = Boolean.parseBoolean(value); return true;
                default:         return super.editField(field, value);
            }
        } catch (NumberFormatException e) { return false; }
    }

    // ============ МЕТОД 3. ПОКАЗАТЬ ВСЕ ЗДАНИЯ ============
    public static String showAll() {
        if (allBuildings.isEmpty()) return "Список зданий пуст.";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allBuildings.size(); i++) {
            sb.append(i + 1).append(". ")
              .append(allBuildings.get(i).getInfo())  // полиморфный вызов
              .append("\n\n");
        }
        return sb.toString();
    }

    // ============ МЕТОД 4. СОРТИРОВАТЬ ПО ВЫСОТЕ ============
    public static String sortByHeight() {
        if (allBuildings.isEmpty()) return "Список зданий пуст.";
        List<Building> sorted = new ArrayList<>(allBuildings);
        sorted.sort(Comparator.comparingDouble(Building::getHeight));

        StringBuilder sb = new StringBuilder("=== Здания по возрастанию высоты ===\n\n");
        for (int i = 0; i < sorted.size(); i++) {
            sb.append(i + 1).append(". ")
              .append(sorted.get(i).getInfo())
              .append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public String getInfo() {
        return super.getInfo()
             + "\n    Квартир: " + apartments
             + " | Жильцов: " + residents
             + " | Детская площадка: " + (hasPlayground ? "есть" : "нет");
    }
}