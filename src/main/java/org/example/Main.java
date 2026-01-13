package org.example;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Contact> listContacts = List.of(
                new Contact("Иван", "+7-900-111-22-33", "ivan.petrov@mail.com", "Друзья"),
                new Contact("Анна", "+7-900-222-33-44", "anna.smirnova@mail.com", "Работа"),
                new Contact("Сергей", "+7-900-333-44-55", "sergey.ivanov@mail.com", "Семья"),
                new Contact("Мария", "+7-900-444-55-66", "maria.k@mail.com", "Работа"),
                new Contact("Дмитрий", "+7-900-555-66-77", "d.sokolov@mail.com", "Друзья"),
                new Contact("Ольга", "+7-900-666-77-88", "olga.m@mail.com", "Семья"),
                new Contact("Алексей", "+7-900-777-88-99", "alex.volkov@mail.com", "Работа"),
                new Contact("Екатерина", "+7-900-888-99-00", "katya.p@mail.com", "Друзья"),
                new Contact("Николай", "+7-901-111-22-33", "nfedorov@mail.com", "Семья"),
                new Contact("Татьяна", "+7-901-222-33-44", "tb@mail.com", "Работа")
        );

        Set<Contact> contacts = new HashSet<>(listContacts);
        Map<String, List<Contact>> contactGroup = new HashMap<>();
        Iterator<Contact> iterator = contacts.iterator();
        Scanner scanner = new Scanner(System.in);

//        contactGroup = contacts.stream().collect(Collectors.groupingBy(
//                Contact::getGroup,
//                Collectors.toList()
//        ));
        for (Contact contact : contacts) {
            contactGroup
                    .computeIfAbsent(contact.getGroup(), key -> new ArrayList<>())
                    .add(contact);
        }


        for (Map.Entry<String, List<Contact>> n : contactGroup.entrySet()) {
            if (n.getKey().equals("Семья")) System.out.println(n);
        }

        while (true) {
            System.out.println("Выберите действие: \n" +
                    "1 - Добавить контакт\n" +
                    "2 - Удалить контакт\n" +
                    "3 - Посмотреть все контакты\n" +
                    "4 - Найти контакт по имени\n" +
                    "5 - Посмотреть все контакты по группе\n" +
                    "0 - Exit"
                    );

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case (1):
                    System.out.print("Введите имя: ");
                    String enteredName = scanner.nextLine();

                    System.out.print("Введите номер: ");
                    String enteredPhone = scanner.nextLine();

                    System.out.print("Введите email: ");
                    String enteredEmail = scanner.nextLine();

                    System.out.print("Введите группу: ");
                    String enteredGroup = scanner.nextLine();

                    Contact contact = new Contact(enteredName, enteredPhone, enteredEmail, enteredGroup);

                    if (!contacts.contains(contact)) {
                        contacts.add(contact);
                        System.out.println("Новый контакт: " + contact + " - был добавлен");
                    } else {
                        System.out.println("Такой контакт уже есть в списке");
                        break;
                    }

                    if (!contactGroup.containsKey(contact.getGroup())) {
                        contactGroup.put(contact.getGroup(), new ArrayList<>());
                    }

                    contactGroup.get(contact.getGroup()).add(contact);
                    break;

                case (2):
                    boolean found = false;
                    contacts.forEach(System.out::println);
                    System.out.println("Введите id контакта для удаления: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    while (iterator.hasNext()) {
                        Contact iteratorContact = iterator.next();
                        if (iteratorContact.getId() == (long) id) {
                            found = true;
                            iterator.remove();
                            contactGroup.remove(iteratorContact.getGroup());
                            System.out.println("Контакт " + iteratorContact + " - был удален");
                            break;
                        }
                    }

                    if (id > contacts.size()) {
                        System.out.println("Контакта с таким id нет");
                        break;
                    } else if (!found) {
                        System.out.println("Неизвестная команда");
                        break;
                    }
                    break;
                case (3):
                    if (contacts.isEmpty()) {
                        System.out.println("Список контактов пуст!");
                        break;
                    }
                    System.out.println("Все контакты: ");
                    contacts.forEach(System.out::println);
                    break;

                case (4):
                    found = false;
                    System.out.println("Введите имя контакта: ");

                    while (iterator.hasNext()) {
                        Contact hasName = iterator.next();
                        if (hasName.getName().equals(scanner.nextLine())) {
                            found = true;
                            iterator.remove();
                            contactGroup.remove(hasName.getGroup());
                            System.out.println("Контакт с именем: " + hasName.getName() + " - был удален");
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Такого контакта нет в списке");
                    }
                    break;

                case (5):
                    System.out.print("Введите группу: >");
                    String group = scanner.nextLine();
                    if (group.isEmpty()) {
                        System.out.println("Поле не должно быть пустым!");
                        break;
                    }

                    if (contactGroup.containsKey(group)) {
                        for (Map.Entry<String, List<Contact>> c : contactGroup.entrySet()) {
                            if (c.getKey().equals(group)) {
                                c.getValue().forEach(System.out::println);
                            }
                        }
                    } else {
                        System.out.println("Нету такой группы");
                    }

                    break;

                case (0):
                    System.out.println("Выход...");
                    return;

                default:
                    System.out.println("Неизвестная команда!");
            }
        }
    }

}