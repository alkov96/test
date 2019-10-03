# language: ru
Функционал: 1

  Предыстория:
    * сохраняем в память
      | NAME | randomNumber 5 |

  @test
  Сценарий: тест

    * переходит на страницу 'https://github.com/'
    * открывается страница "Главная неавторизованная Git"
    * пользователь (нажимает кнопку) "Sign In - Логин"

    * открывается страница "Логин"

    * пользователь (логинится с данными) "alkov96" "151112aA"
    * открывается страница "Авторизованная страница"

    * (проверяет, что пользователь залогиген под своей учёткой) "@alkov96"

    * запрос к API "user/repos?access_token=d789c8339da4dd7793716ee30121485016f391c3" и сохраняем в "RESPONCE_API":
      | name        | NAME                          |
      | description | This is your first repository |
      | homepage    | https://github.com            |
      | private     | true                          |

    * проверка ответа API из "RESPONCE_API":
      | exepted | "name":NAME                                   |
      | exepted | "description":"This is your first repository" |
      | exepted | "homepage":"https://github.com"               |
      | private | false                                         |


    * пользователь (нажимает кнопку) "Кнопка выбора параметров"

    * (выбирает из списка опций) "Your repositories"

    * открывается страница "Странциа с репозиториями"

    * (проверяет, создан ли репозиторий) "NAME"

#    * (удалим репозиторий) "NAME"


