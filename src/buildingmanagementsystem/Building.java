package buildingmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Building {
    protected String category;
    protected double height;
    protected String name;
    protected String location;

    // Общий список, к которому обращаются все три класса
    static final List<Building> allBuildings = new ArrayList<>();

    public Building(String category, double height, String name, String location) {
        this.category = category;
        this.height   = height;
        this.name     = name;
        this.location = location;
    }

    // геттеры / сеттеры
    public String getCategory() { return category; }
    public double getHeight()   { return height; }
    public String getName()     { return name; }
    public String getLocation() { return location; }

    public void setCategory(String c) { this.category = c; }
    public void setHeight(double h)   { this.height = h; }
    public void setName(String n)     { this.name = n; }
    public void setLocation(String l) { this.location = l; }

    // ============ МЕТОД 1. ДОБАВИТЬ ЗДАНИЕ ============
    public static boolean addBuilding(Building b) {
        if (b == null) return false;
        return allBuildings.add(b);
    }

    // ============ МЕТОД 2. РЕДАКТИРОВАТЬ ПОЛЕ ============
    /** Общая точка входа — из GUI вызывается только этот метод */
    public static boolean editField(int index, String field, String value) {
        if (index < 0 || index >= allBuildings.size()) return false;
        return allBuildings.get(index).editField(field, value); // полиморфизм
    }

    /** Переопределяется в наследниках; базовый правит общие поля */
    public boolean editField(String field, String value) {
        try {
            switch (field.toLowerCase().trim()) {
                case "название":       this.name = value;     return true;
                case "местоположение": this.location = value; return true;
                case "высота":         this.height = Double.parseDouble(value); return true;
                case "категория":      this.category = value; return true;
                default: return false;
            }
        } catch (NumberFormatException e) { return false; }
    }

    // ===== общая часть для вывода =====
    public String getInfo() {
        return "[" + category + "] " + name
             + " | высота: " + height + " м"
             + " | адрес: " + location;
    }

    public String getShortInfo() {
        return name + " (" + category + ", " + height + " м)";
    }
    
    
    
        // ============================================================
    // ПРЕДЗАПОЛНЕНИЕ: 5 зданий при старте программы
    // Выполняется один раз при загрузке класса Building
    // ============================================================
    static {
        // ---- 3 жилых ----
        addBuilding(new HomeBuilding(
                "ЖК Солнечный", "ул. Ленина, 15", 45.5,
                120, 340, true));

        addBuilding(new HomeBuilding(
                "Дом на Мира", "пр. Мира, 8", 32.0,
                80, 210, false));

        addBuilding(new HomeBuilding(
                "ЖК Небесный", "ул. Космонавтов, 42", 78.3,
                250, 620, true));

        // ---- 2 офисных ----
        addBuilding(new OfficeBuilding(
                "БЦ Москва-Сити", "Пресненская наб., 8", 374.0,
                350, true, 12));

        addBuilding(new OfficeBuilding(
                "Бизнес-центр Гарант", "ул. Гагарина, 3", 56.7,
                95, false, 4));
    }
}