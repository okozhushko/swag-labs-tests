# swag-labs-test — UI автотести для saucedemo.com

UI-автотести для https://www.saucedemo.com/ на **Selenide + TestNG** (не Playwright).
Репозиторій: github.com/okozhushko/swag-labs-tests

## Стек
- Java 20, Maven
- Selenide 7.1.0, TestNG 7.7.1, AssertJ 3.26.3, JavaFaker, WebDriverManager
- Звіти: TestNG/Surefire (`target/surefire-reports`), Allure не підключено

## Команди
- Усі тести: `mvn clean test`
- Один клас: `mvn test -Dtest=TestCheckUserLogin`
- Один метод: `mvn test -Dtest=TestCheckUserLogin#testLogout`
- Headless: `mvn test -Dselenide.headless=true` (у CI вмикається автоматично через змінну `CI`)
- Швидка перевірка компіляції: `mvn -q compile test-compile`
- Surefire підхоплює лише класи за маскою `**/Test*.java`, тому тестовий клас має починатися з `Test`.

## Структура
```
src/main/java/org/example/
  pages/        # Page Objects (LoginPage, CartPage, AllItemsPage, Checkout*Page, Product, SocialLinks)
  constants/    # PageConstants (тексти UI), AuthConfig, TimeOut, DefaultDuration, WaitForElement
  actions/      # допоміжні дії (IndexedElements.byPosition, ClearSelectedText)
  helpers/      # Faker (випадкові дані)
src/test/java/org/example/
  base/tests/   # BaseTest + тестові класи TestCheck*
  constants/    # TestConstants (очікувані дані для тестів)
src/main/resources/testng.xml
.github/workflows/ci.yml
```

## Як побудовані тести
- Тестовий клас наслідує `BaseTest`. Він у `@BeforeMethod` піднімає Chrome з чистим профілем, а в `@AfterMethod` закриває браузер.
- Назва тестового класу: `TestCheck<Область>`, метод: `test<Що>` (наприклад `testRemoveItemFromCart`).
- Тест читається як ланцюжок викликів Page Object, без прямих локаторів у тесті.
- Швидкий вхід у застосунок: `LoginPage.loginViaCookie()`. Повний UI-логін `LoginPage.login()` потрібен лише там, де тестується сам логін.

## Page Objects
- Статичний фабричний метод `init<Page>()` перевіряє, що сторінка відкрилась (заголовок видимий), і повертає екземпляр.
- Методи повертають `this` для тієї ж сторінки або наступний Page Object при переході (`clickCheckoutBtn()` повертає `CheckoutInfoPage`).
- Колекції елементів вибираються за 1-based позицією через `IndexedElements.byPosition(collection, position)`.
- Тексти для перевірок беруться з `PageConstants`, а не пишуться в методах.
- Локатори: стабільні `data-test`, `#id`, потім клас. Складні XPath уникаємо.
- Перевірки (`shouldBe`, `shouldHave`) живуть у методах Page Object з префіксом `check`/`validate`. Для цього проєкту це усталена практика.

## Очікування й таймаути
- Основний механізм: Selenide `shouldBe`/`shouldHave` із `DefaultDuration.DEFAULT` (30 с). Глобальний `Configuration.timeout` у `BaseTest` = 5000 мс.
- Заборонено `Thread.sleep`. Не додавай нові значення в `TimeOut` без потреби, використовуй наявні.

## Дані та конфіг
- URL і логін живуть в `AuthConfig`, логін перевизначається через `-Dtest.username=...`.
- Облікові дані й секрети не пишемо в нові файли. Нові значення беремо з `System.getProperty` або змінних середовища.
- Очікувані дані тестів (назви, описи, ціни товарів) лежать у `TestConstants`.
- Випадкові дані генеруємо через `helpers/Faker`.

## CI
- GitHub Actions (`ci.yml`): push/PR у `main`, нічний запуск о 03:00 UTC, ручний запуск.
- Спершу `quality-gates` (компіляція + заборона `@Test(enabled = false)`), потім `e2e` із повтором до 2 спроб.
- Тести ходять на живий публічний сайт, тому одиничні падіння через мережу можливі.

## Агенти
- Page Objects, локатори, UI-тести → `ui-test-engineer` (враховуй: тут Selenide, а не Playwright)
- Тестові дані, фабрики → `test-data-engineer`
- Нестабільний або впалий тест → `test-debugger`
- Зміни в CI → `ci-cd-engineer`
- Перед комітом → `code-reviewer`
- Вимоги й acceptance criteria → `ba-engineer`

## Робочі правила
- Відповідай українською, код і назви класів англійською.
- Перед змінами структури проєкту чи залежностей питай підтвердження.
- Не коміть і не пуш без явного прохання.
- Після змін запускай `mvn -q compile test-compile`, а для змінених тестів їх прогін.

## Відомі нюанси
- Запуск іде через Surefire за маскою `Test*`. `testng.xml` перелічує три наявні тестові класи, нові класи треба додавати і туди, якщо потрібен запуск через нього.
- `.playwright-mcp/` містить логи інструмента браузера, вони в `.gitignore` і не є частиною тестів.
- SSH-ключі та інші секрети в репозиторії не зберігаємо (див. `.gitignore`).
