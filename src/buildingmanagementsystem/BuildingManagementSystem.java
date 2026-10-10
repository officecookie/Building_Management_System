
package buildingmanagementsystem;
import javax.swing.SwingUtilities;
/**
 *
 * @author first
 */
//если что это основная программа(класс/файл) который в старте вызывает Интерфейс.
public class BuildingManagementSystem {

    public static void main(String[] args) {
        
        //тут идет реализация только вывода интерфейса(3 строчки)
       SwingUtilities.invokeLater(() -> {
            mainJFrame form = new mainJFrame();
            form.setVisible(true);
            //далее все остальное, а именно интерфейс реализован в файле mainJFrame.java
        });
       //пока что я не реализовал 3(здании)класса, а только сделал интерфейс и его вывод...
    }
}
