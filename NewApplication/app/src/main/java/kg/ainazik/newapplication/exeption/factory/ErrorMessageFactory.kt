package kg.ainazik.newapplication.exeption.factory

import kg.ainazik.data.exeption.ConnectionLostException

class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable): String? {
            if (throwable is ConnectionLostException) {
                return "Проверьте интернет соедниение"
            }

            return throwable.localizedMessage
        }

    }
}