## Отчет по HW2
a) Основная бизнесс логика прописана в сервисах:
1. AnimalRegistrationService:
- createAnimal - создание животного
- deleteAnimal - удаление животного
2. AnimalTransferService:
- placed - перемещение животного (не важно из какого то вольера или первое поселение) в вольер
- moveOut - выселение животного из вольера
3. AnimalTreatService:
- treatById - лечение животного (даже если животное здорово его еще раз вылечат)
4. EnclosureCleaningService:
- cleanEnclosureById - чистка вольера
5. EnclosureRegistrationService:
- createEnclosure - создание вольера
- deleteEnclosure - удаление вольера
- getAnimals - возвращает список животных проживающих в вольере с заданным id
6. FeedingOrganizationService:
- makeNewTime - создание нового расписания кормления
- feedAll - выполнить все активные расписания кормления
- doSkip - пропустить расписание кормления по его id на 1 кормление
- doAllSkip - пропустить все расписания кормления на 1 кормление
- undoAllSkip - вернуть все пропущенные расписания кормления
- undoSkip - вернуть пропущенное кормление в расписание по id
- deleteById - удалить расписание кормления по id
- doAllSkipForAnimal - пропустить все кормление для конкретного животного
- undoAllSkipForAnimal - вернуть все пропущенное кормление для конкретного животного
7. ZooStatisticsService
- getAnimalFeedingSchedule - возвращает расписание кормления для конкретного животного
- getCntOfAllAnimal - возвращает количество животных в зоопарке
- getCntOfAllEnclosure - возвращает количество вольеров в зоопарке
- getCntOfAllFeedingSchedule - возвращает количество расписаний кормления в зоопарке
- getAllAnimal - возвращает список всех животных в зоопарке
- getAnimal - возвращает животное по id
- getAllEnclosure - возвращает список всех вольеров в зоопарке
- getEnclosure - возвращает вольер по id
- getAllFeedingSchedule - возвращает список всех расписаний кормлений в зоопарке
- getFeedingSchedule - возвращает расписание кормления по id
- findEnclosureByAnimalId - возвращает вольер в котором находится заданное животное
- getHistoryOfFeed - возвращает список из событий кормления в зоопарке
- getHistoryOfZooEvent - возвращает список событий (уборка, лечение) в зоопарке
- getFilterEnclosure - возвращает список клеток предназначенных для определенного вида в которых осталось хотябы minCnt мест

б) 
1. Чистая архитектура (Clean Architecture):
- Есть четыре слоя: Domain, Application, Infrastructure, Presentation
- Все классы Domain не зависят от Application, Infrastructure, Presentation слоев
- Все классы Application не зависят от Infrastructure, Presentation слоев
- Все классы Infrastructure не зависят от Presentation слоя
2. Domain-Driven Design:
- Все сущьности в Domain имеют либо числовые поля, логические, либо value object
- Базовая бизнес логика находится в самих сущьностях. Например, в классе Animal есть методы treat(), feed(), 
в классе Enclosure есть методы delAnimal(), pushAnimal(), clean(), isAcceptable(), в классе FeedingSchedule 
есть методы doSkip(), undoSkip(), make()
- Есть Events доменные события AnimalMovedEvent, AnimalTreatEvent, CleaningEvent, FeedingTimeEvent

в) Покрытие тестами 72%
