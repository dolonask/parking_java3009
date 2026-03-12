import model.dto.EntryDto;
import model.enums.EntryStatus;
import service.EntryService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //DbHelper.INSTANCE.initDatabase();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Заезд автомобиля\n" +
                    "2. Выезд автомобиля\n" +
                    "3. Список автомобилей на парковке\n" +
                    "4. Выход\n");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            if (choice == 1){
                System.out.print("Номер автобомиля: ");
                String carNumber = scanner.next();
                EntryService.INSTANCE.in(carNumber);
            } else if (choice == 2){
                System.out.print("Номер автобомиля: ");
                String carNumber = scanner.next();
                EntryService.INSTANCE.out(carNumber);
            } else if (choice == 3){
                List<EntryDto> entryDtos = EntryService.INSTANCE.findEntryDtosInParking();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                for (int i = 0; i < entryDtos.size(); i++){
                    System.out.println((i+1)+". Автомобиль: " + entryDtos.get(i).getCarNumber() + ". Время заезда: " + dateTimeFormatter.format(entryDtos.get(i).getStartDate()));
                }
            } else if (choice == 4)
                break;


        }


    }
}